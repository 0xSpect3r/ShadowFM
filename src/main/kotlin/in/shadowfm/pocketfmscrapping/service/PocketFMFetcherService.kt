package `in`.shadowfm.pocketfmscrapping.service

import `in`.shadowfm.pocketfmscrapping.dto.Show
import `in`.shadowfm.pocketfmscrapping.entity.ShowEntity
import `in`.shadowfm.pocketfmscrapping.entity.StoryEntity
import `in`.shadowfm.pocketfmscrapping.repository.ShowRepository
import `in`.shadowfm.pocketfmscrapping.repository.StoryRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class PocketFMFetcherService(val pocketFMService: PocketFMService, val showRepository: ShowRepository, val storyRepository: StoryRepository) {

    private val taskExecutor: SimpleAsyncTaskExecutor = SimpleAsyncTaskExecutor()
    private val log : Logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(cron = "0 35 14 ? * *")
    @Scheduled(cron = "0 35 18 ? * *")
    @Scheduled(cron = "0 35 8 ? * *")
    @Scheduled(cron = "0 35 2 ? * *")
    @Scheduled(cron = "0 0 0 ? * *")
    fun fetchAllEpisodesForAllShows() {
        taskExecutor.concurrencyLimit = 10
        showRepository.findAll().forEach {
            it.id?.let {it1 ->
                taskExecutor.execute { fetchAllEpisodes(it1, 0) }
            }
        }
    }

    private fun fetchAllEpisodes(show: String, currPtr: Int) {
        val stories = pocketFMService.fetchEpisodes(show,currPtr)
        if(stories?.isEmpty() == false && stories.first().stories?.isEmpty() == false) {
            val typeRef : TypeReference<List<StoryEntity>> = object : TypeReference<List<StoryEntity>>() {}
            stories.first().stories!!.forEach { story ->
                run {
                    if (story.media_url.isNullOrBlank()) {
                        story.story_id?.let {
                            storyRepository.findById(it).ifPresent { storyEntity ->
                                run {
                                    if (!storyEntity.mediaUrl.isNullOrBlank()) {
                                        story.media_url = storyEntity.mediaUrl
                                    }
                                }
                            }
                        }
                    }
                }
            }
            storyRepository.saveAll(ObjectMapper().convertValue(stories.first().stories,typeRef))
            log.info("${stories.first().show_title} :: total episodes = ${stories.first().episodes_count} :: current top episode = ${stories.first().stories?.first()?.story_title}")
        }
        while (stories?.first()?.stories?.count() == 10) {
            fetchAllEpisodes(show,currPtr + 10)
            return
        }
    }

    @Scheduled(cron = "0 30 14 ? * *")
    @Scheduled(cron = "0 30 8 ? * *")
    @Scheduled(cron = "0 30 2 ? * *")
    fun fetchAllShows() {
        val listTabs = pocketFMService.fetchFeedTabs()
        listTabs.let {
            it?.forEach {
                val listFeeds = it.apiType?.let { it1 -> pocketFMService.fetchShowsByTabs(it1) }
                listFeeds.let {
                    it?.forEach{
                        it.entities?.forEach{
                            if(it.type.equals("show")) {
                                val show: Show = ObjectMapper().convertValue(it.value, Show::class.java)
                                saveStory(show)
                                log.info("Saving : ${show.show_title}")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun saveStory(show: Show) {
        showRepository.save(ObjectMapper().convertValue(show, ShowEntity::class.java))
    }
}

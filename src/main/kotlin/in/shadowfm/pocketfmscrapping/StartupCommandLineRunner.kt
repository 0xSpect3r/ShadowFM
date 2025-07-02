package `in`.shadowfm.pocketfmscrapping

import `in`.shadowfm.pocketfmscrapping.service.PocketFMDownloaderService
import `in`.shadowfm.pocketfmscrapping.service.PocketFMFetcherService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.stereotype.Component

@Component
class StartupCommandLineRunner(
        val pocketFMFetcherService: PocketFMFetcherService,
        val pocketFMDownloaderService: PocketFMDownloaderService
) : CommandLineRunner {
    private val log : Logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        log.info("Start")
        SimpleAsyncTaskExecutor().execute {
//            pocketFMFetcherService.fetchAllShows()
//            pocketFMFetcherService.fetchAllEpisodesForAllShows()
            //pocketFMDownloaderService.downloadAllShowsAvailableEpisodes()
            log.info("End")
        }
    }
}

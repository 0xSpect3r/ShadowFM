package `in`.shadowfm.pocketfmscrapping.service

import `in`.shadowfm.pocketfmscrapping.entity.ShowEntity
import `in`.shadowfm.pocketfmscrapping.entity.StoryEntity
import `in`.shadowfm.pocketfmscrapping.repository.ShowRepository
import `in`.shadowfm.pocketfmscrapping.repository.StoryRepository
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URI
import java.net.URLConnection


@Service
public class PocketFMDownloaderService (val showRepository: ShowRepository,
                                 val storyRepository: StoryRepository) {
    private val taskExecutor = SimpleAsyncTaskExecutor()
    private val log : Logger = LoggerFactory.getLogger(javaClass)
    @PostConstruct
    fun init() {
        taskExecutor.concurrencyLimit = 100
    }

    fun downloadAllShowsAvailableEpisodes() {
        showRepository.findAll().forEach {
            downloadAllEpisodesFromShow(it)
        }
    }

    private fun downloadAllEpisodesFromShow(showEntity: ShowEntity) {
        if(showEntity.genre.equals("Suspense/Thriller") && showEntity.language.equals("hindi",true)) {
            val episodes = showEntity.id?.let { storyRepository.findAllByShowId(it) }
            episodes?.forEach {
                taskExecutor.execute { downloadEpisode(it,showEntity) }
            }
        }
    }

    private fun downloadEpisode(storyEntity: StoryEntity, showEntity: ShowEntity, count : Int = 0) {
        if(count == 3) return

        if(storyEntity.mediaUrl.isNullOrBlank()) return

        val basePath = "PocketFM/"
        val showDir : String = showEntity.showTitle?.trim()+"/"
        val episodeFileBaseName : String = storyEntity.storyTitle?.trim()+"_"
        val fileUrl : String = storyEntity.mediaUrl.toString()

        try {
            val baseDir = File(basePath)
            if(!baseDir.exists())
                baseDir.mkdir()
            val dir = File(basePath + replaceSpecialCharacters(showDir))
            if(!dir.exists())
                dir.mkdir()
            val url = URI(fileUrl).toURL()
            val conn: URLConnection = url.openConnection()
            val fileName = basePath+
                    replaceSpecialCharacters(showDir)+"/"+
                    replaceSpecialCharacters(episodeFileBaseName)+
                    replaceSpecialCharacters(getFileName(conn)) // Extract filename from URL
            //log.info("Downloading $fileName")
            if(File(fileName).exists() && File(fileName).length() == conn.getHeaderField("Content-Length").toLong()) {
                log.info("Already Exists $fileName")
                return
            }
            val inputStream = conn.getInputStream()
            val outputStream = FileOutputStream(fileName)
            val buffer = ByteArray(4096)
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
            outputStream.close()
            inputStream.close()
            println("File downloaded successfully as: $fileName")
        } catch (e: IOException) {
            log.warn("Exception in ${showEntity.showTitle} ${storyEntity.storyTitle}", e)
            downloadEpisode(storyEntity,showEntity,count+1)
        }
    }

    private fun getFileName(connection: URLConnection): String {
        val fileName = connection.getHeaderField("Content-Disposition")
        return if (fileName != null && fileName.contains("filename=")) {
            fileName.substring(fileName.indexOf("filename=") + 9)
                .replace("\"".toRegex(), "") // Remove double quotes if present
        } else {
            val urlString = connection.url.toString()
            urlString.substring(urlString.lastIndexOf("/") + 1)
        }
    }

    private fun replaceSpecialCharacters(input: String): String {
        // Replace all non-word characters except space (anything other than [a-zA-Z_0-9 ])
        return input.replace("[^a-zA-Z0-9 ._-]".toRegex(), "").trim()
    }
}
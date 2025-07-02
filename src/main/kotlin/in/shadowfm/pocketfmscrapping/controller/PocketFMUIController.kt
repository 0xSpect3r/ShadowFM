package `in`.shadowfm.pocketfmscrapping.controller

import `in`.shadowfm.pocketfmscrapping.service.PocketFMDataService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import javax.servlet.http.HttpServletResponse
import java.net.URL
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream


@Controller
@RequestMapping("/")
class PocketFMUIController(val pocketFMDataService: PocketFMDataService) {

    @RequestMapping(value = ["/","/shows"],method = [RequestMethod.GET])
    fun showList() : ModelAndView {
        val mav = ModelAndView("showList")
        mav.addObject("shows", pocketFMDataService.fetchAllShows())
        return mav
    }

    @RequestMapping("/episodes",method = [RequestMethod.GET])
    fun episodeList(
        @RequestParam showId: String,
        @RequestParam(required = false, defaultValue = "false") all: Boolean
    ) : ModelAndView {
        val mav = ModelAndView("episodeList")
        mav.addObject("show", pocketFMDataService.fetchShow(showId))
        val episodes = pocketFMDataService.fetchAllEpisodeByShow(showId)
        if (all) {
            mav.addObject("episodes", episodes)
            mav.addObject("showAll", true)
        } else {
            mav.addObject("episodes", episodes.take(10))
            mav.addObject("showAll", false)
            mav.addObject("episodesCount", episodes.size)
        }
        return mav
    }

    @RequestMapping("/episodes/downloadAll", method = [RequestMethod.GET])
    fun downloadAllEpisodes(@RequestParam showId: String, response: HttpServletResponse) {
        val show = pocketFMDataService.fetchShow(showId)
        val episodes = pocketFMDataService.fetchAllEpisodeByShow(showId)
        val showTitleSafe = show.show_title.replace("[^a-zA-Z0-9._-]".toRegex(), "_")

        response.contentType = "application/zip"
        response.setHeader(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"${showTitleSafe}_all_episodes.zip\""
        )

        ZipOutputStream(response.outputStream).use { zipOut ->
            episodes.sortedBy { it.natural_sequence_number }.forEach { episode ->
                val epNum = episode.natural_sequence_number.toString().padStart(2, '0')
                val epTitleSafe = episode.story_title.replace("[^a-zA-Z0-9._-]".toRegex(), "_")
                val entryName = "\${epNum}_\${epTitleSafe}.mp3"

                try {
                    val url = URL(episode.media_url)
                    url.openStream().use { input ->
                        zipOut.putNextEntry(ZipEntry(entryName))
                        input.copyTo(zipOut)
                        zipOut.closeEntry()
                    }
                } catch (e: Exception) {
                    // Skipping file on error
                }
            }
            zipOut.flush()
        }
    }

}
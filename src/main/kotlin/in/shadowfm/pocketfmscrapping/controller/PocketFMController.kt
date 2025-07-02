package `in`.shadowfm.pocketfmscrapping.controller

import `in`.shadowfm.pocketfmscrapping.dto.Show
import `in`.shadowfm.pocketfmscrapping.dto.Story
import `in`.shadowfm.pocketfmscrapping.service.PocketFMDataService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pocket-fm")
class PocketFMController(val pocketFMDataService: PocketFMDataService) {
    @RequestMapping(value = ["/all-shows"],method = [RequestMethod.GET])
    fun allShows():List<Show> {
        return pocketFMDataService.fetchAllShows()
    }

    @RequestMapping(value = ["/all-genre"],method = [RequestMethod.GET])
    fun allGenre():Set<String?> {
        return pocketFMDataService.fetchAllGenre()
    }

    @RequestMapping(value = ["/all-shows-by-genre"],method = [RequestMethod.GET])
    fun allShowsByGenre(@RequestParam genre: String):List<Show> {
        return pocketFMDataService.fetchAllShowsByGenre(genre)
    }

    @RequestMapping(value = ["/all-episodes-by-show"],method = [RequestMethod.GET])
    fun allEpisodesByShow(@RequestParam show: String):List<Story> {
        return pocketFMDataService.fetchAllEpisodeByShow(show)
    }
}
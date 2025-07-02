package `in`.shadowfm.pocketfmscrapping.service

import `in`.shadowfm.pocketfmscrapping.dto.Show
import `in`.shadowfm.pocketfmscrapping.dto.Story
import `in`.shadowfm.pocketfmscrapping.repository.ShowRepository
import `in`.shadowfm.pocketfmscrapping.repository.StoryRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service

@Service
class PocketFMDataService(val showRepository: ShowRepository, val storyRepository: StoryRepository) {

    fun fetchAllShows():List<Show> {
        val typeRef: TypeReference<List<Show>> = object : TypeReference<List<Show>>(){}
        return ObjectMapper().convertValue(showRepository.findAll(),typeRef)
    }

    fun fetchAllShowsByGenre(genre :String?):List<Show> {
        val typeRef: TypeReference<List<Show>> = object : TypeReference<List<Show>>(){}
        return ObjectMapper().convertValue(showRepository.findAllByGenre(genre),typeRef)
    }

    fun fetchAllGenre(): Set<String?> {
        val list: HashSet<String?> = hashSetOf()
        fetchAllShows().forEach{ list.add(it.genre) }
        return list;
    }

    fun fetchShow(show :String): Show {
        val typeRef: TypeReference<Show> = object : TypeReference<Show>(){}
        return ObjectMapper().convertValue(showRepository.findById(show).get(),typeRef)
    }

    fun fetchAllEpisodeByShow(show :String):List<Story> {
        val typeRef: TypeReference<List<Story>> = object : TypeReference<List<Story>>(){}
        return ObjectMapper().convertValue(storyRepository.findAllByShowId(show),typeRef)
    }
}
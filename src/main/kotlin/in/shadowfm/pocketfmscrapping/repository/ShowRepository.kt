package `in`.shadowfm.pocketfmscrapping.repository

import `in`.shadowfm.pocketfmscrapping.entity.ShowEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ShowRepository : MongoRepository<ShowEntity,String> {
    fun findAllByGenre(genre: String?): List<ShowEntity>?
}
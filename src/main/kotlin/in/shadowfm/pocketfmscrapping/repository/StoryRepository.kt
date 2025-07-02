package `in`.shadowfm.pocketfmscrapping.repository

import `in`.shadowfm.pocketfmscrapping.entity.StoryEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StoryRepository : MongoRepository<StoryEntity,String> {
    fun findAllByShowId(showId: String) : List<StoryEntity>
}
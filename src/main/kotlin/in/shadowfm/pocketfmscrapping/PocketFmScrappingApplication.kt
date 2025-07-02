package `in`.shadowfm.pocketfmscrapping

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableMongoRepositories
@SpringBootApplication
class PocketFmScrappingApplication

fun main(args: Array<String>) {
    runApplication<PocketFmScrappingApplication>(*args)
}

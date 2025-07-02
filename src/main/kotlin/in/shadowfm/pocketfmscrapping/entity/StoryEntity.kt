package `in`.shadowfm.pocketfmscrapping.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class StoryEntity {
    @JsonProperty("created_by")
    var createdBy: String? = null
    @JsonProperty("story_title")
    var storyTitle: String? = null
    @JsonProperty("show_id")
    var showId: String? = null
    @JsonProperty("create_time")
    var createTime:Date? = null
    @JsonProperty("image_url")
    var imageurl: String? = null
    @JsonProperty("media_url")
    var mediaUrl: String? = null
    var duration: Double? = null
    @JsonProperty("topic_ids")
    var topicIds: String? = null
    var status: String? = null
    @JsonProperty("is_ugc")
    var isUgc: Boolean? = null
    @JsonProperty("seq_number")
    var seqNumber: Int? = null
    @Id
    @JsonProperty("story_id")
    var id: String? = null
    var entity_type: String? = null
    @JsonProperty("days_since_upload")
    var daysSinceUpload: String? = null
    var stats: Map<String, Any?>? = null
    var user_info: Map<String, Any?>? = null
    var description: String? = null
    @JsonProperty("natural_sequence_number")
    var naturalSequenceNumber: Int? = null
    @JsonProperty("is_part_of_series")
    var isPartOfSeries: Boolean? = null
    @JsonProperty("story_desc")
    var storyDesc: String? = null
    var video_url: String? = null
    var media_meta_data: Map<String, Any?>? = null
    @JsonProperty("story_type")
    var storyType: String? = null
    var coins_required: Int? = null
    var share_media_url: String? = null
}
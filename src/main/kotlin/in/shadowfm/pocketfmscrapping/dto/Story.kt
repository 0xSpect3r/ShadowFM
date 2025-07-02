package `in`.shadowfm.pocketfmscrapping.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Data
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Story {
    var created_by: String? = null
    var story_title: String? = null
    var show_id: String? = null
    var create_time:Date? = null
    var image_url: String? = null
    var media_url: String? = null
    var duration: Double? = null
    var topic_ids: String? = null
    var status: String? = null
    var is_ugc: Boolean? = null
    var seq_number: Int? = null
    var story_id: String? = null
    var entity_type: String? = null
    var days_since_upload: String? = null
    var stats: Map<String, Any?>? = null
    var user_info: Map<String, Any?>? = null
    var description: String? = null
    var natural_sequence_number: Int? = null
    var is_part_of_series: Boolean? = null
    var story_desc: String? = null
    var video_url: String? = null
    var media_meta_data: Map<String, Any?>? = null
    var story_type: String? = null
    var coins_required: Int? = null
    var share_media_url: String? = null
}
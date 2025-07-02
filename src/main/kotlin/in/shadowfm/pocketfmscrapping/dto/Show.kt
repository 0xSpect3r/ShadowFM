package `in`.shadowfm.pocketfmscrapping.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Data
import java.util.*

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Show {
    var created_by: String? = null
    var show_title: String? = null
    var create_time: Date? = null
    var image_url: String? = null
    var show_desc: String? = null
    var language: String? = null
    var topic_ids: String? = null
    var series = false
    var sort_order: String? = null
    var editor_score = 0
    var last_story_added_time: Date? = null
    var hash_tags: String? = null
    var author_id: String? = null
    var callout_info: String? = null
    var show_desc_en: String? = null
    var upload_frequence = 0.0
    var show_type: String? = null
    var is_split_allowed = false
    var episode_locking_point = 0
    var show_id: String? = null
    var entity_type: String? = null
    var gift_url: String? = null
    var episodes_count = 0
    var stories: List<Story>? = null
    var next_ptr = 0
    var tab_count = 0
    var show_duration = 0
    var unlocked_episodes_count = 0
    var is_coin_user = false
    var paid = false
    var unordered_unlock_flag = false
    var extra_details: Map<String, Any>? = null
    var stats: Map<String, Any>? = null
    var user_info: Map<String, Any>? = null
    var genre: String? = null
    var is_audiobook = false
    var rank = 0
    var completed = false
    var show_category_rank = 0
    var leader_board_topic_id: String? = null
    var rank_text: String? = null
    var vip_timestamp: Date? = null
    var crosswalk_intervention_episode: String? = null
}

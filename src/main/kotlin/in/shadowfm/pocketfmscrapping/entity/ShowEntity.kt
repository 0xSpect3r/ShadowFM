package `in`.shadowfm.pocketfmscrapping.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ShowEntity {
    @JsonProperty("create_by")
    var createdBy: String? = null
    @JsonProperty("show_title")
    var showTitle: String? = null
    @JsonProperty("create_time")
    var createTime: Date? = null
    var image_url: String? = null
    @JsonProperty("show_desc")
    var showDesc: String? = null
    var language: String? = null
    @JsonProperty("topic_ids")
    var topicIds: String? = null
    var series = false
    @JsonProperty("sort_order")
    var sortOrder: String? = null
    @JsonProperty("editor_score")
    var editorScore = 0
    @JsonProperty("last_story_added_time")
    var lastStoryAddedTime: Date? = null
    var hash_tags: String? = null
    @JsonProperty("author_id")
    var authorId: String? = null
    var callout_info: String? = null
    @JsonProperty("show_desc_en")
    var showDescEn: String? = null
    @JsonProperty("upload_frequence")
    var uploadFrequence = 0.0
    @JsonProperty("show_type")
    var showType: String? = null
    var is_split_allowed = false
    var episode_locking_point = 0
    @Id
    @JsonProperty("show_id")
    var id: String? = null
    var entity_type: String? = null
    var gift_url: String? = null
    @JsonProperty("episodes_count")
    var episodesCount = 0
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
    @JsonProperty("is_audiobook")
    var isAudiobook = false
    var rank = 0
    var completed = false
    @JsonProperty("show_category_rank")
    var showCategoryRank = 0
    @JsonProperty("leader_board_topic_id")
    var leaderBoardTopicId: String? = null
    var rank_text: String? = null
    var vip_timestamp: Date? = null
    var crosswalk_intervention_episode: String? = null
}

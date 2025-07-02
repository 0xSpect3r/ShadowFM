package `in`.shadowfm.pocketfmscrapping.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Data


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class FeedResult {
    var module_id: String? = null
    var rank = 0
    var fixed = false
    var default_sort: String? = null
    var lowest_supported_appv = 0
    var api_type: String? = null
    var language: String? = null
    var layout_info: Map<String,Any?>? = null
    var locale: String? = null
    var module_name: String? = null
    var is_disabled = 0
    var is_explicit = 0
    var is_clickable = false
    var module_type: Any? = null
    var web_meta: Any? = null
    var entity_type: String? = null
    var check_count = false
    var props: Map<String,Any?>? = null
    var entities: ArrayList<PFEntity>? = null
}
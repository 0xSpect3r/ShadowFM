package `in`.shadowfm.pocketfmscrapping.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class ResponseWrapper<T> {
    var status = 0
    var message: String? = null

    @JsonProperty("is_learn_tab_enabled")
    var isLearnTabEnabled = false
    var result: T? = null
}

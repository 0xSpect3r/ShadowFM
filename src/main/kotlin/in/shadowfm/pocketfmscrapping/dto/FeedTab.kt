package `in`.shadowfm.pocketfmscrapping.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class FeedTab {
    @JsonProperty("title")
    val title: String? = null

    @JsonProperty("api_type")
    val apiType: String? = null
}

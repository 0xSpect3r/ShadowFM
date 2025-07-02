package `in`.shadowfm.pocketfmscrapping.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import lombok.NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PFEntity {
    var type: String? = null
    var value: Map<String,Any?>? = null
}

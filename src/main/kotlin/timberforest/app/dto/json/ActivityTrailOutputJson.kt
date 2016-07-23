package timberforest.app.dto.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Created by masahiro on 2016/07/23.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class ActivityTrailOutputJson(private val request: ActivityTrailJsonRequest) {
    val timestamp = request.timestamp
    val tag = request.tag
    @JsonProperty("log_level")
    val logLevel = request.logLevel
    val message = ObjectMapper().writeValueAsString(request.message)
    val action = request.action
}
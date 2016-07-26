package timberforest.app.dto.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.http.HttpServletRequest

/**
 * Created by masahiro on 2016/07/23.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class ActivityTrailOutputJson(private val request: HttpServletRequest, private val requestJson: ActivityTrailJsonRequest) {
    @JsonProperty("@timestamp")
    val timestamp = requestJson.timestamp
    val tag = requestJson.tag
    @JsonProperty("log_level")
    val logLevel = requestJson.logLevel
    val message = ObjectMapper().writeValueAsString(requestJson.message)
    val action = requestJson.action
    @JsonProperty("ip_addr")
    val ipAddr = request.remoteAddr
    @JsonProperty("host_name")
    val hostName = request.remoteHost
}
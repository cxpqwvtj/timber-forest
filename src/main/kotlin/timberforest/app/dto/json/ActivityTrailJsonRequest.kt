package timberforest.app.dto.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by masahiro on 2016/07/20.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class ActivityTrailJsonRequest {
    val timestamp = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Date())
    var tag: String? = null
    @JsonProperty("log_level")
    var logLevel: String? = null
    var message: Any? = null
    var action: String? = null
}
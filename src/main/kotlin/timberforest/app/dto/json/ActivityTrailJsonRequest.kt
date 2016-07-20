package timberforest.app.dto.json

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

/**
 * Created by masahiro on 2016/07/20.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class ActivityTrailJsonRequest {
    val timestamp = Date()
    var tag: String? = null
    var log_level: String? = null
    var message: String? = null
    var action: String? = null
}
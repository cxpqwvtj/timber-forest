package timberforest.app.dto.json

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by masahiro on 2016/04/13.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class RootJsonResponse(val result: Any?, val error: ErrorJsonResponse?) {
    constructor(result: Any) : this(result, null) {
    }

    constructor(error: ErrorJsonResponse) : this(null, error) {
    }
}
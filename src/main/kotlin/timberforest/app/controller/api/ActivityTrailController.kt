package timberforest.app.controller.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.slf4j.MarkerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import timberforest.app.dto.json.ActivityTrailJsonRequest
import timberforest.app.dto.json.ActivityTrailOutputJson
import timberforest.app.dto.json.RootJsonResponse
import javax.servlet.http.HttpServletRequest

/**
 * Created by masahiro on 2016/07/20.
 */
@RestController
@RequestMapping("/api")
open class ActivityTrailController {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping(value = "/trail/create")
    open fun trail(request: HttpServletRequest, @RequestBody trail: ActivityTrailJsonRequest): RootJsonResponse {
        logger.trace("called trail")
        val json = ObjectMapper().writeValueAsString(ActivityTrailOutputJson(request, trail))
        logger.trace(MarkerFactory.getMarker("TRAIL"), json)
        return RootJsonResponse("")
    }
}

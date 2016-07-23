package timberforest.app.controller.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.slf4j.MarkerFactory
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
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
class ActivityTrailController {

    private val logger = LoggerFactory.getLogger(ActivityTrailController::class.java)

    @RequestMapping(value = "/trail/create", method = arrayOf(RequestMethod.POST))
    fun trail(request: HttpServletRequest, @RequestBody trail: ActivityTrailJsonRequest): RootJsonResponse {
        logger.trace("called trail")
        // サーバ側で扱いやすくするため、一旦オブジェクトに変換している
        val json = ObjectMapper().writeValueAsString(ActivityTrailOutputJson(request, trail))
        logger.trace(MarkerFactory.getMarker("TRAIL"), json)
        return RootJsonResponse("")
    }
}
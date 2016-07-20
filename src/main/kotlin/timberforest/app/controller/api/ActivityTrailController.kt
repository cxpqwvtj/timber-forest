package timberforest.app.controller.api

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by masahiro on 2016/07/20.
 */
@RestController
@RequestMapping("/api")
class ActivityTrailController {

    private val logger = LoggerFactory.getLogger(ActivityTrailController::class.java)

    @RequestMapping(value = "/trail", method = arrayOf(RequestMethod.POST))
    fun trail() {
        logger.trace("called trail")
    }
}
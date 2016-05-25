package timberforest.app.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by masahiro on 2016/04/14.
 */
@Controller
@RequestMapping("/")
class RootController {
    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun root(): String {
        return "redirect:index.html"
    }
}
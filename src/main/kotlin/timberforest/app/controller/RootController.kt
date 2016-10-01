package timberforest.app.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletResponse

/**
 * Created by masahiro on 2016/04/14.
 */
@Controller
@RequestMapping("/")
open class RootController {
    @RequestMapping(path = arrayOf("", "timber/**"), method = arrayOf(RequestMethod.GET))
    open fun root(response: HttpServletResponse) {
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
        response.outputStream.use { it.write(this.javaClass.getResource("/static/index.html").readBytes()) }
    }
}
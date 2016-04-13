package timberforest.app.interceptor

import org.slf4j.LoggerFactory
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by masahiro on 2016/04/14.
 */
class RequestLoggingInterceptor : HandlerInterceptorAdapter() {
    private val logger = LoggerFactory.getLogger(RequestLoggingInterceptor::class.java)

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?): Boolean {
        //        logger.trace("preHandle")
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?,
                            modelAndView: ModelAndView?) {
        //        logger.trace("postHandle")
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, ex: Exception?) {
        //        logger.trace("afterCompletion")
    }

    @Throws(Exception::class)
    override fun afterConcurrentHandlingStarted(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?) {
        logger.trace("afterConcurrentHandlingStarted")
    }

}
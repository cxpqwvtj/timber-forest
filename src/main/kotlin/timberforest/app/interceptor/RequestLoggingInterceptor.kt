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

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val LF = "\n"

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?): Boolean {
        if (request != null) {
            val list = mutableListOf<String>()
            list.add("*********************** BEGIN ${request.servletPath}")
            if (logger.isTraceEnabled) {
                list.add("[URL]${request.requestURL} [query]${request.queryString} [method]${request.method}")
                list.add("[protocol]${request.protocol} [scheme]${request.scheme} [secure]${request.isSecure} [RemoteAddr]${request.remoteAddr} [RemoteHost]${request.remoteHost} [SessionId]${request.requestedSessionId} [class]${request.javaClass.name}")
                list.add("[ContentLength]${request.contentLength} [contentType]${request.contentType} [encoding]${request.characterEncoding} [locale]${request.locale} [locales]${request.locales.toList().joinToString(" ")}")
                list.addAll(request.headerNames?.toList()?.map { "[header]$it: ${request.getHeader(it)}" } ?: arrayListOf())
                list.addAll(request.parameterNames?.toList()?.map { "[parameter]$it: ${request.getParameterValues(it).joinToString(", ")}" } ?: arrayListOf())
                list.addAll(request.cookies?.toList()?.map { "[cookie]${it.name}: ${it.value}" } ?: arrayListOf())
//            list.addAll(request.attributeNames?.toList()?.map { "[attribute]$it: ${request.getAttribute(it)}" } ?: arrayListOf())
//            val session = request.getSession(false)
//            if (session != null) {
//                list.addAll(session.attributeNames?.toList()?.map { "[session]$it: ${session.getAttribute(it)}" } ?: arrayListOf())
//            }
            }
            logger.debug(list.joinToString(LF))
        }
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?,
                            modelAndView: ModelAndView?) {
//        logger.trace("postHandle")
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, ex: Exception?) {
        if (request != null) {
            logger.debug("***********************  END  ${request.servletPath}")
        }
    }

    @Throws(Exception::class)
    override fun afterConcurrentHandlingStarted(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?) {
        logger.trace("afterConcurrentHandlingStarted")
    }

}
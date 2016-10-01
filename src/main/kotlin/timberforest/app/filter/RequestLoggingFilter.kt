package timberforest.app.filter

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest

/**
 * Created by masahiro on 2016/04/14.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class RequestLoggingFilter : Filter {

    enum class FilterLoggingType(val type: Int) {
        NOTHING(0),
        SIMPLE(1),
        VERBOSE(2)
    }

    @Value("\${app.log.filter.enabled}")
    private val loggingType: FilterLoggingType = FilterLoggingType.NOTHING

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val LF = "\n"

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        logger.trace("init")
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, chain: FilterChain) {
        val request = servletRequest as HttpServletRequest
        if (loggingType == FilterLoggingType.SIMPLE) {
            logger.debug("[${request.method}] ${request.servletPath}")
        } else if (loggingType == FilterLoggingType.VERBOSE) {
            val list = mutableListOf<String>()
            list.add("*********************** BEGIN ${request.servletPath}")
            list.add("[URL]${request.requestURL} [query]${request.queryString} [method]${request.method}")
            list.add("[protocol]${request.protocol} [scheme]${request.scheme} [secure]${request.isSecure} [RemoteAddr]${request.remoteAddr} [RemoteHost]${request.remoteHost} [SessionId]${request.requestedSessionId} [class]${request.javaClass.name}")
            list.add("[ContentLength]${request.contentLength} [contentType]${request.contentType} [encoding]${request.characterEncoding} [locale]${request.locale} [locales]${request.locales.toList().joinToString(" ")}")
            list.addAll(request.headerNames?.toList()?.map { "[header]$it: ${request.getHeader(it)}" } ?: arrayListOf())
            list.addAll(request.parameterNames?.toList()?.map { "[parameter]$it: ${request.getParameterValues(it).joinToString(", ")}" } ?: arrayListOf())
            list.addAll(request.cookies?.toList()?.map { "[cookie]${it.name}: ${it.value}" } ?: arrayListOf())
            list.addAll(request.attributeNames?.toList()?.map { "[attribute]$it: ${request.getAttribute(it)}" } ?: arrayListOf())
            val session = request.getSession(false)
            if (session != null) {
                list.addAll(session.attributeNames?.toList()?.map { "[session]$it: ${session.getAttribute(it)}" } ?: arrayListOf())
            }
            logger.debug(list.joinToString(LF))
        }
        chain.doFilter(servletRequest, servletResponse)
        if (loggingType == FilterLoggingType.VERBOSE) {
            logger.debug("***********************  END  ${request.servletPath}")
        }
    }

    override fun destroy() {
        logger.trace("destroy")
    }
}
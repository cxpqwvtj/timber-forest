package timberforest.app.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.*
import javax.servlet.*
import javax.servlet.http.HttpServletRequest

/**
 * Created by masahiro on 2016/04/14.
 */
class RequestLoggingFilter : Filter {

    private val logger = LoggerFactory.getLogger(RequestLoggingFilter::class.java)
    private val LF = "\n"

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        logger.trace("init")
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, chain: FilterChain) {
        if (logger.isTraceEnabled) {
            val request = servletRequest as HttpServletRequest
            val headerNames = request.headerNames

            val map = LinkedHashMap<String, Any>()
            map.put("URI", request.requestURI)
            map.put("method", request.method)
            map.put("URL", request.requestURL)

            val header = LinkedHashMap<String, String>()
            while (headerNames.hasMoreElements()) {
                val key = headerNames.nextElement()
                val value = request.getHeader(key)
                header.put(key, value)
            }
            map.put("header", header)
            if (request.method == "POST") {
                map.put("content-length", request.contentLengthLong)
            }
            logger.trace("[REQUEST]" + ObjectMapper().writeValueAsString(map))

            val list = mutableListOf<String>()
            list.add("*********************** Begin")
            list.add("[URL]${request.requestURL} [query]${request.queryString} [SessionId]${request.requestedSessionId} [class]${request.javaClass.name}")
            list.add("[method]${request.method} [protocol]${request.protocol} [scheme]${request.scheme} [secure]${request.isSecure} [RemoteAddr]${request.remoteAddr} [RemoteHost]${request.remoteHost}")
            list.add("[encoding]${request.characterEncoding} [ContentLength]${request.contentLength} [contentType]${request.contentType} [locale]${request.locale} [locales]${request.locales.toList().joinToString(" ")}")
            list.addAll(request.headerNames?.toList()?.map { "[header]$it: ${request.getHeader(it)}" } ?: arrayListOf())
            list.addAll(request.parameterNames?.toList()?.map { "[parameter]$it: ${request.getParameterValues(it).joinToString(", ")}" } ?: arrayListOf())
            list.addAll(request.cookies?.toList()?.map { "[cookie]${it.name}: ${it.value}" } ?: arrayListOf())
            list.addAll(request.attributeNames?.toList()?.map { "[attribute]$it: ${request.getAttribute(it)}" } ?: arrayListOf())
            val session = request.getSession(false)
            if (session != null) {
                list.addAll(session.attributeNames?.toList()?.map { "[session]$it: ${session.getAttribute(it)}" } ?: arrayListOf())
            }
            logger.trace(list.joinToString(LF))
        }
        chain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {
        logger.trace("destroy")
    }
}
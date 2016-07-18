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
class RequestLoggingFilter: Filter {

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
            val requestInfo = mutableListOf<String>()
            requestInfo.add("${request.javaClass.name}")
            requestInfo.add("${request.requestedSessionId}")
            requestInfo.add("${request.requestURL}")
            requestInfo.add("${request.queryString}")
            requestInfo.add("${request.method}")
            requestInfo.add("${request.protocol}")
            requestInfo.add("${request.scheme}")
            requestInfo.add("${request.isSecure}")
            requestInfo.add("${request.remoteAddr}")
            requestInfo.add("${request.remoteHost}")
            requestInfo.add("${request.characterEncoding}")
            requestInfo.add("${request.contentLength}")
            requestInfo.add("${request.contentType}")
            requestInfo.add("${request.locale}")
            requestInfo.add("${request.locales}")

            val list = mutableListOf<String>()
            list.add("*********************** Begin$LF")
            list.add(request.headerNames.toList().map { "[header]$it: ${request.getHeader(it)}" }.joinToString(LF))
            list.add(request.parameterNames.toList().map { name -> "[$name]${request.getParameterValues(name).joinToString(", ")}" }.joinToString(LF))
//            request.cookies
//            request.attributeNames
            val session = request.getSession(false)
            if (session!=null){
//                session.attributeNames
            }
        }
        chain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {
        logger.trace("destroy")
    }
}
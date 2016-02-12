package timberforest.app.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.trace("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        if (logger.isTraceEnabled()) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            Enumeration<String> headerNames = request.getHeaderNames();

            Map<String, Object> map = new LinkedHashMap<>();
            map.put("URI", request.getRequestURI());
            map.put("method", request.getMethod());
            map.put("URL", request.getRequestURL());

            Map<String, String> header = new LinkedHashMap<>();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                header.put(key, value);
            }
            map.put("header", header);
            if (request.getMethod().equals("POST")) {
                map.put("content-length", request.getContentLengthLong());
            }
            logger.trace("[REQUEST]" + new ObjectMapper().writeValueAsString(map));
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.trace("destroy");
    }

}

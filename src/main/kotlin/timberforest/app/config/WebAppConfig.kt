package timberforest.app.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import timberforest.app.interceptor.RequestLoggingInterceptor

/**
 * Created by masahiro on 2016/04/14.
 */
@Component
open class WebAppConfig : WebMvcConfigurerAdapter() {

    @Value("\${app.log.interceptor.type}")
    private val loggingType: RequestLoggingInterceptor.LoggingType = RequestLoggingInterceptor.LoggingType.NOTHING

    override fun addInterceptors(registry: InterceptorRegistry?) {
        // for logging
        registry!!.addInterceptor(RequestLoggingInterceptor(loggingType)).addPathPatterns("/**")
    }
}
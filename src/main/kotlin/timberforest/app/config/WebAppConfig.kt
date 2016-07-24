package timberforest.app.config

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import timberforest.app.filter.RequestLoggingFilter
import timberforest.app.interceptor.RequestLoggingInterceptor

/**
 * Created by masahiro on 2016/04/14.
 */
@Component
open class WebAppConfig : WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter() {

    override fun addInterceptors(registry: InterceptorRegistry?) {
        // for logging
        registry!!.addInterceptor(RequestLoggingInterceptor()).addPathPatterns("/**")
    }

    @Bean
    open fun filterRegistrationBean(): FilterRegistrationBean {
        // for logging
        val registrationBean = FilterRegistrationBean()
        registrationBean.setFilter(RequestLoggingFilter())
        registrationBean.order = 1
        return registrationBean
    }
}
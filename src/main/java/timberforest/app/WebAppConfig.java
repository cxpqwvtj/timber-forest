package timberforest.app;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import timberforest.app.interceptor.RequestLoggingInterceptor;

@Component
public class WebAppConfig extends WebMvcAutoConfigurationAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // for logging
        // registry.addInterceptor(new GodHandableControllerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new RequestLoggingInterceptor()).addPathPatterns("/**");
    }

    // @Bean
    // public FilterRegistrationBean filterRegistrationBean() { // for logging
    // FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    // RequestLoggingFilter loggingFilter = new RequestLoggingFilter();
    // registrationBean.setFilter(loggingFilter);
    // registrationBean.setOrder(1);
    // return registrationBean;
    // }
}

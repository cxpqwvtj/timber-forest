package timberforest.app.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Created by masahiro on 2016/07/24.
 */
@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.inMemoryAuthentication()
                .withUser("user").password("user").authorities("ROLE_USER").and()
                .withUser("admin").password("admin").authorities("ROLE_ADMIN")
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests().anyRequest().authenticated()
        http.formLogin()
    }
}

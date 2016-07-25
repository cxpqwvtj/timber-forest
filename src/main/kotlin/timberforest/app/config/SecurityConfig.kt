package timberforest.app.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.WebUtils
import java.io.IOException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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
        http!!.httpBasic()
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                //ログアウト設定
                .and().logout().permitAll()
                .logoutRequestMatcher(AntPathRequestMatcher("/api/logout"))
                .and().csrf().csrfTokenRepository(csrfTokenRepository())
                .and().addFilterAfter(csrfHeaderFilter(), CsrfFilter::class.java)
    }

    //セッションヘッダーにCSRFトークンを設定
    private fun csrfTokenRepository(): CsrfTokenRepository {
        val repository = HttpSessionCsrfTokenRepository()
        repository.setHeaderName("X-CSRF-TOKEN")
        return repository
    }

    private fun csrfHeaderFilter(): Filter {
        return object : OncePerRequestFilter() {
            @Throws(ServletException::class, IOException::class)
            override fun doFilterInternal(request: HttpServletRequest,
                                          response: HttpServletResponse, filterChain: FilterChain) {
                val csrf = request.getAttribute(CsrfToken::class.java.getName()) as CsrfToken?
                if (csrf != null) {
                    var cookie = WebUtils.getCookie(request, "XSRF-TOKEN")
                    val token = csrf.getToken()
                    if (cookie == null || token != null && token != cookie.getValue()) {
                        cookie = Cookie("XSRF-TOKEN", token)
                        cookie.setPath("/")
                        response.addCookie(cookie)
                    }
                }
                filterChain.doFilter(request, response)
            }
        }
    }
}

package helpboard.board.common.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@Import(SecurityConfig.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService repositoryUserDetailsService;

    @Value("${auth.rememberme.secret:null}")
    String rememberMeSecret;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors().and()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/logout-success")
                .and()
                .formLogin()
                .loginProcessingUrl("/auth/login")
                .permitAll()
                .successForwardUrl("/auth/success")
//                    .successHandler(successHandler())
                .failureForwardUrl("/auth/failure")
//                    .failureHandler(failureHandler())
                .and()
                .rememberMe()
                .key(rememberMeSecret)
                .rememberMeCookieName("auth_token_remember")
                .and()
                .exceptionHandling() // 1
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(repositoryUserDetailsService)
                .and()
                .eraseCredentials(true);
    }
}

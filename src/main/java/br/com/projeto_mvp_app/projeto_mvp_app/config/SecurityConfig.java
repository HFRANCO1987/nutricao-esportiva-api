package br.com.projeto_mvp_app.projeto_mvp_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] permitAll = {
            "/*",
            "/oauth/authorize",
            "/oauth/token",
            "/oauth/confirm_access"
        };

        http
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .and()
            .requestMatchers()
            .antMatchers(permitAll)
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .and().formLogin().disable()
            .csrf().disable()
            .headers().frameOptions().disable();
    }
}

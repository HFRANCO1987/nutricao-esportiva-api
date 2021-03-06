package br.com.projeto_mvp_app.projeto_mvp_app.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao.ADMIN;
import static br.com.projeto_mvp_app.projeto_mvp_app.modules.usuario.enums.EPermissao.USER;
import static java.util.Arrays.asList;

@Configuration
@EnableResourceServer
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

    private static final String TEST_PROFILE = "test";

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private Environment environment;

    @Override
    @SuppressWarnings({"checkstyle:methodlength"})
    public void configure(HttpSecurity http) throws Exception {
        String[] permitAll = {
            "/login/**",
            "/oauth/token",
            "/oauth/authorize",
            "/api/usuarios/novo",
            "/api/usuarios/is-authenticated",
            "/swagger-ui.html"
        };

        http
            .addFilterBefore(new CorsConfigFilter(), ChannelProcessingFilter.class)
            .requestMatchers()
            .antMatchers("/**")
            .and()
            .authorizeRequests()
            .antMatchers(permitAll).permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers("/api/usuarios/**").hasAnyRole(ADMIN.name(), USER.name())
            .antMatchers("/api/dieta/**").hasAnyRole(ADMIN.name(), USER.name())
            .antMatchers("/api/periodos/**").hasAnyRole(ADMIN.name(), USER.name())
            .antMatchers("/api/taco/alimentos/**").hasAnyRole(ADMIN.name(), USER.name())
            .antMatchers("/api/esportes/**").hasAnyRole(ADMIN.name(), USER.name());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        if (asList(environment.getActiveProfiles()).contains(TEST_PROFILE)) {
            resources.stateless(false);
        }
        resources.tokenStore(tokenStore);
    }
}

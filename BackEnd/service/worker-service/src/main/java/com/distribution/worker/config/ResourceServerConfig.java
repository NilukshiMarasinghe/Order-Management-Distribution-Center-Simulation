package com.distribution.worker.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ConfigProperties configs;

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer) {
        configurer
                .resourceId(configs.getAuth().getResourceId())
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //http.requestMatcher(request -> request.getServletPath().startsWith("/api/"))
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, configs.getEndpoint().getGetShortestPath()).permitAll()
                .antMatchers(HttpMethod.GET, "/spring-security-rest/api/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetTasksByPicker()).permitAll()

                .antMatchers("/N.html").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/test/message").permitAll()
                .antMatchers("/**").denyAll();
    }
}

package com.distribution.simulation.config;

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

//
                //.antMatchers(HttpMethod.POST, configs.getEndpoint().getUserCreate()).hasAuthority("u-c")
//                DC MAP
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetAllVertices()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetDcMap()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetOppositeVertices()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetPackingStations()).permitAll()
                .antMatchers(HttpMethod.POST, configs.getEndpoint().getGetShortestPath()).permitAll()

//                Items
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetAllItems()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetAllItemsMin()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getItemView()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getItemById()).permitAll()
                // Worker
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetAllWorkers()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetAllByStatus()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getGetByName()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getChangeWorkerStatus()).permitAll()

                .antMatchers(HttpMethod.POST, configs.getEndpoint().getUserCreate()).permitAll()
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getUserView()).permitAll()
                .antMatchers(HttpMethod.POST, configs.getEndpoint().getRoleCreate()).hasAuthority("r-c")
                .antMatchers(HttpMethod.GET, configs.getEndpoint().getRoleView()).hasAuthority("r-v")


                .antMatchers("/N.html").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/test/message").permitAll()
                .antMatchers("/**").denyAll();
    }
}

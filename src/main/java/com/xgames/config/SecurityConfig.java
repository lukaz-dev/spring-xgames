package com.xgames.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("joao").password("123").roles("TABLE_GAMES").and()
                .withUser("admin").password("adm").roles("TABLE_GAMES", "NEW_GAME", "SEARCH_GAMES");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/layout/**")
                .antMatchers("/javascripts/**")
                .antMatchers("/stylesheets/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/games").hasRole("TABLE_GAMES")
                .antMatchers("/games/new").hasRole("NEW_GAME")
                .antMatchers(HttpMethod.POST, "/games/new").hasRole("NEW_GAME")
                .antMatchers("/games/search").hasRole("SEARCH_GAMES")
                .antMatchers(HttpMethod.POST, "/games/search").hasRole("SEARCH_GAMES")
                .antMatchers(HttpMethod.DELETE, "/games/**").hasRole("SEARCH_GAMES")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home", true)
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}

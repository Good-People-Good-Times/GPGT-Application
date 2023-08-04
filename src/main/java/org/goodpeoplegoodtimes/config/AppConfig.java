package org.goodpeoplegoodtimes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .formLogin()
                .loginPage("/auth/login").permitAll()
                .loginProcessingUrl("/auth/login/process").permitAll()
                    .usernameParameter("email")
                    .passwordParameter("password")
                .defaultSuccessUrl("/")

            .and()

            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login").permitAll()

            .and()

            .authorizeRequests()
                .antMatchers("/members/**").permitAll()
                .antMatchers("/").permitAll()

            .and()

            .httpBasic().and()
            .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/css/**", "/js/**", "/img/**");
    }

}

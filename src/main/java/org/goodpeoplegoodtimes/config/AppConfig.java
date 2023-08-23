package org.goodpeoplegoodtimes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;


@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Random random() {
        return new Random();
    }

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
                .exceptionHandling()
                    .accessDeniedPage("/auth/login")
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login").permitAll()
            .and()
            .authorizeRequests()
                .antMatchers("/","/party", "/auth/**").permitAll()
                .antMatchers("/notice/create").hasRole("ADMIN")
                .antMatchers("/party/**").authenticated()

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

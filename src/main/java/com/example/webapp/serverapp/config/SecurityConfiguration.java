package com.example.webapp.serverapp.config;

import com.example.webapp.serverapp.filters.JwtFilter;
import com.example.webapp.serverapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

 @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
                .authorizeRequests()
               .antMatchers("/api/v1").permitAll()
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/api/v1/register").permitAll()
                .antMatchers("/api/v1/movie/image").permitAll()
               .antMatchers("/api/v1/movie/genre").permitAll()
               .antMatchers("/api/v1/movie/more/{id}").permitAll()
               .antMatchers("/api/v1/movie/test").permitAll()
               .antMatchers("/api/v1/movie/image/*").permitAll()
                .antMatchers("/api/v1/movie/{id}").permitAll()
               .antMatchers("/api/v1/movie/focus/{id}").permitAll()
               .antMatchers("/api/v1/movie/header").permitAll()
                .antMatchers("/api/v1/movie/upload").permitAll()
               .antMatchers("/api/v1/trailer").permitAll()
               .antMatchers("/api/v1/trailer/segment").permitAll()
               .antMatchers("/actuator").permitAll()
               .antMatchers("/actuator/*").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

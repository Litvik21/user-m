package com.example.accountcontrol.config;

import com.example.accountcontrol.model.Role;
import com.example.accountcontrol.security.JwtConfigurer;
import com.example.accountcontrol.security.JwtTokenProvider;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider provider;

    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder, JwtTokenProvider provider) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.provider = provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.GET,"/users").permitAll()
                .antMatchers(HttpMethod.GET,"/users/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/users/{id}/admin").hasRole(Role.RoleName.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .antMatchers(HttpMethod.PUT,"/users/{id}/edit").hasRole(Role.RoleName.ADMIN.name())
                .anyRequest()
                .authenticated().and()
                .apply(new JwtConfigurer(provider)).and()
                .headers().frameOptions().disable();
    }
}

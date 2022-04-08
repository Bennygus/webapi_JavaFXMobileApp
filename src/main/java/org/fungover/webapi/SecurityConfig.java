package org.fungover.webapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true,
//        jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
      return   http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/products").anonymous()
              .antMatchers(HttpMethod.POST,"/users").anonymous()
                .antMatchers(HttpMethod.POST,"/products").hasRole("ADMIN")
                .anyRequest().authenticated().and().build();



    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().
//                withUser("user").password(passwordEncoder().encode("password")).roles("USER").
//                and()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("USER","ADMIN");
//
//    }
}


















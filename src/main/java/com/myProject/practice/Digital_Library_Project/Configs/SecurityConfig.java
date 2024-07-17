package com.myProject.practice.Digital_Library_Project.Configs;

import com.myProject.practice.Digital_Library_Project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    // Step 1 : Authentications

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    // Step 2 : Autherization

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                    request ->
                            request.requestMatchers(HttpMethod.POST, "/book/**").hasAuthority("add-book")
                                    .requestMatchers("/book/**").hasAuthority("get-book-details")
                                    .requestMatchers("/student/create/**").permitAll()
                                    .requestMatchers("/student/find/**").hasAuthority("get-student-details")
                                    .requestMatchers("/student/**").hasAnyAuthority("update-student-profile", "get-student-profile")
                                    .requestMatchers("/transaction/create/**").hasAuthority("create-transaction")
                                    .requestMatchers("/transaction/get/txn/**").hasAuthority("get-txn-id")
                                    .requestMatchers("/transaction/**").hasAuthority("execute-all-transaction")

                ).formLogin(Customizer.withDefaults())
                .build();

    }

}

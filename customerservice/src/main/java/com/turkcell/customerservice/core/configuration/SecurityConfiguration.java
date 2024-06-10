package com.turkcell.customerservice.core.configuration;

import com.turkcell.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.tcellfinalcore.security.BaseJwtFilter;
import com.turkcell.tcellfinalcore.services.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final IndividualCustomerService customerService;
    private final BaseJwtFilter jwtFilter;
    private final BaseSecurityService baseSecurityService;
    private static final String[] WHITE_LIST = {
            //"/api/v1/individualCustomer/**",
            // "/api/v1/address/**",
            // "/api/v1/contact/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        baseSecurityService.configureCoreSecurity(http);
        http.authorizeHttpRequests((req) -> req.requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers("/api/v1/**").hasAnyAuthority("Kodlama.io").anyRequest().authenticated());
        return http.build();
    }
}

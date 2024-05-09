package com.turkcell.authserver.core.configurations;

import com.turkcell.tcellfinalcore.configuration.BaseApplicationConfiguration;
import com.turkcell.tcellfinalcore.security.BaseJwtFilter;
import com.turkcell.tcellfinalcore.services.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final BaseJwtFilter jwtFilter;
    private final BaseSecurityService baseSecurityService;

    private static final String[] WHITE_LIST={
            "/api/v1/auth/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        baseSecurityService.configureCoreSecurity(http);
        http
                .authorizeHttpRequests((req)-> req
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers(("/api/v1/admin"))
                        .hasAnyAuthority("admin")
                        .anyRequest().authenticated())
                .httpBasic(AbstractHttpConfigurer::disable);
        return http.build();
    }
}

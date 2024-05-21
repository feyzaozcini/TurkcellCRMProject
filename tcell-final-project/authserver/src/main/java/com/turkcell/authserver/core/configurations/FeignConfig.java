package com.turkcell.authserver.core.configurations;

import com.turkcell.authserver.services.abstracts.AuthService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {
    private final AuthService authService;
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String jwtToken = "Bearer " + authService.getToken(); // Bu token'ı dinamik olarak alabilirsiniz
                template.header("Authorization", jwtToken);
            }
        };
    }
}

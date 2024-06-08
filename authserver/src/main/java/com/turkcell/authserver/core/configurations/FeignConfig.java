package com.turkcell.authserver.core.configurations;
import com.turkcell.authserver.services.abstracts.AuthService;
import com.turkcell.authserver.services.abstracts.UserService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@RequiredArgsConstructor
public class FeignConfig {
    private final ApplicationContext applicationContext;
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            UserService userService = applicationContext.getBean(UserService.class);
            String jwtToken = "Bearer " + userService.getToken();
            template.header("Authorization", jwtToken);
        };
    }
}

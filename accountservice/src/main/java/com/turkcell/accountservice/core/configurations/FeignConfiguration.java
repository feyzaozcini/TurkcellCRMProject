package com.turkcell.accountservice.core.configurations;

import com.turkcell.accountservice.services.abstracts.AccountService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfiguration {
    private final ApplicationContext applicationContext;
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            AccountService orderService = applicationContext.getBean(AccountService.class);
            String jwtToken = "Bearer " + orderService.getToken();
            template.header("Authorization", jwtToken);
        };
    }
}

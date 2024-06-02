package com.turkcell.orderservice.core.configurations;

import com.turkcell.orderservice.services.abstracts.OrderService;
import com.turkcell.orderservice.services.dtos.requests.OrderRequest;
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
            OrderService orderService = applicationContext.getBean(OrderService.class);
            String jwtToken = "Bearer " + orderService.getToken();
            template.header("Authorization", jwtToken);
        };
    }
}

package com.turkcell.basketservice;

import com.turkcell.tcellfinalcore.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableSecurity
@EnableFeignClients
public class BasketserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketserviceApplication.class, args);
	}

}

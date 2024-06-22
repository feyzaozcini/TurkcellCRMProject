package com.turkcell.invoiceservice;
import com.turkcell.tcellfinalcore.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@EnableSecurity
public class InvoiceserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InvoiceserviceApplication.class, args);
	}
}

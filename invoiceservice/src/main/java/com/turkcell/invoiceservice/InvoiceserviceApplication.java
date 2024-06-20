package com.turkcell.invoiceservice;

import com.turkcell.common.events.InvoiceEvent;
import com.turkcell.invoiceservice.entities.Invoice;
import com.turkcell.invoiceservice.services.mappers.InvoiceMapper;
import com.turkcell.tcellfinalcore.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableSecurity
public class InvoiceserviceApplication {


	public static void main(String[] args) {
		SpringApplication.run(InvoiceserviceApplication.class, args);
	}

//	@KafkaListener(topics = {"orderTopic"})
//	public void consumeKafkaMessage(InvoiceEvent invoiceEvent){
//		Invoice invoice = InvoiceMapper.INSTANCE.invoiceFromInvoiceEvent(invoiceEvent);
//
//	}
}

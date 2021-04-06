package com.example.invoiceservice.invoiceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableEurekaClient
public class InvoiceserviceApplication {

	/*@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(InvoiceserviceApplication.class, args);
	}

}


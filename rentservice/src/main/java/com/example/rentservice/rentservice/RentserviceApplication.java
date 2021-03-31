package com.example.rentservice.rentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class RentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentserviceApplication.class, args);
	}

}

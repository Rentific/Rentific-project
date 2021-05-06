package com.example.rentservice.rentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
>>>>>>> 18c9e1ddfbfdead1c301a61a13576df62685f594


@EnableEurekaClient
@SpringBootApplication
public class RentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentserviceApplication.class, args);
	}

}

/*
@RefreshScope
@RestController
@RequestMapping("/api/test")
class TestController {

	@Value("${test.name}")
	private String name;

	@GetMapping
	public String test() {
		return name;
	}
}*/

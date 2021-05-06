package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication

public class UserserviceApplication {
<<<<<<< HEAD
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
=======
	//@Bean
	//@LoadBalanced
	/*public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}*/
>>>>>>> 18c9e1ddfbfdead1c301a61a13576df62685f594
	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

}
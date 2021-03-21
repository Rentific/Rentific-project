package com.example.adminservice.adminservice;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AdminserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminserviceApplication.class, args);
	}

}

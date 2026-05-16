package com.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersMicroApplication.class, args);
		System.out.println("Hiii...");
	}
}

package com.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootGraphQLApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraphQLApplication.class, args);
	}

}

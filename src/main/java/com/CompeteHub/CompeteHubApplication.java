package com.CompeteHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class CompeteHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompeteHubApplication.class, args);
	}

	@RequestMapping("/")
	String test() {
		return "TEST";
	}

}

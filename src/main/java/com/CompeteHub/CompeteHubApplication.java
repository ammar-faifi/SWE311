package com.CompeteHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class CompeteHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompeteHubApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> basicsApplicationListener(TodoRepository repository) {
		return event -> repository
				.saveAll(Stream.of("A", "B", "C")
						.map(name -> new Todo("configuration", "congratulations, you have set up correctly!", true))
						.collect(Collectors.toList()))
				.forEach(System.out::println);
	}
}

interface TodoRepository extends JpaRepository<Todo, Long> {

}

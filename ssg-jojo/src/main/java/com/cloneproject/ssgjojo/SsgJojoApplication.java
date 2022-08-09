package com.cloneproject.ssgjojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SsgJojoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsgJojoApplication.class, args);
	}

}

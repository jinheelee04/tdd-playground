package com.jinhee.tdd.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TddPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(TddPlaygroundApplication.class, args);
	}

}

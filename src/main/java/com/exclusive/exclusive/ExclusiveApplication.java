package com.exclusive.exclusive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.exclusive")
public class ExclusiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExclusiveApplication.class, args);
	}

}

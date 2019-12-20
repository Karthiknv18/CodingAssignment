package com.rabobank.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 738575
 *
 */
@SpringBootApplication
@ComponentScan("com.rabobank.*")
public class CustomerStatementProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerStatementProcessorApplication.class, args);
	}

}

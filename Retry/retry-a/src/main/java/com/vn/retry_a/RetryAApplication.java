package com.vn.retry_a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class RetryAApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetryAApplication.class, args);
	}

}

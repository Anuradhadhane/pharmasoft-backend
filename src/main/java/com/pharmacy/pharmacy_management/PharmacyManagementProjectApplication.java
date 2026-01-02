package com.pharmacy.pharmacy_management;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableScheduling
@SpringBootApplication
public class PharmacyManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyManagementProjectApplication.class, args);
	}

}

package com.project.ECommerceApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ECommerceApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "development");
		SpringApplication.run(ECommerceApplication.class, args);
	}

}

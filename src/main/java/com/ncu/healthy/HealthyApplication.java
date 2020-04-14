package com.ncu.healthy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ncu.healthy.mapper")
@SpringBootApplication
public class HealthyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthyApplication.class, args);
	}

}

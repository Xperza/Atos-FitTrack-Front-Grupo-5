package com.atos.fittrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class FittrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FittrackApplication.class, args);
	}

}

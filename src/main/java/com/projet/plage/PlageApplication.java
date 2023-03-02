package com.projet.plage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages = "com.projet.plage")
public class PlageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlageApplication.class, args);
	}
	
//	 @Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//		}


}

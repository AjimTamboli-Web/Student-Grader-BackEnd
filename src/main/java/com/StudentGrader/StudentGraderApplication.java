package com.StudentGrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StudentGraderApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentGraderApplication.class, args);
	}
 
}

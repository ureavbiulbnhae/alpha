package com.example.testh2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Testh2Application {
	
	/**
	notes: 
		-h2 might not work- fix by creating "test.mv.db" file within user directory (C:\Users\Name)
		-no tables in database- check url before log in
		-firefox formats json nicely, in brave it looks like when returned as string (/schools)
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(Testh2Application.class, args);
		
	}
}
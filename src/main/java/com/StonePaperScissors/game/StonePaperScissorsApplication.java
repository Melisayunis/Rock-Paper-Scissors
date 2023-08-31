package com.StonePaperScissors.game;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StonePaperScissorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StonePaperScissorsApplication.class, args);

		System.out.println(" Helloooo! ");
	}

}

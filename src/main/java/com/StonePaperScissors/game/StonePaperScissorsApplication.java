package com.StonePaperScissors.game;

import com.StonePaperScissors.game.Models.Element;
import com.StonePaperScissors.game.Models.User;
import com.StonePaperScissors.game.Service.ElementService;
import com.StonePaperScissors.game.Service.GameService;
import com.StonePaperScissors.game.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class StonePaperScissorsApplication {
	@Autowired
	private ElementService elementService;

	public static void main(String[] args) {
		SpringApplication.run(StonePaperScissorsApplication.class, args);

		System.out.println(" Helloooo! ");
	}


	// con postconstruct se ejecutará automáticamente después de que Spring inicialice los componentes
	@PostConstruct
	public void initialize() {
		elementService.initializeGameElements();
		System.out.println("Elements initialized!");
	}

}

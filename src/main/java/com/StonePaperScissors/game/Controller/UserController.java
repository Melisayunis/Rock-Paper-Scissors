package com.StonePaperScissors.game.Controller;

import com.StonePaperScissors.game.Models.Element;
import com.StonePaperScissors.game.Models.User;
import com.StonePaperScissors.game.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user")
    public ResponseEntity<List<User>> getAllUser() {
        try {
            List<User> getAllUser = userService.getAllUser();
            return ResponseEntity.ok(getAllUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUserID(@PathVariable Long id) {
        try {
            User getUserId = userService.getUserID(id);
            return ResponseEntity.ok(getUserId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<User> deleteUserId(@PathVariable Long id) {
        try {
            userService.deleteUserId(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/user")
    public ResponseEntity<List<User>> deleteAllUser(){
        // toma algo? devuelve una responseEntity de lista de  user'??
        try {
            userService.deleteAllUser();
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/api/register-user")
    public String mostrarPaginaRegistro() {
        return "Register. "; // Retorna el nombre de la vista
    }


    @PostMapping(path = "/api/register-user", consumes = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            userService.saveNewUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/api/register-and-play", consumes = "application/json")
    public ResponseEntity<User> registerAndPlay(@RequestBody User user) {
        try {
            userService.registerUserAndPlay(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/api/register-option")
    @ResponseBody
    public ResponseEntity<String> registerOptionUser(@RequestBody User user) {
        return ResponseEntity.ok("Entroooooo!!! ");
        /*
        try {
            User saveUser = userService.saveNewUser(user);
            return ResponseEntity.ok(saveGame);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        try {
            // Aquí puedes usar el EleccionService para guardar la elección en la base de datos

            // ver si recibo un usuario o solo un integer


            userService.registerUserElementChoice(userPlaying.getUserElementChoice());
            return ResponseEntity.ok("Successfully registered choice. ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register your choice.");
        }*/
    }



/*
    @PostMapping(path = "/api/play-game", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> playGame(@RequestBody Map<String, String> requestBody) {
        String eleccionUsuario = requestBody.get("eleccion");



        // Aquí procesa la lógica del juego y obtén el resultado
        String resultado = ...; // Obtén el resultado del juego

        return ResponseEntity.ok(resultado);
    }*/


}

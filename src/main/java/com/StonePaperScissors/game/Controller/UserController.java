package com.StonePaperScissors.game.Controller;

import com.StonePaperScissors.game.Models.User;
import com.StonePaperScissors.game.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private IUserService iUserService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/api/user")
    public ResponseEntity<List<User>> getAllUser() {
        try {
            List<User> getAllUser = iUserService.getAllUser();
            return ResponseEntity.ok(getAllUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUserID(@PathVariable Long id) {
        try {
            User getUserId = iUserService.getUserID(id);
            return ResponseEntity.ok(getUserId);
        } catch (ResponseStatusException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.notFound().build();
            }
            // manejar mas excepciones ..
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<User> deleteUserId(@PathVariable Long id) {
        try {
            iUserService.deleteUserId(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.notFound().build();
            }
            // averiguar y ver que otras excepciones manejar
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/api/user")
    public ResponseEntity<List<User>> deleteAllUser(){
        // toma algo? devuelve una responseEntity de lista de  user'??
        try {
            iUserService.deleteAllUser();
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/api/register-and-play", consumes = "application/json")
    public ResponseEntity<User> registerAndPlay(@RequestBody User user) {
        try {
            iUserService.registerUserAndPlay(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud", e);
            return ResponseEntity.notFound().build();
        }
    }

//////////----------------------------///////////

    @GetMapping("/api/register-user")
    public String mostrarPaginaRegistro() {
        return "Register. ";
    }


    @PostMapping(path = "/api/register-user", consumes = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            iUserService.saveNewUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }







}

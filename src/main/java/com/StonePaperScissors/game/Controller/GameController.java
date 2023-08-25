package com.StonePaperScissors.game.Controller;

import com.StonePaperScissors.game.Models.Element;
import com.StonePaperScissors.game.Models.Game;
import com.StonePaperScissors.game.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/api/game")
    public ResponseEntity<List<Game>> getAllGame() {
        try {
            List<Game> allGame = gameService.getAllGame();
            return ResponseEntity.ok(allGame);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/game/{id}")
    public ResponseEntity<Game> getGameId(@PathVariable Long id) {
        try {
            Game getGameId = gameService.getGameId(id);
            return ResponseEntity.ok(getGameId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/game/{id}")
    public ResponseEntity<Game> deleteGameId(@PathVariable Long id) {
        try {
            gameService.deleteGameId(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/game")
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        try {
            Game saveGame = gameService.saveNewGame(game);
            return ResponseEntity.ok(saveGame);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

/*
    @PostMapping(path = "/api/play-game", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> playGame(@RequestBody Map<String, String> requestBody) {
        String eleccionUsuario = requestBody.get("eleccion");



        // Aquí procesa la lógica del juego y obtén el resultado
        String resultado = ...; // Obtén el resultado del juego

        return ResponseEntity.ok(resultado);
    }
*/







    /*
    @PutMapping("/api/enrollment/update/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        try {
            Enrollment updateEnrollment = enrollmentService.updateEnrollment(
                    id,
                    enrollment.getStatus(),
                    enrollment.getDate());
            return ResponseEntity.ok(updateEnrollment);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
     */




}

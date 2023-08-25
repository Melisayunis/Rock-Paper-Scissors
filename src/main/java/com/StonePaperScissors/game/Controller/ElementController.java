package com.StonePaperScissors.game.Controller;

import com.StonePaperScissors.game.Models.Element;
import com.StonePaperScissors.game.Service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ElementController {

    @Autowired
    private ElementService elementService;

    @PostMapping("/api/init-game")
    public ResponseEntity<String> initializeGame() {
        elementService.initializeGameElements();
        return ResponseEntity.ok("Game elements initialized.");
    }

    @GetMapping("/api/element")
    public ResponseEntity<List<Element>> getAlElement() {
        try {
            List<Element> allElement = elementService.getAllElement();
            return ResponseEntity.ok(allElement);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/element/{id}")
    public ResponseEntity<Element> getElementId(@PathVariable Long id) {
        try {
            Element getIdElement = elementService.getIdElement(id);
            return ResponseEntity.ok(getIdElement);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

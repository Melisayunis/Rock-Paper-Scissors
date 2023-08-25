package com.StonePaperScissors.game.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game")
    private Long id;

    public Game(){}

    @Column(name = "game_name", nullable = false) // si?
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

}

package com.StonePaperScissors.game.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    public User() {}

    public User(String name){
        this.userName = name;
        this.wonMatches = 0;
        this.userElementChoice = "";
        this.elementChoiceMachine = 0;
        this.typeGame = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "won_matches")
    private Integer wonMatches;

    @Column(name = "user_element_choice")
    private String userElementChoice;

    @Column(name = "element_choice_machine")
    private Integer elementChoiceMachine;

    @Column(name = "type_game")
    private Integer typeGame;
}

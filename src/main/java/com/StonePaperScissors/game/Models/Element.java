package com.StonePaperScissors.game.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "element")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_element")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "wins_against")
    private Integer winsAgainst;

}

package com.StonePaperScissors.game.Models;

public class Element {

    private String nameElement;
    private int numberElement;
    private int winsTo;

    public Element(String name, int numberElement, int winsTo) {
        this.nameElement = name;
        this.numberElement = numberElement;
        this.winsTo = winsTo;
    }

    public String getNameElement() {
        return nameElement;
    }

    public int getNumberElement() {
        return numberElement;
    }

    public int getWinsTo() {
        return winsTo;
    }

}

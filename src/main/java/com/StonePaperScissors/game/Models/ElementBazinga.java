package com.StonePaperScissors.game.Models;

public class ElementBazinga {
    private String nameElement;
    private int numberElement;
    private int[] winsTo;
    private int[] loserTo; // no lo uso, peor lo dejo por si mas adelante es necesario

    public ElementBazinga(String name, int numberElement, int[] winsTo, int[] loserTo) {
        this.nameElement = name;
        this.numberElement = numberElement;
        this.winsTo = winsTo;
        this.loserTo = loserTo;
    }

    public String getNameElement() {
        return nameElement;
    }

    public int getNumberElement() {
        return numberElement;
    }

    public int[] getWinsTo() {
        return winsTo;
    }

    public int[] getLoserTo() { return  loserTo; }
}

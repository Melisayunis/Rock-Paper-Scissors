package com.StonePaperScissors.game.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayNormal {

    private static PlayNormal instance;

    private List<Element> elements;

    private PlayNormal() {
        elements = new ArrayList<>();
        elements.add(new Element("rock", 1, 3));
        elements.add(new Element("paper", 2, 1));
        elements.add(new Element("scissors", 3, 2));
    }

    public static synchronized PlayNormal getInstance() {
        if (instance == null) {
            instance = new PlayNormal();
        }
        return instance;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void play(User user) {

        int machine = playMachine();
        user.setElementChoiceMachine(machine);

        isUserWins(user, machine);
    }

    private void isUserWins(User user, int machine) {
        Element userElement = getUserElement(user.getUserElementChoice());

        if (userElement.getWinsTo() == machine) {
            user.setWonMatches(1);
        } else if (userElement.getNumberElement() == machine) {
            // empate
            user.setWonMatches(-1);
        }
    }

    private Element getUserElement(String nameElementUser) {
        for (Element element : elements) {
            if (element.getNameElement().equals(nameElementUser)) {
                return element;
            }
        }
        return null;
    }

    private Integer playMachine() {
        return generarNumeroAleatorio(1, 3);
    }

    private static int generarNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}

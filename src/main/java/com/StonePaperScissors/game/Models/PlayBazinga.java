package com.StonePaperScissors.game.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayBazinga{

    private static PlayBazinga instance;

    private List<ElementBazinga> elements;

    private PlayBazinga() {
        elements = new ArrayList<>();
        elements.add(new ElementBazinga("rock", 1, new int[]{3, 4}, new int[]{2, 5}));
        elements.add(new ElementBazinga("paper", 2, new int[]{1, 5}, new int[]{3, 4}));
        elements.add(new ElementBazinga("scissors", 3, new int[]{2, 4}, new int[]{1, 5}));
        elements.add(new ElementBazinga("lizard", 4, new int[]{2, 5}, new int[]{1, 3}));
        elements.add(new ElementBazinga("spock", 5, new int[]{1, 3}, new int[]{2, 4}));
    }

    public static synchronized PlayBazinga getInstance() {
        if (instance == null) {
            instance = new PlayBazinga();
        }
        return instance;
    }

    public List<ElementBazinga> getElements() {
        return elements;
    }

    public void play(User user) {
        int machine = playMachine();
        user.setElementChoiceMachine(machine);
        isUserWins(user, machine);
    }

    private void isUserWins(User user, int machine) {
        ElementBazinga userElement = getUserElement(user.getUserElementChoice());

        int[] winsTo = userElement.getWinsTo();

        if (machine == winsTo[0] || machine == winsTo[1]) {
            // gano
            user.setWonMatches(1);
        } else if (machine == userElement.getNumberElement()) {
            // empate
            user.setWonMatches(-1);
        }
    }

    private ElementBazinga getUserElement(String nameElementUser) {
        for (ElementBazinga element : elements) {
            if (element.getNameElement().equals(nameElementUser)) {
                return element;
            }
        }
        return null;
    }

    private Integer playMachine() {
        return generarNumeroAleatorio(1, 5);
    }

    private static int generarNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}

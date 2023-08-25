package com.StonePaperScissors.game.Service;

import com.StonePaperScissors.game.Models.Element;
import com.StonePaperScissors.game.Models.Game;
import com.StonePaperScissors.game.Models.User;
import com.StonePaperScissors.game.Repository.UserRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepositoryPost userRepositoryPost;

    @Autowired
    public UserService(UserRepositoryPost userRepositoryPost) {
        this.userRepositoryPost = userRepositoryPost;
    }

    public List<User> getAllUser() {
        return userRepositoryPost.findAll();
    }

    public User getUserID(Long id) {
        return userRepositoryPost.getById(id);
    }

    public void deleteUserId(Long id) {
        userRepositoryPost.deleteById(id);
    }

    public void deleteAllUser() { userRepositoryPost.deleteAll();} // ver si anda

    public User saveNewUser(User user) {
        return userRepositoryPost.save(user);
    }

    public User registerUserAndPlay(User user) {
        if (userWins(user)) {
            increaseWonMatches(user);
        }
        return userRepositoryPost.save(user);
    }

    public boolean userWins(User player) {
        int machine = playMachine();
        player.setElementChoiceMachine(machine);

        int userWinsTo = getElementWins(player.getUserElementChoice());
        boolean winLosser = (userWinsTo == machine);

        if (getElementUserUse(player.getUserElementChoice()) == machine) {
            // si hay un empate, le asigno -1 a las partidas ganadas
            player.setWonMatches(-1);
        }
        return winLosser;
    }

    private int getElementUserUse(String element) {
        // obtengo el numero del elemento que el usuario esta jugando
        if (element.equals("rock")) {
            return 1;
        } else if (element.equals("paper")) {
            return 2;
        } else if (element.equals("scissors")) {
            return 3;
        }
        System.out.println(" no deberia entrar aca... ");
        return 0;
    }

    private int getElementWins(String element) {
        // le paso el string que ingreso el usuario, y devuelvo el entero que correcponde a quien le gana
        // rock le gana al 3 (scissors)  -- paper al 1 (rock) -- scissors al 2 (paper)

        if (element.equals("rock")) {
            return 3;
        } else if (element.equals("paper")) {
            return 1;
        } else if (element.equals("scissors")) {
            return 2;
        }
        System.out.println(" no deberia entrar aca... ");
        return 0;
    }

    public void increaseWonMatches(User user) {
        user.setWonMatches(user.getWonMatches() + 1);
    }

    public Integer playMachine() {
        return generarNumeroAleatorio(1, 3);
    }

    public static int generarNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}

package com.StonePaperScissors.game.Service;

import com.StonePaperScissors.game.Models.Element;
import com.StonePaperScissors.game.Models.Game;
import com.StonePaperScissors.game.Repository.GameRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private final GameRepositoryPost gameRepositoryPost;

    @Autowired
    public GameService(GameRepositoryPost gameRepositoryPost) {
        this.gameRepositoryPost = gameRepositoryPost;
    }

    public List<Game> getAllGame() {
        return gameRepositoryPost.findAll();
    }

    public Game getGameId(Long id) {
        return gameRepositoryPost.getById(id);
    }

    public void deleteGameId(Long id) {
        gameRepositoryPost.deleteById(id);
    }

    public Game saveNewGame(Game game) { return gameRepositoryPost.save(game); }



    public boolean userWins(Element player1) {
        return (player1.getWinsAgainst() == playMachine());
    }

    public Integer playMachine() {
        return generarNumeroAleatorio(1, 3);
    }

    public static int generarNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


    /* PUT
    public Enrollment updateEnrollment(Long id, String statusEnrollment, Date dateEnrollment){
        Enrollment existingEnrollment = enrollmentRepository.findById(id).orElse(null);

        if (existingEnrollment == null) {
            throw new NoSuchElementException(String.format("Enrollment with ID %s not found", id));
        }
        System.out.println();
        existingEnrollment.setStatus(statusEnrollment == null ? existingEnrollment.getStatus() : statusEnrollment);
        existingEnrollment.setDate(dateEnrollment == null ? existingEnrollment.getDate() : dateEnrollment);
        existingEnrollment.setCourse(existingEnrollment.getCourse());
        existingEnrollment.setStudent(existingEnrollment.getStudent());

        return enrollmentRepository.save(existingEnrollment);
    }

     */

}

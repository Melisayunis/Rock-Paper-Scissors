package com.StonePaperScissors.game.Service;

import com.StonePaperScissors.game.Models.PlayBazinga;
import com.StonePaperScissors.game.Models.PlayNormal;
import com.StonePaperScissors.game.Models.User;
import com.StonePaperScissors.game.Repository.UserRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private PlayNormal playNormal = PlayNormal.getInstance();
    private PlayBazinga playBazinga = PlayBazinga.getInstance();

    private final UserRepositoryPost userRepositoryPost;

    @Autowired
    public UserServiceImpl(UserRepositoryPost userRepositoryPost) {
        this.userRepositoryPost = userRepositoryPost;
    }

    public List<User> getAllUser() {
        return userRepositoryPost.findAll();
    }

    public User getUserID(Long id) {
        Optional<User> userOptional = userRepositoryPost.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteUserId(Long id) {
        Optional<User> userOptional = userRepositoryPost.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userRepositoryPost.delete(userOptional.get());
    }

    public void deleteAllUser() {
        userRepositoryPost.deleteAll();
    } // ver si anda

    public User saveNewUser(User user) {
        return userRepositoryPost.save(user);
    }

    public User registerUserAndPlay(User user) {
        if (user.getTypeGame() == 0) {
            playNormal.play(user);
        } else if (user.getTypeGame() == 1) {
            playBazinga.play(user);
        }
        return userRepositoryPost.save(user);
    }
}

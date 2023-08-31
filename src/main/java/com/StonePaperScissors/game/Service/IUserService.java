package com.StonePaperScissors.game.Service;

import com.StonePaperScissors.game.Models.User;
import java.util.*;

public interface IUserService {

    List<User> getAllUser();

    User getUserID(Long id);

    void deleteUserId(Long id);

    void deleteAllUser();

    User saveNewUser(User user);

    User registerUserAndPlay(User user);


}
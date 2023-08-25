package com.StonePaperScissors.game.Repository;

import com.StonePaperScissors.game.Models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepositoryPost extends JpaRepository<Game, Long> {
}

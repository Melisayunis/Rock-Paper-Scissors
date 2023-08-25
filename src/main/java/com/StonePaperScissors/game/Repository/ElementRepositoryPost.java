package com.StonePaperScissors.game.Repository;

import com.StonePaperScissors.game.Models.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepositoryPost extends JpaRepository<Element, Long> {
}

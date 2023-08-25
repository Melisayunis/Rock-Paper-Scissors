package com.StonePaperScissors.game.Service;

import com.StonePaperScissors.game.Models.Element;
import com.StonePaperScissors.game.Repository.ElementRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ElementService {

    private final ElementRepositoryPost elementRepositoryPost;

    @Autowired
    public ElementService(ElementRepositoryPost elementRepositoryPost) {
        this.elementRepositoryPost = elementRepositoryPost;
    }

    public List<Element> getAllElement() {
        return elementRepositoryPost.findAll();
    }

    public Element getIdElement(Long id) {
        return elementRepositoryPost.getById(id);
    }

    public void initializeGameElements() {
        if (elementRepositoryPost.count() == 0) {
            Element stone = new Element();
            stone.setName("Rock");
            stone.setWinsAgainst(3);
            elementRepositoryPost.save(stone);

            Element paper = new Element();
            paper.setName("Paper");
            paper.setWinsAgainst(1);
            elementRepositoryPost.save(paper);

            Element scissors = new Element();
            scissors.setName("Scissors");
            scissors.setWinsAgainst(2);
            elementRepositoryPost.save(scissors);
        }
    }

}

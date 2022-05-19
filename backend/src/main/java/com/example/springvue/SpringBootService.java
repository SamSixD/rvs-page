package com.example.springvue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringBootService {
    @Autowired
    private SpringBootRepository repository;

    public List<Game> getGame(String name, Integer minPos, Integer maxPos, Integer minScore, Integer maxScore, Integer minId, Integer maxId) {

        return repository.getGame(name, minPos, maxPos, minScore, maxScore, minId, maxId);
    }

    public void addGame(Game game) {
        repository.addGame(game);

    }

    public void deleteGame(Integer id) {
        repository.deleteGame(id);

    }

    public void putGame(Integer id, Game game) {
        repository.putGame(id, game);

    }

}

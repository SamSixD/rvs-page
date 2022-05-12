package com.example.springvue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringBootService {
    @Autowired
    private SpringBootRepository repository;
    public List<Game> find(String name,Integer minPos,Integer maxPos,Integer minScore,Integer maxScore){

        return repository.find(name,minPos,maxPos,minScore,maxScore);
    }
    public void addGame(Game game){
        repository.addGame(game.getName(),game.getPosition(),game.getScore());
    }
}

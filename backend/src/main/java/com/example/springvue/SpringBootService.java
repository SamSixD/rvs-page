package com.example.springvue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringBootService {
    @Autowired
    private SpringBootRepository repository;
    public List<Game> find(){

        return repository.find();
    }
}

package com.example.springvue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
class SpringBootController {

    //    public static void main(String[] args) throws IOException {
//        SpringBootController sbc = new SpringBootController();
//        List<Game> games = sbc.gameFind("Zelda");
//        for (Game game : games) {
//            System.out.println(game.getName());
//        }
//
//    }
    @Autowired
    private SpringBootService service;

    //@GetMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
    //List<Game> games() throws IOException {

      //  return service.find();
   // }

    @GetMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> gameFind(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") Integer minPos,
            @RequestParam(defaultValue = "100") Integer maxPos,
            @RequestParam(defaultValue = "0") Integer minScore,
            @RequestParam(defaultValue = "100") Integer maxScore
    ) throws IOException {

        return service.find(name,minPos,maxPos,minScore,maxScore);
    }

    @PostMapping(value = "/games", consumes = MediaType.APPLICATION_JSON_VALUE)
    void gameCreate(
            @RequestBody Game game)


    {
        service.addGame(game);
        System.out.println(game.toString());
    }
}
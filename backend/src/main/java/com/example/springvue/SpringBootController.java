package com.example.springvue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> games() throws IOException {

        return service.find();
    }

    @GetMapping(value = "/games/search", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> gameFind(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") Integer min,
            @RequestParam(defaultValue = "100") Integer max
    ) throws IOException {
        List<Game> returnValue = new ArrayList<>();
        //

        //String search = "Zelda";

        Path thePath = Paths.get("/Users/sam/IdeaProjects/spring-boot-vue-example/rvs-page/backend/src/main/resources/gamescore.csv");
        List<String> allRows = Files.readAllLines(thePath);
        //Read all rows at once

        for (int i = 0; i < allRows.size(); i++) {
            String row = allRows.get(i);

            Game game = new Game();

            String[] parts = row.split(",");

            try {
                String gameName = parts[1].replaceAll("\"", "");
                if (!gameName.toLowerCase().contains(name.toLowerCase())) {

                    continue;

                }

                String positionWithoutQuotations = parts[0].replaceAll("\"", "");
                int position = Integer.parseInt(positionWithoutQuotations);
                if (position < min || position > max) {
                    continue;
                }


                String scorepo = parts[2].replaceAll("\"", "");

                int score = Integer.parseInt(scorepo);

                game.setName(gameName);
                game.setPosition(position);
                game.setScore(score);

                returnValue.add(game);


            } catch (NumberFormatException e) {
                System.out.println("I HAVE THROWN AN ERROR ON LINE: " + row);

            }
        }

        return returnValue;
    }
}
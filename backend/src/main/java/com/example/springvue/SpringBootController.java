package com.example.springvue;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.sun.javafx.util.Utils.contains;


@RestController
class SpringBootController {

    public static void main(String[] args) throws IOException {
        SpringBootController sbc = new SpringBootController();
        sbc.zeldaFind();
    }

    @GetMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)

    List<Game> games() throws IOException {
        List<Game> returnValue = new ArrayList<>();

        Path thePath = Paths.get("/Users/sam/IdeaProjects/spring-boot-vue-example/rvs-page/backend/src/main/resources/gamescore.csv");
        List<String> allRows = Files.readAllLines(thePath);
        //Read all rows at once

        for (int i = 0; i < allRows.size(); i++) {
            String row = allRows.get(i);

            Game game = new Game();

            String[] parts = row.split(",");

            try {
                String positionWithoutQuotations = parts[0].replaceAll("\"", "");
                String name = parts[1].replaceAll("\"", "");
                String scorepo = parts[2].replaceAll("\"", "");

                int position = Integer.parseInt(positionWithoutQuotations);
                int score = Integer.parseInt(scorepo);

                game.setPosition(position);
                game.setName(name);
                game.setScore(score);

                returnValue.add(game);
            } catch (NumberFormatException e) {
                System.out.println("I HAVE THROWN AN ERROR ON LINE: " + row);
                //continue;
            }
        }

        return returnValue;
    }

    @GetMapping(value = "/games/zelda", produces = MediaType.APPLICATION_JSON_VALUE)

    List<Game> zeldaFind() throws IOException {
        List<Game> returnValue = new ArrayList<>();

        Path thePath = Paths.get("/Users/sam/IdeaProjects/spring-boot-vue-example/rvs-page/backend/src/main/resources/gamescore.csv");
        List<String> allRows = Files.readAllLines(thePath);
        //Read all rows at once

        for (int i = 0; i < allRows.size(); i++) {
            String row = allRows.get(i);

            Game game = new Game();

            String[] parts = row.split(",");

            try {
                String positionWithoutQuotations = parts[0].replaceAll("\"", "");
                String name = parts[1].replaceAll("\"", "");
                String scorepo = parts[2].replaceAll("\"", "");

                int position = Integer.parseInt(positionWithoutQuotations);
                int score = Integer.parseInt(scorepo);

                game.setPosition(position);
                game.setName(name);
                game.setScore(score);

                returnValue.add(game);
                if (contains(name, "Zelda")) {
                    returnValue.add(game);
                }

            } catch (NumberFormatException e) {
                System.out.println("I HAVE THROWN AN ERROR ON LINE: " + row);
                //continue;
            }
        }

        return returnValue;
    }}
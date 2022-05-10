package com.example.springvue;

import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class SpringBootControllerTest {

    SpringBootController sbc = new SpringBootController();

    @Test
    // Test that length of games = 75
    public void testLength() throws IOException {

        List<Game> games = sbc.games();

        int actual = games.size();

        int expected = 75;

        assertEquals("Expected game length to be 75",expected, actual);


    }

    @Test
    // Tests that games are in order
    public void testOrder() throws IOException {
        List<Game> games = sbc.games();

        for (int i = 0; i < 74; i++) {

            int first = games.get(i).getPosition();
            int second = games.get(i+1).getPosition();
            assertEquals("Games are not listed in the correct order",first, second-1);
            //System.out.printf("%d%d", first, second);

        }

    }

    @Test
    // Tests that game names are not null
    public void testNamesNull() throws IOException {
        List<Game> games = sbc.games();

        for (int i = 0; i < 75; i++) {

            String name = games.get(i).getName();

            assertNotNull("Game name is NULL",name);

        }



    }

    @Test
    // Tests that game scores are between 0 and 100
    public void testScoreRange() throws IOException {
        List<Game> games = sbc.games();

        for (int i = 0; i < 75; i++) {

            int score = games.get(i).getScore();


            //System.out.printf("%d", score);
            assertTrue("Games scores are out of range (0-100)",0<= score && score <=100);

        }

    }

    @Test
    // Test that length of games = 75
    public void testSearch() throws IOException {

        List<Game> games = sbc.gameFind("Zelda",0,100);

        int actual = games.size();

        int expected = 8;

        assertEquals("Expected game length to be 8",expected, actual);

    }

    @Test
    // Test that length of games = 75
    public void testGameName() throws IOException {

        List<Game> games = sbc.gameFind("ZeLdA",0,100);

        for (Game game : games) {
            String name = game.getName();
            assertTrue(name.contains("Zelda"));
        }

    }
    @Test
    // Test that length of games = 75
    public void testGameRange() throws IOException {

        List<Game> games = sbc.gameFind("",60,73);

        int actual = games.size();

        int expected = 14;

        assertEquals("Expected game range length to be 14",expected, actual);
        }

    @Test
    // Tests that games are in order
    public void testOrderSet() throws IOException {
        List<Game> games = sbc.gameFind("",0,100);

        for (int i = 1; i < 11; i++) {

            int first = games.get(i).getPosition();
            int second = games.get(i+(2*i)).getPosition();

            //System.out.printf("%d%d", i,"");

            assertTrue("Games are not listed in the correct order",(first<second));
            //System.out.printf("%d%s%d", first, "_", second);

        }

    }





    }







//order check <>
//postgresql set up
//

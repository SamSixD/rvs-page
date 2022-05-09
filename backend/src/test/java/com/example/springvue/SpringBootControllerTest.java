package com.example.springvue;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SpringBootControllerTest {

    SpringBootController sbc = new SpringBootController();

    @Test
    // Test that length of games = 75
    public void testLength() throws IOException {
        List<Game> games = sbc.games();
    }

}

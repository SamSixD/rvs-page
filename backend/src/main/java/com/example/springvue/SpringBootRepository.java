package com.example.springvue;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//database
public class SpringBootRepository {
    private DataSource dataSource;

    public List<Game> find() {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from games");
             ResultSet results = statement.executeQuery();
        ) {

            List<Game> gameList = new ArrayList<>();
            while (results.next()) {
                Game game = new Game();


                game.setPosition(results.getInt("position"));
                game.setName(results.getString("name"));
                game.setScore(results.getInt("score"));

                gameList.add(game);

            }

            return gameList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

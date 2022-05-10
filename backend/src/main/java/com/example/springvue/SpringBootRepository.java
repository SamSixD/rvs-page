package com.example.springvue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//database
@Component
public class SpringBootRepository {
    @Autowired
    private DataSource dataSource;

    public List<Game> find(String name, Integer min, Integer max) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM games WHERE position  >= ? AND position  <= ? AND name LIKE '%'||?||'%' ORDER BY position")) {

            statement.setInt(1, min);
            statement.setInt(2, max);
            statement.setString(3, name);


            try (
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

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

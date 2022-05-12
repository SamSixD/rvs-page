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

    public List<Game> find(String name, Integer minPos, Integer maxPos, Integer minScore, Integer maxScore) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM games WHERE position  >= ? AND " +
                     "position  <= ? AND score  >= ? AND score  <= ? AND name LIKE '%'||?||'%' ORDER BY position")) {

            statement.setInt(1, minPos);
            statement.setInt(2, maxPos);
            statement.setInt(3, minScore);
            statement.setInt(4, maxScore);
            statement.setString(5, name);


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

    public void addGame(String name, Integer pos, Integer score) {


        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO games (position, name, score) VALUES (?, ?, ?);")) {

            statement.setInt(1, pos);
            statement.setString(2, name);
            statement.setInt(3, score);

            statement.executeUpdate();
            //call find method here and return?

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

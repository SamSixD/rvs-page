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

    public List<Game> getGame(String name, Integer minPos, Integer maxPos, Integer minScore, Integer maxScore,Integer minId, Integer maxId) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM games WHERE position  >= ? AND " +
                     "position  <= ? AND score  >= ? AND score  <= ? AND id  >= ? AND id  <= ? AND name LIKE '%'||?||'%' ORDER BY position")) {

            statement.setInt(1, minPos);
            statement.setInt(2, maxPos);
            statement.setInt(3, minScore);
            statement.setInt(4, maxScore);
            statement.setInt(5, minId);
            statement.setInt(6, maxId);
            statement.setString(7, name);


            try (
                    ResultSet results = statement.executeQuery();
            ) {

                List<Game> gameList = new ArrayList<>();
                while (results.next()) {
                    Game game = new Game();


                    game.setPosition(results.getInt("position"));
                    game.setName(results.getString("name"));
                    game.setScore(results.getInt("score"));
                    game.setId(results.getInt("id"));
                    gameList.add(game);

                }

                return gameList;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void addGame(Game game) {

        String name = game.getName();
        Integer pos = game.getPosition();
        Integer score = game.getScore();


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

    public void deleteGame(Integer id) {


        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM games WHERE id =?;")) {

            statement.setInt(1, id);


            statement.executeUpdate();
            //call find method here and return?

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void putGame(Integer id, Game game) {

        String name = game.getName();
        Integer pos = game.getPosition();
        Integer score = game.getScore();


        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE games SET position = ?, name = ?, score = ? WHERE id = ?;")) {

            statement.setInt(1, pos);
            statement.setString(2, name);
            statement.setInt(3, score);
            statement.setInt(4, id);

            statement.executeUpdate();
            //call find method here and return?

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

package com.example.springvue;

public class Game {
    private String name;
    private Integer position;
    private Integer score;

    public Game() {
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", score=" + score +
                '}';
    }


}

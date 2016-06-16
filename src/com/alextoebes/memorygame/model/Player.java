package com.alextoebes.memorygame.model;

import java.util.*;

/**
 * 
 */
public class Player extends Observable{

    private String name;
    private List<CardSet> score;

    public Player(String name) {
        this(name, new ArrayList<>());
    }

    public Player(String name, List<CardSet> score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CardSet> getScore() {
        return score;
    }

    public void setScore(List<CardSet> score) {
        this.score = score;
    }
}
package com.alextoebes.memorygame.model;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * 
 */
public class Game extends Observable {
    private Card[] cards;
    private int cardsInCardSet = 2;
    private int cartSetsInGame = 18;
    private Player[] players;
    private Player turn;

    /**
     * Default constructor
     */
    public Game() {
        this.players = new Player[]{new Player(), new Player()};
        this.cards = Card.createNewGameCards(cartSetsInGame, cardsInCardSet);
        this.cards = shuffle(this.cards);
    }

    private Card[] shuffle(Card[] cards) {
        List<Card> cardsList = Arrays.asList(cards);
        Collections.shuffle(cardsList);
        return (Card[]) cardsList.toArray();
    }

    /**
     * Load Savegame
     */
    public static Game fromSave(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        String saveGame = "";
        while((s = bufferedReader.readLine()) != null) {
            saveGame += s + "\r\n";
        }
        fileReader.close();

        return Game.fromSave(saveGame);
    }

    public static Game fromSave(String fileContent) {

        return Game.Parser.fromString(fileContent).toGame();
    }

    /**
     * @param card1 
     * @param card2 
     * @return
     */
    public boolean makeGuess(Card card1, Card card2) {
        // TODO implement here
        return false;
    }

    /**
     * 
     */
    public void determineFirstTurn() {
        // TODO implement here
    }

    public Card[] getCards() {
        return cards;
    }

    private static class Parser {

        private int[][] gameMap;
        private String player1Name;
        private String player2Name;
        private int[] player1Score;
        private int[] player2Score;
        private int turn;

        private Parser() {
        }

        private static Parser fromString(String fileContent) {

            Parser parser = new Parser();

            String gameMapRaw = "";

            String[] lines = fileContent.split("\\r?\\n");

            for (int i = lines.length-1; i >= 0; i--) {
                int reverseIndex = lines.length-(i+1);

                switch (reverseIndex) {
                    case 0:
                        // Player Turn
                        parser.turn = Integer.parseInt(lines[i]);
                        break;
                    case 1:
                        // Player 2 Score
                        parser.player2Score = Parser.parseScore(lines[i]);
                        break;
                    case 2:
                        // Player 2 Name
                        parser.player2Name = lines[i];
                        break;
                    case 3:
                        // Player 1 Score
                        parser.player1Score = Parser.parseScore(lines[i]);
                        break;
                    case 4:
                        // Player 1 Name
                        parser.player1Name = lines[i];
                        break;
                    default:
                        // Game Board data
                        gameMapRaw = lines[i] + "\r\n" + gameMapRaw;
                }
            }

            parser.gameMap = Parser.parseGameMap(gameMapRaw);
            return null;
        }

        private static int[] parseGameMap(String gameMapRaw) {
            String lines[] = gameMapRaw.split("\\r?\\n");
            
            for (String line : lines) {
                line.split(" ");
            }
        }

        private static String parseGameMap(int[] gameMap) {

        }

        public String toString() {
            return "";
        }

        private static Parser fromGame() {

        }

        public Game toGame() {
            return new Game();
        }

        private static int[] parseScore(String score) {
            return new int[];
        }

        private static String parseScore(int[] score) {
            return "";
        }
    }
}
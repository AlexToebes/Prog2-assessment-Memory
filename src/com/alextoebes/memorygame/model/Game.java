package com.alextoebes.memorygame.model;

import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 
 */
public class Game extends Observable {
    private static final int DEFAULT_CARDS_IN_CARDSET = 2;
    private static final int DEFAULT_CARDSETS_IN_GAME = 18;

    private Card[] cards;
    private Player[] players;
    private Player turn;

    /**
     * Default constructor
     */
    public Game() {
        this(
                Card.createNewGameCards(DEFAULT_CARDSETS_IN_GAME, DEFAULT_CARDS_IN_CARDSET),
                new Player[]{new Player("Player 1"), new Player("Player 2")},
                null
        );

        this.shuffle();
        this.randomizeTurn();
    }

    public Game(Card[] cards, Player[] players, Player turn) {
        this.cards = cards;
        this.players = players;
        this.turn = turn;
    }

    private void shuffle() {
        List<Card> cardsList = Arrays.asList(this.cards);
        Collections.shuffle(cardsList);
        this.cards = (Card[]) cardsList.toArray();
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

    public void randomizeTurn() {
        this.turn = this.players[(new Random()).nextInt(this.players.length)];
    }

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
        private List<Integer> player1Score;
        private List<Integer> player2Score;
        private int turn;

        private Parser() {
        }

        private static Parser fromString(String fileContent) {

            Parser parser = new Parser();

            String gameMapRaw = "";

            String[] lines = fileContent.split("\\r?\\n");

            for (int i = lines.length-1; i >= 0; i--) {
                switch (lines.length-(i+1)) {
                    case 0:
                        // Player Turn
                        parser.turn = Integer.parseInt(lines[i].trim());
                        break;
                    case 1:
                        // Player 2 Score
                        parser.player2Score = Parser.parseScore(lines[i].trim());
                        break;
                    case 2:
                        // Player 2 Name
                        parser.player2Name = lines[i];
                        break;
                    case 3:
                        // Player 1 Score
                        parser.player1Score = Parser.parseScore(lines[i].trim());
                        break;
                    case 4:
                        // Player 1 Name
                        parser.player1Name = lines[i].trim();
                        break;
                    default:
                        // Game Board data
                        gameMapRaw = lines[i].trim() + "\r\n" + gameMapRaw;
                }
            }

            parser.gameMap = Parser.parseGameMap(gameMapRaw);
            return parser;
        }

        private static int[][] parseGameMap(String gameMapRaw) {

            String[] lines = gameMapRaw.split("\\r?\\n");
            int[][] map = new int[lines.length][];
            for (int i = 0; i < lines.length; i++) {

                String[] line = lines[i].split(" ");
                int[] row = new int[line.length];
                for (int i1 = 0; i1 < line.length; i1++) {
                    row[i1] = Integer.parseInt(line[i1]);
                }
                map[i] = row;
            }

            return map;
        }

        private static String parseGameMap(int[] gameMap) {
            // TODO: 16-6-2016 Implement
            return "";
        }

        public String toString() {
            // TODO: 16-6-2016 Implement
            return "";
        }

        private static Parser fromGame() {
            // TODO: 16-6-2016 Implement
            return new Parser();
        }

        public Game toGame() {

            /**
             * Convert Game board
             */

            int longestRowAmount = 0;
            List<Card> guessedCards = new ArrayList<>();
            Map<Integer, CardSet> cardSets = new HashMap<>();
            Map<Integer, Integer> cardSetsAppliedCounter = new HashMap<>();

            List<Integer> combinedPlayerScore = new ArrayList<>();
            combinedPlayerScore.addAll(this.player1Score);
            combinedPlayerScore.addAll(this.player2Score);

            for (int combinedPlayerScoreValue : combinedPlayerScore) {
                CardSet cardSet = CardSet.createNewCardSetWithCards(Integer.toString(combinedPlayerScoreValue), DEFAULT_CARDS_IN_CARDSET);
                cardSets.put(combinedPlayerScoreValue, cardSet);
                cardSetsAppliedCounter.put(combinedPlayerScoreValue, 0);
                Collections.addAll(guessedCards, cardSet.getCards());
            }
            
            for (int[] gameMapRow : this.gameMap) {
                if (longestRowAmount < gameMapRow.length) longestRowAmount = gameMapRow.length;
                for (int gameMapCell : gameMapRow) {
                    if (gameMapCell > 0) {
                        cardSets.put(gameMapCell, CardSet.createNewCardSetWithCards(Integer.toString(gameMapCell), DEFAULT_CARDS_IN_CARDSET));
                        cardSetsAppliedCounter.put(gameMapCell, 0);
                    }
                }
            }

            Card[] cards = new Card[(this.gameMap.length * longestRowAmount)];

            int cardsIndex = 0;

            for (int i = 0; i < this.gameMap.length; i++) {
                for (int i1 = 0; i1 < this.gameMap[i].length; i1++) {
                    if (this.gameMap[i][i1] < 0) {
                        cards[cardsIndex++] = guessedCards.get(0);
                        guessedCards.remove(cards[i]);
                    } else {
                        cards[cardsIndex++] = cardSets.get(
                                this.gameMap[i][i1]
                        ).getCards()
                                [
                                cardSetsAppliedCounter.get(
                                        this.gameMap[i][i1]
                                )
                                ];
                    }
                }
            }

            /**
             * Convert players
             */
            Player[] players = new Player[]{
                    new Player(player1Name, player1Score.stream().map(cardSets::get).collect(Collectors.toList())),
                    new Player(player2Name, player2Score.stream().map(cardSets::get).collect(Collectors.toList()))
            };
            Player turn = players[this.turn];

            /**
             * Return Game Object
             */

            return new Game(cards, players, turn);
        }

        private static List<Integer> parseScore(String scoreString) {
            String[] scoreStrings = scoreString.split(" ");
            List<Integer> score = new ArrayList<>();
            for (String scoreString1 : scoreStrings) {
                if (!scoreString1.isEmpty()) {
                    score.add(Integer.parseInt(scoreString1));
                }
            }
            return score;
        }

        private static String parseScore(List<Integer> score) {
            String scoreString = "";
            for (int scoreValue : score) {
                if (!scoreString.equals("")) {
                    scoreString += " ";
                }
                scoreString += scoreValue;
            }
            return scoreString;
        }
    }
}
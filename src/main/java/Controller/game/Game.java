package Controller.game;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Scanner;

import Controller.CardToCardConvertor;
import Controller.PreGameMenu;
import Model.*;
import Veiw.Out;

public class Game {
    private Player player1, player2;
    private GameBoard gameBoard;
    private UIManager uiManager;
    private CoinManager coinManager;
    private int currentRound;

    public Game() {
        initializePlayers();
        gameBoard = new GameBoard(player1, player2);
        uiManager = new UIManager(this);
        coinManager = new CoinManager(player1, player2);
        currentRound = 1;
    }

    public Game(User user1, User user2) {
        initializePlayers(user1, user2);
        gameBoard = new GameBoard(player1, player2);
        uiManager = new UIManager(this);
        coinManager = new CoinManager(player1, player2);
        currentRound = 1;
    }

    public void start() {
        while (!isGameOver()) {
            playRound();
        }
        endGame();
    }

    private void initializePlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player 1 username: ");
        String player1Name = scanner.nextLine();
        System.out.println("Enter player 2 username: ");
        String player2Name = scanner.nextLine();
        player1 = new Player(player1Name, LoadPlayerAvailableCardsFFromFile(player1Name));
        player2 = new Player(player2Name, LoadPlayerAvailableCardsFFromFile(player2Name));
        this.player1 = player1;
        this.player2 = player2;
        this.player1.setGame(this);
        this.player2.setGame(this);
        this.gameBoard = new GameBoard(player1, player2);
        this.coinManager = new CoinManager(player1, player2);
        this.uiManager = new UIManager(this);
    }

    private void initializePlayers(User user1, User user2) {

        this.player1 = new Player(user1);
        this.player2 = new Player(user2);
        this.player1.setGame(this);
        this.player2.setGame(this);
        this.gameBoard = new GameBoard(player1, player2);
        this.coinManager = new CoinManager(player1, player2);
        this.uiManager = new UIManager(this);
    }

    private ArrayList<Card> LoadPlayerAvailableCardsFFromFile(String characterName) {
        if (characterName.equals("1")) {
            ArrayList<Card> cards = new ArrayList<>();
            GsonHandler gsonHandler = new GsonHandler();
            cards = CardToCardConvertor.convertCardModelListToCardList(gsonHandler.readCardJSON());
            return cards;

        } else {
            ArrayList<Card> cards = new ArrayList<>();
            GsonHandler gsonHandler = new GsonHandler();
            cards = CardToCardConvertor.convertCardModelListToCardList(gsonHandler.readCardJSON());
            return cards;
        }

    }

    private boolean isGameOver() {
        return player1.getHealth() <= 0 || player2.getHealth() <= 0;
    }

    private void endGame() {
        User user1 = Data.getLoggedInUser1();
        User user2 = Data.getLoggedInUser2();
        String date = LocalDateTime.now().toString();

        if (player1.getHealth() <= 0) {
            user1.addHistory(new DataHistory(date, Integer.toString(user2.getLevel()), user2.getUsername(), "loose", Integer.toString(-1 * PreGameMenu.gambleGolds)));
            user2.addHistory(new DataHistory(date, Integer.toString(user1.getLevel()), user1.getUsername(), "win", Integer.toString(PreGameMenu.gambleGolds)));

            user1.setGold(user1.getGold() - PreGameMenu.gambleGolds);
            user2.setGold(user2.getGold() + PreGameMenu.gambleGolds);

            user2.setXp(user2.getXp() + 45);

            Out.print(player2.getName() + " wins!");
            if (PreGameMenu.gambleGolds > 0)
                Out.print(player2.getName() + " get " + PreGameMenu.gambleGolds + "gold and " + player1.getName() + "loose " + PreGameMenu.gambleGolds + " gold");

        } else {
            user1.addHistory(new DataHistory(date, Integer.toString(user2.getLevel()), user2.getUsername(), "win", Integer.toString(-1 * PreGameMenu.gambleGolds)));
            user2.addHistory(new DataHistory(date, Integer.toString(user1.getLevel()), user1.getUsername(), "loose", Integer.toString(PreGameMenu.gambleGolds)));

            user1.setGold(user1.getGold() + PreGameMenu.gambleGolds);
            user2.setGold(user2.getGold() - PreGameMenu.gambleGolds);

            user1.setXp(user1.getXp() + 45);

            Out.print(player1.getName() + " wins!");
            if (PreGameMenu.gambleGolds > 0)
                Out.print(player1.getName() + " get " + PreGameMenu.gambleGolds + "gold and " + player2.getName() + "loose " + PreGameMenu.gambleGolds + " gold");

        }

    }

    private void playRound() {

        uiManager.displayRound(currentRound);
        System.out.println("-----------------------");
        System.out.println("current round: " + currentRound);
        System.out.println("-----------------------");

        System.out.println("\n-----------------------");
        player1.drawCard();
        player2.drawCard();
        player1.playTurn(gameBoard, coinManager);
        player2.playTurn(gameBoard, coinManager);
        if (currentRound >= 3) {
            endOfTheRound();
        }
        currentRound++;
    }

    private void endOfTheRound() {
        System.out.println("End of round!");
        endOfTurn(player1, player2, gameBoard);
        currentRound = 0;
    }

    public void endOfTurn(Player player1, Player player2, GameBoard gameBoard) {
        gameBoard.getBoard(player1).forEach(card -> {
            if (card == null) {
            } else {
                Card player2card = gameBoard.getOpossingCard(player1, player2, card);
                if (player2card == null) {
                    player2.setHealth(player2.getHealth() - card.getDamage());
//                    System.out.println(player2.getHealth() + " " + card.getDamage());
                } else {
                    player2card.setDefense(player2card.getDefense() - card.getDamage());
                    if (player2card.getDefense() <= 0) {
                        gameBoard.getBoard(player2).remove(player2card);
                        player2.setHealth(player2.getHealth() - card.getDamage() + player2card.getDefense());
                        player1.addACardToHand();
                        //bonus for killing enemy card
//                        System.out.println(player2.getHealth() + " " + card.getDamage() + " " + player2card.getDefense()  );
                    }
                }
            }
        });
        gameBoard.getBoard(player2).forEach(card -> {
            if (card == null) {
            } else {
                Card player1card = gameBoard.getOpossingCard(player2, player1, card);
                if (player1card == null) {
                    player1.setHealth(player1.getHealth() - card.getDamage());
//                    System.out.println(player1.getHealth() + " " + card.getDamage());
                } else {
                    player1card.setDefense(player1card.getDefense() - card.getDamage());
                    if (player1card.getDefense() <= 0) {
                        gameBoard.getBoard(player1).remove(player1card);
                        player1.setHealth(player1.getHealth() - card.getDamage() + player1card.getDefense());
                        player2.addACardToHand();

//                        System.out.println(player1.getHealth() + " " + card.getDamage() + " " + player1card.getDefense()  );
                    }
                }
            }
        });
        if (player1.getRoundPoisened() > 0) {
            player1.setHealth(player1.getHealth() - 5);
            player1.setRoundPoisened(player1.getRoundPoisened() - 1);
        }
        if (player2.getRoundPoisened() > 0) {
            player2.setHealth(player2.getHealth() - 5);
            player2.setRoundPoisened(player2.getRoundPoisened() - 1);
        }
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }
}

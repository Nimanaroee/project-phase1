package Model;

import Controller.game.Card;

import java.util.ArrayList;

public class User {
    private String username, nickname, password, email, question, answer;
    private int Level = 1;
    private int xp = 0;
    private int gold = 0; ///// set value of gold
    private ArrayList<CardModel> cardModels;
    private ArrayList<DataHistory> histories;

    private int character = 1;

    public User(String username, String password, String email, String nickname) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setNickname(nickname);
        cardModels = new ArrayList<CardModel>();
        histories = new ArrayList<>();
    }

    public User(String data) {
    }

//    public User(String username, String password, String email, int score, int coins, Card cards, String nickname, String question, String answer) {
//        setUsername(username);
//        setPassword(password);
//        setEmail(email);
//        setXp(score);
//        setGold(coins);
//        addCard(cards);
//    }

    public void addCard(CardModel cardModel) {
        this.cardModels.add(cardModel);
        Data.updateUser(this);
    }

    public void addHistory(DataHistory history) {
        this.histories.add(history);
        Data.updateUser(this);
    }

    ///////// after game update //////////
    public void updateLevel() {
    }

    public void updateXp() {
    }

    public void updateHp() {
    }

    public void updateGold() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        Data.updateUser(this);
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        Data.updateUser(this);
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        Data.updateUser(this);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
        Data.updateUser(this);
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
        Data.updateUser(this);
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        Data.updateUser(this);
    }

    public int getLevel() {
        return this.Level;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int coins) {
        this.gold = coins;
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int score) {
        this.xp = score;
        if (this.xp > 100)
            this.setLevel();
    }

    public void setLevel() {
        this.Level++;
        this.setGold(this.getGold() + 250);
    }

    public ArrayList<CardModel> getCards() {
        return this.cardModels;
    }

    public String getXP() {
        return String.valueOf(this.xp);
    }

    public String getCoins() {
        return String.valueOf(this.gold);
    }

    public ArrayList<DataHistory> getHistories() {
        return this.histories;
    }

    public CardModel getCardByName(String name) {
        for (CardModel cardModel : this.cardModels) {
            if (cardModel.getName().equals(name))
                return cardModel;
        }
        return null;
    }


    public ArrayList<CardModel> getAvailableCards() {
        GsonHandler gsonHandler = new GsonHandler();
        Integer Character = getCharacter();
        ArrayList<CardModel> cards = gsonHandler.readCardJSON();
        cards.forEach(cardModel -> {
            if (cardModel.getName().contains(Character.toString())) {
                cardModels.add(cardModel);
            }
        });

        return cardModels;
    }

    public void setCardModels(ArrayList<CardModel> cardModels) {
        this.cardModels = cardModels;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public void setCardByName(String name, CardModel card) {
        for (CardModel cardModel : this.cardModels) {
            if (cardModel != null) {
                if (cardModel.getName().equals(name))
                    cardModel = card;
            }
        }
    }
}

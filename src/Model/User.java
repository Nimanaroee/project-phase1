package Model;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.logging.Level;

public class User {
    private String username, nickname, password, email, question, answer;
    private  int Level=1;
    private  int hp = 100;
    private  int xp = 0;
    private  int gold = 0; ///// set value of goldddd;lsfk;slfk;a
    private ArrayList<Card> cards;

    public User(String username, String password, String email, String nickname) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setNickname(nickname);
        cards = new ArrayList<Card>();
    }

    public User(String data) {


    }

    public User(String username, String password, String email, int score, int coins, Card cards, String nickname, String question, String answer) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setXp(score);
        setGold(coins);
        addCard(cards);
    }

    public void setGold(int coins) {
        this.gold = coins;
    }
    private void setXp(int score) {
        this.xp = score;
    }

    public void setUsername(String username) { this.username = username; Data.updateUser(this); }
    public void setNickname(String nickname) { this.nickname = nickname; Data.updateUser(this); }
    public void setPassword(String password) { this.password = password; Data.updateUser(this); }
    public void setEmail(String email) { this.email = email; Data.updateUser(this); }
    public void setQuestion(String question) { this.question = question; Data.updateUser(this); }
    public void setAnswer(String answer) { this.answer = answer; Data.updateUser(this); }
    public void addCard(Card card) { this.cards.add(card); Data.updateUser(this);}

    ///////// after game update //////////
    public void updateLevel() {}
    public void updateXp() {}
    public void updateHp() {}
    public void updateGold() {}
    public String getUsername() {
        return this.username;
    }
    public String getNickname() {
        return this.nickname;
    }
    public String getPassword() {
        return this.password;
    }
    public String getEmail() { return this.email; }
    public String getQuestion() { return this.question; }
    public String getAnswer() { return this.answer; }
    public int getLevel() { return this.Level; }
    public int getGold() { return this.gold; }
    public int getHp() { return this.hp; }
    public int getXp() { return this.xp; }
    public ArrayList<Card> getCards() { return this.cards; }
    public Card getCardByName(String name) {
        for(Card card : this.cards) {
            if(card.getName().equals(name))
                return card;
        }
        return null;
    }

    public String getXP() {
        return String.valueOf(this.xp);
    }

    public String getCoins() {
        return String.valueOf(this.gold);
    }

}

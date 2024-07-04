package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;

public class GsonHandler {
    private Gson gsonUser;
    private Gson gsonCard;

    public void readUserJSON() {
        GsonBuilder builder = new GsonBuilder();
        gsonUser = builder.create();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/users.json"));
            ArrayList<User> users = new ArrayList<>();
            String line = null;
            while (true) {
                line = bufferedReader.readLine();
                if(line == null)
                    break;
                users.add(gsonUser.fromJson(line, User.class));
            }
            Data.setAllUsers(users);
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void saveUserGson() {
        GsonBuilder builder = new GsonBuilder();
        gsonUser = builder.create();
        try {
            FileWriter writer = new FileWriter("src/main/resources/users.json");
            writer.flush();
            ArrayList<User> users = Data.getAllUser();
            for(User user : users)
                writer.write(gsonUser.toJson(user));
            writer.close();
        } catch (IOException e) {e.printStackTrace();}

    }
    public void readCardJSON() {
        GsonBuilder builder = new GsonBuilder();
        gsonCard = builder.create();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/cards.json"));
            ArrayList<Card> cards = new ArrayList<>();
            String line = null;
            while (true) {
                line = bufferedReader.readLine();
                if(line == null)
                    break;
                cards.add(gsonCard.fromJson(line, Card.class));
            }
            Data.setAllCards(cards);
        } catch (IOException e ) { e.printStackTrace();}
    }
    public void saveCardGson() {
        GsonBuilder builder = new GsonBuilder();
        gsonCard = builder.create();
        try {
            FileWriter writer = new FileWriter("src/main/resources/cards.json");
            ArrayList<Card> cards = Data.getAllCards();
            for(Card card : cards)
                writer.write(gsonCard.toJson(card));
            writer.close();
        } catch (IOException e) { e.printStackTrace(); };
    }
}

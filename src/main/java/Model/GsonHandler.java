package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

public class GsonHandler {
    private Gson gsonUser;
    private Gson gsonCard;
    private Gson gson = new Gson();

//    public void readUserJSON() {
//        GsonBuilder builder = new GsonBuilder();
//        gsonUser = builder.create();
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/users.json"));
//            ArrayList<User> users = new ArrayList<>();
//            String line = null;
//            while (true) {
//                line = bufferedReader.readLine();
//                if (line == null)
//                    break;
//                users.add(gsonUser.fromJson(line, User.class));
//            }
//            Data.setAllUsers(users);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public ArrayList<User> readUserJSON() {
        File file = new File("src/main/resources/users.json");
        ArrayList<User> users = new ArrayList<>();
        // Check if file exists and is not empty
        if (file.exists() && file.length() != 0) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                Type userListType = new TypeToken<ArrayList<User>>() {
                }.getType();
                users = gson.fromJson(bufferedReader, userListType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Handle the case where the file is empty or doesn't exist
            System.out.println("File is empty or does not exist. Returning an empty list.");
        }
        return users;
    }

    public void saveUserGson() {
        GsonBuilder builder = new GsonBuilder();
        gsonUser = builder.create();
        try {
            FileWriter writer = new FileWriter("src/main/resources/users.json");
            BufferedWriter out = new BufferedWriter(writer);
            writer.flush();
            ArrayList<User> users = Data.getAllUser();
            for (User user : users) {
                out.write(gsonUser.toJson(user));
                out.newLine();
            }
            out.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<CardModel> readCardJSON() {
        GsonBuilder builder = new GsonBuilder();
        gsonCard = builder.create();
        ArrayList<CardModel> cardModels = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/cards.json"));
            Type cardModelListType = new TypeToken<ArrayList<CardModel>>() {
            }.getType();
            cardModels = gsonCard.fromJson(bufferedReader, cardModelListType);
            Data.setAllCards(cardModels);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cardModels;
    }

    public void saveCardGson() {
        GsonBuilder builder = new GsonBuilder();
        gsonCard = builder.create();
        try {
            FileWriter writer = new FileWriter("src/main/resources/cards.json");
            BufferedWriter out = new BufferedWriter(writer);
            ArrayList<CardModel> cardModels = Data.getAllCards();
            for (CardModel cardModel : cardModels) {
                out.write(gsonCard.toJson(cardModel));
                out.newLine();
            }
            out.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }
}

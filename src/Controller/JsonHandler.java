package Controller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Model.User;
import Model.Card;
import Model.DataHistory;
import java.io.FileWriter;
import java.io.IOException;




public class JsonHandler {
    public void WriteToJsonUser(User user) {
        JSONObject obj = new JSONObject();
        obj.put("username", user.getUsername());
        obj.put("nickname", user.getNickname());
        obj.put("password", user.getPassword());
        obj.put("email", user.getEmail());
        obj.put("question", user.getQuestion());
        obj.put("answer", user.getAnswer());
        obj.put("level", user.getLevel());
        obj.put("hp", user.getHp());
        obj.put("xp", user.getXp());
        obj.put("gold", user.getGold());
        JSONArray cards = new JSONArray();
        for (int i = 0; i < user.getCards().size(); i++) {
            JSONObject card = new JSONObject();
            card.put("name", user.getCards().get(i).getName());
            card.put("attack", user.getCards().get(i).getAttack());
            card.put("duration", user.getCards().get(i).getDuration());
            card.put("damage", user.getCards().get(i).getDamage());
            card.put("upgradeLevel", user.getCards().get(i).getUpgradeLevel());
            card.put("upgradeCost", user.getCards().get(i).getUpgradeCost());
            card.put("price", user.getCards().get(i).getPrice());
            cards.put(card);
        }
        obj.put("cards", cards);
        // write to file
        try (FileWriter file = new FileWriter("user.json")) {
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public User GetUserFromJson(String username) {
        JSONObject obj = new JSONObject();
        try {
            obj = new JSONObject("user.json");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        User user = new User(obj.getString("username"), obj.getString("password"), obj.getString("email"), obj.getInt("xp"), obj.getInt("gold"), new Card(obj.getString("cards")), obj.getString("nickname"), obj.getString("question"), obj.getString("answer"));
        return user;
    }

    public void WriteToJsonHistory(DataHistory dataHistory) {
        JSONObject obj = new JSONObject();
        obj.put("date", dataHistory.getDate());
        obj.put("resualt", dataHistory.getResualt());
        obj.put("opponentName", dataHistory.getOpponentName());
        obj.put("opponentLevel", dataHistory.getOpponentLevel());
        obj.put("prize", dataHistory.getPrize());
        // write to file
        try (FileWriter file = new FileWriter("history.json")) {
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataHistory GetHistoryFromJson() {
        JSONObject obj = new JSONObject();
        try {
            obj = new JSONObject("history.json");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DataHistory dataHistory = new DataHistory();
        dataHistory.setDate(obj.getString("date"));
        dataHistory.setResult(obj.getString("resualt"));
        dataHistory.setOpponentName(obj.getString("opponentName"));
        dataHistory.setOpponentLevel(obj.getString("opponentLevel"));
        dataHistory.setPrize(obj.getString("prize"));
        return dataHistory;
    }

    public void WriteToJsonCard(Card card) {
        JSONObject obj = new JSONObject();
        obj.put("name", card.getName());
        obj.put("attack", card.getAttack());
        obj.put("duration", card.getDuration());
        obj.put("damage", card.getDamage());
        obj.put("upgradeLevel", card.getUpgradeLevel());
        obj.put("upgradeCost", card.getUpgradeCost());
        obj.put("price", card.getPrice());
        // write to file
        try (FileWriter file = new FileWriter("card.json")) {
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

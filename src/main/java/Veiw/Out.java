package Veiw;

import Model.CardModel;
import Model.User;

public class Out {
    public static void print(String output) {
        System.out.println(output);
    }

    public static void showInfoOfUser(User user) {
        System.out.println("-------------------------------------------------");
        System.out.println("username : " + user.getUsername());
        System.out.println("nickname : " + user.getNickname());
        System.out.println("password : " + user.getPassword());
        System.out.println("email    : " + user.getEmail());
        System.out.println("-------------------------------------------------");
    }

    public static void askQuestion() {
        System.out.println("-------------------------------------------------");
        System.out.println("User created successfully. Please choose a security question :");
        System.out.println("• 1-What is your father’s name ?");
        System.out.println("• 2-What is your favourite color ?");
        System.out.println("• 3-What was the name of your first pet?");
        System.out.println("-------------------------------------------------");
    }

    public static void showInfoOfCard(CardModel cardModel) {
        System.out.println("-------------------------------------------------");
        System.out.println("name : " + cardModel.getName());
        System.out.println("attack/defence : " + cardModel.getDefence());
        System.out.println("duration : " + cardModel.getDuration());
        System.out.println("player damage : " + cardModel.getDamage());
        System.out.println("upgrade level : " + cardModel.getUpgradeLevel());
        System.out.println("upgrade coast : " + cardModel.getUpgradeCost());
        System.out.println("-------------------------------------------------");
    }
}

package Controller;

import Model.*;
import Veiw.Out;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu extends Menu {
    public ShopMenu(Scanner scanner, String menuName) {
        super(scanner, menuName, "back");
        addCommand(Regex.SHOP_SHOW_ALL_CARDS, this::showAllCard);
        addCommand(Regex.SHOP_BUY_CARD, this::buyCard);
        addCommand(Regex.SHOP_UPGRADE_CARD, this::upgradeCard);
    }

    private void showAllCard(Matcher matcher) {
        ArrayList<CardModel> cardModels = Data.getAllCards();
        for (CardModel cardModel : cardModels) {
            Out.showInfoOfCard(cardModel);
        }
    }

    private void buyCard(Matcher matcher) {
        CardModel cardModel = Data.getCardByCardName(matcher.group("name"));
        if (cardModel == null) {
            Out.print("wrong card name !");
            return;
        }
        if (Data.getLoggedInUser1().getGold() < cardModel.getPrice()) {
            Out.print("not enouph money!");
            return;
        }
        User user = Data.getLoggedInUser1();
        user.addCard(cardModel);
        Out.print("card " + cardModel.getName() + " bought successfully!");
    }

    private void upgradeCard(Matcher matcher) {
        CardModel cardModel = Data.getLoggedInUser1().getCardByName(matcher.group("name"));
        if (cardModel == null) {
            Out.print("wrong card name !");
            return;
        }
        if (Data.getLoggedInUser1().getGold() < cardModel.getUpgradeCost()) {
            Out.print("not enouph money!");
            return;
        }
        cardModel.upgrade();
        Data.getLoggedInUser1().setCardByName(matcher.group("name"), cardModel);
    }
}
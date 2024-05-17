package Controller;

import Model.Card;
import Model.Menu;
import Model.Regex;
import Model.User;
import Veiw.Out;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Mainmenu extends Menu {
    User user;
    public Mainmenu(Scanner scanner, User user) {
        super(scanner, "Main Menu", "logout");
        this.user = user;
        addCommand(Regex.START_GAME, this::startGame);
        addCommand(Regex.SHOW_CARDS, this::showCards);
        addCommand(Regex.SHOW_HISTORY, this::showHistory);
        addCommand(Regex.ENTER_SHOPMENU, this::enterShopMenu);
        addCommand(Regex.ENTER_PROFILEMENU, this::enterProfileMenu);
        /// add commands
    }
    private void startGame(Matcher matcher) {
        new Gamemenu(scan, "Game Menu", user).run();
    }
    private void enterShopMenu(Matcher matcher) {
        new Shopmenu(scan, "Shop Menu", user).run();
    }
    private void enterProfileMenu(Matcher matcher) {
        new Profilemenu(scan, "Profile Menu", user).run();
    }
    private void showCards(Matcher matcher) {
        ArrayList <Card> cards= user.getCards();
        for(Card card : cards) {
            card.getInfo();
        }
    }
    private void showHistory(Matcher matcher) {

    }


}

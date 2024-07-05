package Controller;

import Model.*;
import Veiw.Out;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Admin extends Menu {
    public Admin(Scanner scanner) {
        super(scanner, "Admin Menu", "logout");
        /// add commands
        addCommand(Regex.ADMIN_ADD_CARD, this::addCard);
        addCommand(Regex.ADMIN_UPDATE_CARD, this::updateCard);
        addCommand(Regex.ADMIN_DELETE_CARD, this::deleteCard);
        addCommand(Regex.ADMIN_SHOW_PLAYERS, this::showPlayers);
        addCommand(Regex.ADMIN_SHOW_CARDS, this::showCards);
    }
    private void addCard(Matcher matcher) {
        String name = matcher.group("name");
        int attack = Integer.parseInt(matcher.group("attack")),
                duration = Integer.parseInt(matcher.group("duration")),
                damage = Integer.parseInt(matcher.group("damage")),
                upgradeLevel = Integer.parseInt(matcher.group("upgradeLevel")),
                upgradeCost = Integer.parseInt(matcher.group("upgradeCost"));

        if(Data.getCardByCardName(name) != null) {
            Out.print("this name exists! try another name");
            return;
        }
        if(!Card.validCard(attack, duration, damage)) {
            Out.print("invalid card!");
            return;
        }
        Card card = new Card(name, attack, duration, damage, upgradeLevel, upgradeCost);
        Data.addCard(card);
        Out.print("card added successfully!");
        ////// tamam ?!
    }
    private void updateCard(Matcher matcher) {
        String name = matcher.group("name");
        Card card = Data.getCardByCardName(name);
        if(card == null) {
            Out.print("invalid name!");
            return;
        }
        int attack = Integer.parseInt(matcher.group("attack")),
                duration = Integer.parseInt(matcher.group("duration")),
                damage = Integer.parseInt(matcher.group("damage")),
                upgradeLevel = Integer.parseInt(matcher.group("upgradeLevel")),
                upgradeCost = Integer.parseInt(matcher.group("upgradeCost"));

        card = new Card(name, attack, duration, damage, upgradeLevel, upgradeCost);
        Data.updateCard(card);
        ////// tamam ?!
    }
    private void deleteCard(Matcher matcher) {
        String name = matcher.group("name");
        Card card = Data.getCardByCardName(name);
        if(card == null) {
            Out.print("invalid card name!");
            return;
        }
        Data.removeCardByName(name);
        ////// tamam ?!
    }
    private void showPlayers(Matcher matcher) {
        ArrayList<User> users = Data.getAllUser();
        for(User user : users) {
            Out.showInfoOfUser(user);
        }
    }
    private void showCards(Matcher matcher) {
        ArrayList<Card> cards = Data.getAllCards();
        for(Card card : cards) {
            Out.showInfoOfCard(card);
        }
    }
}

/*
add card -name                 -attack    -duration   -damage    -upgradeLevel   -upgradeCost
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name swift−justice -attack 52 -duration 1 -damage 36 -upgradeLevel 1 -upgradeCost 10
add card -name ultra−sonic−brust -attack 20 -duration 2 -damage 33 -upgradeLevel 1 -upgradeCost 10
add card -name ion−blast -attack 10 -duration 2 -damage 27 -upgradeLevel 1 -upgradeCost 10
add card -name biker−support -attack 45 -duration 3 -damage 20 -upgradeLevel 1 -upgradeCost 10
add card -name sonar−strike -attack 28 -duration 2 -damage 16 -upgradeLevel 1 -upgradeCost 10
add card -name from−the−hip -attack 45 -duration 1 -damage 25 -upgradeLevel 1 -upgradeCost 10
add card -name low−blow -attack 25 -duration 1 -damage 36 -upgradeLevel 1 -upgradeCost 10
add card -name side−winder -attack 35 -duration 2 -damage 26 -upgradeLevel 1 -upgradeCost 10
add card -name acid−evaporation -attack 35 -duration 2 -damage 30 -upgradeLevel 1 -upgradeCost 10
add card -name bullet−strike -attack 25 -duration 3 -damage 33 -upgradeLevel 1 -upgradeCost 10
add card -name poison−dispersal -attack 20 -duration 4 -damage 29 -upgradeLevel 1 -upgradeCost 10
add card -name cryo−meltdown -attack 28 -duration 2 -damage 20 -upgradeLevel 1 -upgradeCost 10
add card -name flash−pellets -attack 20 -duration 2 -damage 27 -upgradeLevel 1 -upgradeCost 10
add card -name ion−brust -attack 28 -duration 3 -damage 28 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10
add card -name stealth−support -attack 15 -duration 1 -damage 10 -upgradeLevel 1 -upgradeCost 10



 */

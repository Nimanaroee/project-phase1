package Controller;

import Controller.game.Game;
import Model.*;
import Veiw.Out;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;

public class PreGameMenu extends Menu {
    public static int gambleGolds = 0;

    public PreGameMenu(Scanner scanner, String menuName) {
        super(scanner, menuName, "back");

        gambleGolds = 0;

        /// add command
        addCommand(Regex.LOGIN_LOGIN, this::login);
        addCommand(Regex.GAME_SELECT_CHARACTER, this::selectCharacter);
        addCommand(Regex.GAME_SELECT_GAMBLE_MODE, this::setGambleGolds);
    }

    private void login(Matcher matcher) {
        User user = Data.getUserByUsername(matcher.group("username"));
        if (user == null) {
            Out.print("Username doesn’t exist!");
            return;
        }
        if (!user.getPassword().equals(matcher.group("password"))) {
            Out.print("Password and Username don’t match!");
            return;
        }
        Out.print("user logged in successfully!");
        Data.setLoggedInUser2(user);
    }

    private void selectCharacter(Matcher matcher) {
        String firstCharacter = matcher.group("first");
        String secondCharacter = matcher.group("second");

        ///// check valid characters //////
        ///// select cards ///////
        ///// مشخص کردن دک و کارت های هر بازیکن داخل ایف های خطوط پایینی

        if (firstCharacter.equals("1")) {
            Data.getLoggedInUser1().setCharacter(1);
        } else if (firstCharacter.equals("2")) {
            Data.getLoggedInUser1().setCharacter(2);
        }
        if (secondCharacter.equals("1")) {
            Data.getLoggedInUser2().setCharacter(1);

        } else if (secondCharacter.equals("2")) {
            Data.getLoggedInUser2().setCharacter(2);

        }

        new Game(Data.getLoggedInUser1(), Data.getLoggedInUser2()).start();

        Out.print("back to main menu!");
        Data.setLoggedInUser2(null);
        new MainMenu(scan);
    }

    private void setGambleGolds(Matcher matcher) {
        int first = Integer.parseInt(matcher.group("first"));
        int second = Integer.parseInt(matcher.group("second"));

        if (Data.getLoggedInUser1().getGold() < first) {
            Out.print("not enouph gold to gambling !");
            return;
        }
        if (Data.getLoggedInUser2().getGold() < second) {
            Out.print("not enouph gold to gambling !");
            return;
        }
        PreGameMenu.gambleGolds = Math.min(first, second);
    }

}

package Controller;

import Controller.game.Game;
import Model.*;
import Veiw.Out;

import java.util.Scanner;
import java.util.regex.Matcher;

public class PreGameMenu extends Menu {
    public PreGameMenu(Scanner scanner, String menuName) {
        super(scanner, menuName, "back");
        /// add command
        addCommand(Regex.LOGIN_LOGIN, this::login);
        addCommand(Regex.GAME_SELECT_CHARACTER, this::selectCharacter);
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

        } else if (firstCharacter.equals("2")) {

        } else if (firstCharacter.equals("3")) {

        } else {

        }
        if (secondCharacter.equals("1")) {

        } else if (secondCharacter.equals("2")) {

        } else if (secondCharacter.equals("3")) {

        } else {

        }

        new Game(Data.getLoggedInUser1(), Data.getLoggedInUser2()).start();
    }

}

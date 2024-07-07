import Controller.LoginMenu;
import Model.Data;
import Model.User;

import java.util.Scanner;

import Controller.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Data.gsonHandler.readUserJSON();
        Data.gsonHandler.readCardJSON();
        new LoginMenu(scanner).run();
        //new Controller.game.Game().start();
    }
}
/*
user create -u nima -p !Nimaaaaa1 !Nimaaaaa1 -email nimakahd@gmail.com -n nima

user create -u nima -p random -email nimakahd@gmail.com -n nima

question pick -q 1 -a nima -c nima

Forgot my password -u nima

user login -u nima -p !Nimaaaaa1

user login -u parham -p !Parhammmm1


 */
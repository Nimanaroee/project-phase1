package Controller;

import Model.*;
import java.util.Scanner;

public class Gamemenu extends Menu {
    User user;
    public Gamemenu(Scanner scanner, String menuName, User user) {
        super(scanner, menuName, "back");
        this.user = user;
    }
}

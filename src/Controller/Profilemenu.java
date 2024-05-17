package Controller;

import Model.*;
import java.util.Scanner;

public class Profilemenu extends Menu {
    User user;
    public Profilemenu(Scanner scanner, String menuName, User user) {
        super(scanner, menuName, "back");
        this.user = user;
    }
}

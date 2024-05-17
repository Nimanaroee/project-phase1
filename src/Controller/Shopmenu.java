package Controller;

import Model.*;

import java.util.Scanner;

public class Shopmenu extends Menu {
    User user;
    public Shopmenu(Scanner scanner, String menuName, User user) {
        super(scanner, menuName, "back");
        this.user = user;
    }
}

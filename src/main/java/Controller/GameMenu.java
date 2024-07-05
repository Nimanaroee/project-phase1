package Controller;

import Model.Block;
import Model.Menu;
import java.util.Scanner;

public class GameMenu extends Menu {
    private Block[][] grid;
    private int turn, round;
    public GameMenu(Scanner scanner, String menuName) {
        super(scanner, menuName, "back");
        grid = new Block[2][21];
        round = 1;
        turn = (int)(0.8*Math.random());

        ///// random remover grid
        ///// random shuffle cards
        //// show game board

        //// add command
        //// اضافه شدن دستورات داخل بازی یا صدا شدن آنها از پکیج game داخل اینجا ( یعنی کل فایلی که زدی رو اونجا کپی کن و بعدش اینجا کامندش رو صدا کن )
    }
}

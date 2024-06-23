package Controller;

import Model.Datahistory;
import Model.Menu;
import Model.Regex;
import Veiw.Out;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Historymenu extends Menu {
    final static int LENGTH = 5;
    int startPoint = 0;

    /////// save game history in this arraylist ////////
    ArrayList<Datahistory> gameHistory;

    public Historymenu(Scanner scanner, String menuName) {
        super(scanner, menuName, "back");
        //// add command
        addCommand(Regex.HISTORY_SORT, this::sortBy);
        addCommand(Regex.HISTORY_NEXT_PAGE, this::nextPage);
        addCommand(Regex.HISTORY_PREVIOUS, this::previousPage);
        addCommand(Regex.HISTORY_NUMBERPAGE, this::numberPage);
    }
    private void sortBy(Matcher matcher) {
        String type = matcher.group("type");
        if(type.equals("date"))
            gameHistory.sort(Comparator.comparing(Datahistory::getDate));
        else if(type.equals("resualt"))
            gameHistory.sort(Comparator.comparing(Datahistory::getResualt));
        else if(type.equals("opponent-name"))
            gameHistory.sort(Comparator.comparing(Datahistory::getOpponentName));
        else if(type.equals("opponent-level"))
            gameHistory.sort(Comparator.comparing(Datahistory::getOpponentLevel));
        else
            gameHistory.sort(Comparator.comparing(Datahistory::getDate));

        startPoint = 0;
        show();
    }
    private void nextPage(Matcher matcher) {
        if(startPoint+LENGTH < gameHistory.size())
            startPoint += LENGTH;
        show();
    }
    private void previousPage(Matcher matcher) {
        if(startPoint-LENGTH >= 0)
            startPoint -= LENGTH;
        show();
    }
    private void numberPage(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        if(number*LENGTH < gameHistory.size())
            startPoint = number*LENGTH;
        show();
    }
    private void show() {
        for(int i=startPoint ; i<startPoint+LENGTH ; i++) {
            Datahistory datahistory = gameHistory.get(i);
            Out.print(datahistory.getDate() + " -- " + datahistory.getResualt() + " -- " + datahistory.getOpponentName() + "  " + datahistory.getOpponentLevel() + " -- " + datahistory.getPrize());
        }
    }
}

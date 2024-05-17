package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {
    /// add regexes here
    CREATE_USER(" *user +create +-u +(?<username>.+) +-p +(?<password>[^ ]+) +(?<passwordconfirm>[^ ]+) +–email +(?<email>.+) +-n +(?<nickname>.+) *"),
    VALID_USERNAME("(?<username>[a-zA-Z0-9]+)"),
    VALID_NICKNAME("(?<nickname>[a-zA-Z]+)"),
    VALID_EMAIL("(?<email>.+@gmail.com|.+@outlook.com)"),
    STRONG_PASSWORD("(^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\-\\|\\(\\)\\[\\]])[A-Za-z\\!\\@\\#\\$\\%\\^\\&\\*\\-\\|\\(\\)\\[\\]]{8,20}$)"),
    QUESTION_PICK("question pick -q (?<number>1|2|3) -a (?<answer>.+) -c (?<confirm>.+)"),
    FORGET_PASSWORD("Forgot my password -u (?<username>.+)"),
    LOGIN("user login -u (?<username>.+) -p (?<password>.+)"),
    START_GAME("start game"),
    SHOW_CARDS("show cards"),
    SHOW_HISTORY("show history"),
    ENTER_SHOPMENU("enter shop menu"),
    ENTER_PROFILEMENU("enter profile menu"),
    LOGOUT("logout");

    ;
    private final Pattern pattern;

    Regex(String regex) {
        pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
    public boolean matches(String input) { return pattern.matcher(input).matches();}
}

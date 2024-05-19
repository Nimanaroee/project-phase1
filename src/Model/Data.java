package Model;

import java.util.ArrayList;

public class Data {
    private static final ArrayList<User> users = new ArrayList<User>();
    private static String loggedInUser1 = null;
    private static String loggedInUser2 = null;


    public static User getUserByUsername(String username) {
        for(User user : Data.users) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }
    public static void removeUserByUsername(String username) {
        Data.users.remove(Data.getUserByUsername(username));
    }
    public static void addUser(User user) {
        Data.users.add(user);
    }
    public static void updateUser(User user) {
        Data.removeUserByUsername(user.getUsername());
        Data.addUser(user);
    }
    public User getLoggedInUser1() {
        return Data.getUserByUsername(loggedInUser1);
    }
    public User getLoggedInUser2() {
        return Data.getUserByUsername(loggedInUser2);
    }
}

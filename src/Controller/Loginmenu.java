package Controller;

import Model.*;
import Veiw.Out;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Loginmenu extends Menu {
    private long lasttime=0, attempt =0;
    public Loginmenu(Scanner scan) {
        super(scan, "LOGIN/SIGNUP MENU", "exit");
        //// addcommands here
        addCommand(Regex.CREATE_USER, this::register);
        addCommand(Regex.LOGIN, this::login);
        addCommand(Regex.FORGET_PASSWORD, this::forgetPasswordLogin);
    }

    private void register(Matcher matcher) {
        String username = matcher.group("username"), password = matcher.group("password"), confirmpassword = matcher.group("passwordconfirm"), email = matcher.group("email"), nickname = matcher.group("nickname");
        if(!Regex.VALID_USERNAME.matches(username)){
            Out.print("Invalid Username!");
            return;
        }
        if(Regex.STRONG_PASSWORD.matches(password)) {
            Out.print("Invalid Password!");
            return;
        }
        if(Regex.VALID_EMAIL.matches(email)) {
            Out.print("Invalid Email!");
            return;
        }
        if(Regex.VALID_NICKNAME.matches(nickname)) {
            Out.print("Invalid Nickname!");
            return;
        }
        if(!password.equals(confirmpassword)) {
            System.out.println("Please confirm your password again!");
            return;
        }
        if(password.matches("random")) {

        }
        User user = new User(username, password, email, nickname);
        user = askQuestion(user);
        if(user == null)
            return;
        captcha();
        Data.addUser(user);
    }

    private User askQuestion(User user) {
        Out.print("User created successfully. Please choose a security question :");
        Out.print("• 1-What is your father’s name ?");
        Out.print("• 2-What is your favourite color ?");
        Out.print("• 3-What was the name of your first pet?");
        Matcher matcher = runCommand(Regex.QUESTION_PICK);
        while (!matcher.group("answer").equals(matcher.group("confirm"))) {
            Out.print("Invalid answer! try again");
            matcher = runCommand(Regex.QUESTION_PICK);
        }
        user.setQuestion(matcher.group("number"));
        user.setAnswer(matcher.group("answer"));
        return user;
    }
    private void captcha() {
        ////
    }
    private void login(Matcher matcher) {
        long now = System.currentTimeMillis();
        if((now-lasttime) < attempt *5000) {
            System.out.println("Try again in "+((attempt *5000-(now-lasttime)+999)/1000)+" seconds");
            return;
        }
        User user = Data.getUserByUsername(matcher.group("username"));
        if(user == null) {
            Out.print("Username doesn’t exist!");
            lasttime = now;
            attempt++;
            return;
        }
        if(!user.getPassword().equals(matcher.group("password"))) {
            Out.print("Password and Username don’t match!");
            lasttime = now;
            attempt++;
            return;
        }
        Out.print("user logged in successfully!");
        new Mainmenu(scan, user).run();
    }
    private void forgetPasswordLogin(Matcher matcher) {
        User user = Data.getUserByUsername(matcher.group("username"));
        if(user == null) {
            Out.print("Invalid username, Try again!");
            return;
        }
        Out.print(user.getQuestion());
        String input = scan.nextLine();
        if(!input.equals(user.getPassword())) {
            Out.print("Invalid answer!");
            return;
        }
        Out.print("user logged in successfully!");
        new Mainmenu(scan, user).run();
    }
}


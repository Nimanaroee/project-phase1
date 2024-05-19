package Controller;

import Model.*;
import Veiw.Out;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Loginmenu extends Menu {
    private long lasttime=0, attempt =0;
    public Loginmenu(Scanner scan) {
        super(scan, "LOGIN/SIGNUP MENU", "exit");
        //// add commands here
        addCommand(Regex.CREATE_USER, this::register);
        addCommand(Regex.CREATE_USER_WITH_PASSWORD, this::registerWithPassword);
        addCommand(Regex.LOGIN, this::login);
        addCommand(Regex.FORGET_PASSWORD, this::forgetPasswordLogin);
    }
    private void register(Matcher matcher) {
        String username = matcher.group("username"), password = matcher.group("password"), confirmPassword = matcher.group("passwordconfirm"), email = matcher.group("email"), nickname = matcher.group("nickname");
        checkValidRegister(username, password, confirmPassword, email, nickname);
    }
    private void registerWithPassword(Matcher matcher) {
        String username = matcher.group("username"), email = matcher.group("email"), nickname = matcher.group("nickname");
        String password = passwordBuilder();
        Out.print("Your random password: "+password);
        Out.print("Please enter your password : ");
        String confirmPassword = scan.nextLine();
        checkValidRegister(username, password, confirmPassword, email, nickname);
    }
    private void checkValidRegister(String username, String password,String confirmPassword, String email, String nickname) {
        if(!Regex.VALID_USERNAME.matches(username)){
            Out.print("Invalid Username!");
            return;
        }
        if(Data.getUserByUsername(username) != null) {
            Out.print("This username exist, Try another username!");
        }
        if(!Regex.STRONG_PASSWORD.matches(password)) {
            String errors = "";
            if(password.length() < 8)
                errors += " size";
            if(!password.matches("(^(?=.*[a-z])$)"))
                errors += " small letter";
            if(!password.matches("(^(?=.*[A-Z])$)"))
                errors += " capital letter";
            if(!password.matches("(^(?=.*\\d)$)"))
                errors += " digit";
            if(password.matches("(^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]$)"))
                errors += " special character";
            Out.print("Youre password is weak because :"+errors);
            return;
        }
        if(!Regex.VALID_EMAIL.matches(email)) {
            Out.print("Invalid Email!");
            return;
        }
        if(!Regex.VALID_NICKNAME.matches(nickname)) {
            Out.print("Invalid Nickname!");
            return;
        }
        if(!password.equals(confirmPassword)) {
            System.out.println("Please confirm your password again!");
            return;
        }

        User user = askQuestion(new User(username, password, email, nickname));
        if(user == null)
            return;
        captchaChecker();
        Data.addUser(user);
        System.out.println("Account "+username+" created successfully!");
    }
    private User askQuestion(User user) {
        Out.print("User created successfully. Please choose a security question :");
        Out.print("• 1-What is your father’s name ?");
        Out.print("• 2-What is your favourite color ?");
        Out.print("• 3-What was the name of your first pet?");
        Matcher matcher = runCommand(Regex.QUESTION_PICK);
        if(matcher == null)
            return null;
        while (!matcher.group("answer").equals(matcher.group("confirm"))) {
            Out.print("Invalid answer! try again");
            matcher = runCommand(Regex.QUESTION_PICK);
            if(matcher == null)
                return null;
        }
        user.setQuestion(matcher.group("number"));
        user.setAnswer(matcher.group("answer"));
        return user;
    }
    private String passwordBuilder() {
        char[] SYMBOLS = "!@#$%^&*".toCharArray();
        char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] NUMBERS = "0123456789".toCharArray();
        char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*".toCharArray();
        int length = (int)(Math.random()*10+4);
        System.out.println(length);
        Random rand = new SecureRandom();
        char[] password = new char[length];
        //get the requirements out of the way
        password[0] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[2] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[3] = SYMBOLS[rand.nextInt(SYMBOLS.length)];
        //populate rest of the password with random chars
        for (int i = 4; i < length; i++) {
            password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }
        //shuffle it up
        for (int i = 0; i < password.length; i++) {
            int randomPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randomPosition];
            password[randomPosition] = temp;
        }
        String ret = "";
        for(char c : password)
            ret += c;
        return ret;
    }
    private void captchaChecker() {
        Out.print("ENTER CAPTCHA : ");
        int captcha = Ascii.run();
        int inputcaptcha = scan.nextInt();
        if(inputcaptcha != captcha) {
            Out.print("WRONG CAPTCHA. TRY AGAIN!");
            captchaChecker();
            return;
        }
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

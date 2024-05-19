import Controller.Loginmenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new Loginmenu(scanner).run();
    }
}
/*
user create -u nima -p !Nimaaaaa1 !Nimaaaaa1 -email nimakahd@gmail.com -n nima

question pick -q 1 -a nima -c nima
 */
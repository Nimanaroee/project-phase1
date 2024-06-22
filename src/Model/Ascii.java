package Model;

import Veiw.Out;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;


public class Ascii {
    private static int asciiArt() {
        int width = 200;
        int height = 30;
        int randomleght = (int)(3*Math.random());
        int captcha = 0;
        if(randomleght%3 == 0)
            captcha = (int) (100000*Math.random());
        else if(randomleght%3 == 1)
            captcha = (int) (1000000*Math.random());
        else
            captcha = (int) (10000000*Math.random());
        //BufferedImage image = ImageIO.read(new File("/Users/mkyong/Desktop/logo.jpg"));
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(Integer.toString(captcha), 20, 20);
        //save this image
        //ImageIO.write(image, "png", new File("/users/mkyong/ascii-art.png"));
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "0");
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(sb);
        }
        return captcha;
    }
    private static int func() {
        int firstNum, secNum, function, answer = 0;
        firstNum = (int) (10*Math.random()+1);
        secNum = (int) (10*Math.random()+1);
        function = (int) (3*Math.random()+1);
        if(function < 2) {
            Out.print(firstNum + " + " + secNum + " = ");
            answer = firstNum+secNum;
        }
        else if(function < 3) {
            Out.print(firstNum + " - " + secNum + " = ");
            answer = firstNum-secNum;
        }
        else if(function <= 4) {
            Out.print(firstNum + " * " + secNum + " = ");
            answer = firstNum*secNum;
        }
        return answer;
    }
    public static void captchaChecker(Scanner scan) {
        Out.print("ENTER CAPTCHA : ");
        int captcha = 0;
        if((2*Math.random()) > 1)
            captcha = asciiArt();
        else
            captcha = func();

        int inputcaptcha = Integer.parseInt(scan.nextLine());
        if(inputcaptcha != captcha) {
            Out.print("WRONG CAPTCHA. TRY AGAIN!");
            Ascii.captchaChecker(scan);
            return;
        }
    }
}
package uz.pardayev;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int weeks = requestAge();
        int width = 10000;
        int height = 15000;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();

        // Rasmni oq rangga bo'yab olamiz
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);

        // Oq fonga ramka chizamiz
        drawFrame(g2d, width, height);

        // Yosh chegaralarini chizamiz
        drawAges(g2d);

        // To'g'ri to'rtburchaklar bilan rasm ichini to'ldiramiz
        drawRectangles(g2d, weeks);

        g2d.dispose();

        // Saqlaymiz
        File file = new File("result.png");
        ImageIO.write(bufferedImage, "png", file);

        System.out.println("Result is ready. You can check \"reslut.png\" file");
    }

    public static void drawAges(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));
        g2d.setFont(new Font("Purisa", Font.PLAIN, 150));
        int startPoint = 883;
        int age = 5;
        for (int i = 0; i <= 19; i++) {
            g.drawLine(0, startPoint, 10000, startPoint);
            g2d.drawString("Age: " + age, 50, startPoint - 100);
            startPoint = startPoint + 730;
            age = age + 5;
        }

    }

    public static void drawRectangles(Graphics g, int weeks) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        int startYPoint = 30;
        int filledRects = 1;
        for (int i = 0; i < 100; i++) {
            int startXPoint = 1750;
            startYPoint += 146;
            for (int j = 0; j <= 51; j++) {
                g2d.setColor(Color.BLACK);
                startXPoint += 150;
                g.drawRect(startXPoint, startYPoint, 100, 100);
                if (filledRects < weeks-1) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(startXPoint, startYPoint, 100, 100);
                    filledRects++;
                }
            }
        }
    }

    public static void drawFrame(Graphics g, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(100));
        g2d.drawLine(1500, 0, width, 0);
        g2d.drawLine(1500, 0, 1500, height);
        g2d.drawLine(width, height,width, 0);
        g2d.drawLine(1500, height, width, height);
    }

    public static int requestAge() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your birthday, example: 1992-09-26");
        String dateBeforeString = scanner.nextLine();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateAfterString = date.format(formatter);

        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        int weeksCount = (int) ChronoUnit.WEEKS.between(dateBefore, dateAfter);
        if (weeksCount < 0) {
            System.out.println("Please enter past date...");
            requestAge();
        }

        return weeksCount-1;
    }
}

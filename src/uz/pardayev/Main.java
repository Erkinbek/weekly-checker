package uz.pardayev;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
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
        drawRectangles(g2d);

        g2d.dispose();

        // Saqlaymiz
        File file = new File("result.png");
        ImageIO.write(bufferedImage, "png", file);
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

    public static void drawRectangles(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        int startYPoint = 30;
        for (int i = 0; i < 100; i++) {
            int startXPoint = 1750;
            startYPoint += 146;
            for (int j = 0; j <= 51; j++) {
                startXPoint += 150;
                g.drawRect(startXPoint, startYPoint, 100, 100);
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
}

package desvie;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemy {

    private final Image image;
    private final int width;
    private final int height;
    private static int difficulty, speed;
    private int x;
    private int y;
    private final int[] xInitPattern = {500, 600, 800, 1000, 700, 500, 500, 600, 800, 1000, 700, 500, 700, 600, 1000, 800, 900, 900};
    Random random = new Random();

    public Enemy() {

        height = 30;
        width = 30;

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/inimigo.png"));
        image = imageIcon.getImage();
    }

    public void move() {
        x -= speed;
        if (x < 0) {
            x = 800;
            y = random.nextInt(530);
        }
    }

    public void init(int position) {
        x = xInitPattern[position];
        y = random.nextInt(530);
    }

    // Getters and setters
    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, height, width);
    }

    public static void setDifficulty(int difi) {
        difficulty = difi;
        speed = difficulty;
    }

    public static int getDifficulty() {
        return difficulty;
    }

}

package desvie;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int speed;
    private final int width;
    private final int height;
    private Image image;
    private boolean initialImage = true;


    public Player() {
        init();

        height = 28;
        width = 28;

        speed = 1;

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/jogador.png"));
        image = imageIcon.getImage();
    }

    public void init() {
        x = 80;
        y = 180;
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
        }
        if (x > 765) {
            x = 765;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > 535) {
            y = 535;
        }
    }

    public void keyPressed(KeyEvent tecla) {
        if (tecla.getKeyCode() == KeyEvent.VK_1) {
            speed = 1;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_2) {
            speed = 2;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_3) {
            speed = 3;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_UP) {
            dy = -speed;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN) {
            dy = speed;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT) {
            dx = -speed;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = speed;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_I) {
            ImageIcon imageIcon;
            if (initialImage) {
                imageIcon = new ImageIcon(getClass().getResource("/images/jogador-2.png"));
                initialImage = false;
            } else {
                imageIcon = new ImageIcon(getClass().getResource("/images/jogador.png"));
                initialImage = true;
            }
            image = imageIcon.getImage();
        }

    }

    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key.getKeyCode() == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
    // Getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, height, width);
    }
}

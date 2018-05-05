package desvie;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Objeto {
	// Variáveis
    private int x, y, dx, dy, velocidade, alt, larg;
    private Image imagem;
    private boolean imagemInicial = true;


    public Objeto() {
        inicializa();

        alt = 28;
        larg = 28;

        velocidade = 1;

        ImageIcon ref = new ImageIcon(getClass().getResource("/images/jogador.png"));
        imagem = ref.getImage();
    }

    public void inicializa() {
        x = 80;
        y = 180;
    }
    // Função que move o desvie.Objeto
    public void mover() {
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
    // Função que detecta as teclas pressionadas
    public void keyPressed(KeyEvent tecla) {
        if (tecla.getKeyCode() == KeyEvent.VK_1) {
            velocidade = 1;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_2) {
            velocidade = 2;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_3) {
            velocidade = 3;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_UP) {
            dy = -velocidade;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN) {
            dy = velocidade;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT) {
            dx = -velocidade;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = velocidade;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_I) {
            ImageIcon ref;
            if (imagemInicial) {
                ref = new ImageIcon(getClass().getResource("/images/jogador-2.png"));
                imagemInicial = false;
            } else {
                ref = new ImageIcon(getClass().getResource("/images/jogador.png"));
                imagemInicial = true;
            }
            imagem = ref.getImage();
        }

    }
    // Função que detecta as teclas soltadas
    public void keyReleased(KeyEvent tecla) {
        if (tecla.getKeyCode() == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT) {
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

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, alt, larg);
    }
}

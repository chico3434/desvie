package desvie;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Inimigo {
	// Variáveis
    private Image imagem;
    private int alt, larg;
    private static int dificuldade, velocidade;
    private int x;
    private int y;
    private int init[] = {500, 600, 800, 1000, 700, 500, 500, 600, 800, 1000, 700, 500, 700, 600, 1000, 800, 900, 900};
    Random rand = new Random();

    public Inimigo() {

        alt = 30;
        larg = 30;

        ImageIcon ref = new ImageIcon(getClass().getResource("/images/inimigo.png"));
        imagem = ref.getImage();
    }
    // Função que faz os inimigos se moverem para o lado esquerdo
    public void mover() {
        x -= velocidade;
        if (x < 0) {
            x = 800;
            y = rand.nextInt(530);
        }
    }

    // Inicializa os inimigos aleatoriamente
    public void inicializa(int posi) {
        x = init[posi];
        y = rand.nextInt(530);
    }

    // Getters and setters
    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, alt, larg);
    }

    public static void setDificuldade(int difi) {
        dificuldade = difi;
        velocidade = dificuldade;
    }

    public static int getDificuldade() {
        return dificuldade;
    }

}

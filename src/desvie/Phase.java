package desvie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Phase extends JPanel implements ActionListener {

    private final Player player;
    private final Enemy[] enemies = new Enemy[18];
    private final Image backgroundImage;
    private boolean inGame = false;
    private boolean start = true;
    private boolean pause = false;
    private int score;
    private String difficulty;
    private String displayedDifficulty;
    private long lastTime;

    public Phase() {
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new KeyboardAdapter());

        player = new Player();
        initEnemy();

        ImageIcon ref = new ImageIcon(getClass().getResource("/images/fundo.jpg"));
        backgroundImage = ref.getImage();

        Timer timer = new Timer(5, this);
        timer.start();

        score();
    }

    public void initEnemy(){
        for (int i = 0; i < enemies.length; i++){
            enemies[i] = new Enemy();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!pause && inGame){
            player.move();
            for (Enemy enemy : enemies) {
                enemy.move();
            }
            checkCollision();
            repaint();
        }
    }

    public void init() {
        if (Enemy.getDifficulty() != 0) {
            inGame = true;
            start = false;
            score = 0;
            for (int i = 0; i < enemies.length; i++){
                enemies[i].init(i);
            }
            player.init();
        }
    }

    public void score() {
        ActionListener action = (@SuppressWarnings("unused") ActionEvent e) -> {
            if (inGame && !pause) {
                score += Enemy.getDifficulty();

            }
            if (start) {
                init();
            }

        };
        Timer t = new Timer(10, action);
        t.start();
    }

    public void setEasy(){
        if(!inGame){
            Enemy.setDifficulty(1);
            difficulty = "Fácil";
        }
    }

    public void setMedium(){
        if(!inGame){
            Enemy.setDifficulty(2);
            difficulty = "Médio";
        }
    }

    public void setHard(){
        if(!inGame){
            Enemy.setDifficulty(3);
            difficulty = "Difícil";
        }
    }

    public class KeyboardAdapter extends KeyAdapter {
        // Função que detecta o pressionamento de determinadas teclas e executam determinadas funções
        @Override
        public void keyPressed(KeyEvent e) {
            // Detecta o pressionamento da tecla P e pausa o jogo
            if (e.getKeyCode() == KeyEvent.VK_P) {
                pause = !pause;
            }

            if (e.getKeyCode() == KeyEvent.VK_F) {
                setEasy();
            }
            if (e.getKeyCode() == KeyEvent.VK_M) {
                setMedium();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                setHard();
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                init();
            }
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }

    // Função que detecta colisões e para o jogo
    public void checkCollision() {
        Rectangle playerShape = player.getBounds();
        for (Enemy enemy : enemies) {
            Rectangle enemyShape = enemy.getBounds();
            if (playerShape.intersects(enemyShape)) {
                inGame = false;
                displayedDifficulty = difficulty;
            }
        }
    }

    public void paint(Graphics g) {
        Graphics2D graphic = (Graphics2D) g;
        if (!start) {
            if (inGame) {
                graphic.drawImage(backgroundImage, 0, 0, this);

                graphic.drawImage(player.getImage(), player.getX(), player.getY(), this);

                for (Enemy enemy : enemies) {
                    graphic.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
                }

                graphic.setColor(Color.WHITE);

                // internet code to calculate fps
                double fps = 1000000000.0 / (System.nanoTime() - lastTime);
                lastTime = System.nanoTime();

                graphic.drawString("FPS: " +String.format("%.0f", fps), 10, 15);

                graphic.drawString("Score: " + score, 360, 15);

                graphic.drawString(difficulty, 760, 15);


            } else {

                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/gameover.jpg"));
                graphic.drawImage(imageIcon.getImage(), 0, 0, this);

                graphic.scale(2.5, 2.5);

                graphic.setColor(Color.WHITE);

                graphic.drawString("Pontuação: " + score + " (" + displayedDifficulty + ")", 2, 10);

            }

        } else {

            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/inicio.jpg"));
            graphic.drawImage(imageIcon.getImage(), 0, 0, this);
        }
        g.dispose();
    }
}

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

public class Fase extends JPanel implements ActionListener {

    // Variáveis
    private Objeto jogador;
    private Inimigo inimigo[] = new Inimigo[18];
    private Image fundo;
    private Timer timer;
    private boolean emJogo = false;
    private boolean inicia = true;
    private boolean pause = false;
    private int score;
    private String difi;
    private String difiFinal;
    private double fps;
    private long lastTime;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());

        jogador= new Objeto();
        inicializaInimigo();

        ImageIcon ref = new ImageIcon(getClass().getResource("/images/fundo.jpg"));
        fundo = ref.getImage();

        timer = new Timer(5, this);
        timer.start();

        score();
    }

    public void inicializaInimigo(){
        for (int i = 0; i < inimigo.length; i++){
            inimigo[i] = new Inimigo();
        }
    }

    // Função que é chamada a cada 5 ms, que faz o jogo ser um loop
    // Ela move o desvie.Objeto(Smile), move os Inimigos, roda a função que checa colisões e redesenha a tela
    @Override
    public void actionPerformed(ActionEvent e) {
        if(pause==false && emJogo==true){
            jogador.mover();
            for (int i = 0; i < inimigo.length; i++) {
                inimigo[i].mover();
            }
            checarColisao();
            repaint();
        }
    }
    // Função que inicializa uma jogada
    public void iniciar() {
        if (Inimigo.getDificuldade() != 0) {
            emJogo = true;
            inicia = false;
            score = 0;
            for (int i = 0; i < inimigo.length; i++){
                inimigo[i].inicializa(i);
            }
            jogador.inicializa();
        }
    }

    public void score() {
        ActionListener action = (@SuppressWarnings("unused") ActionEvent e) -> {
            if (emJogo && pause==false) {
                score += Inimigo.getDificuldade();

            }
            if (inicia) {
                iniciar();
            }

        };
        Timer t = new Timer(10, action);
        t.start();
    }
    // Define a dificuldade para Fácil
    public void setF(){
        if(!emJogo){
            Inimigo.setDificuldade(1);
            difi = "Fácil";
        }
    }
    // Define a dificuldade para Médio
    public void setM(){
        if(!emJogo){
            Inimigo.setDificuldade(2);
            difi = "Médio";
        }
    }
    // Define a dificuldade para Difícil
    public void setD(){
        if(!emJogo){
            Inimigo.setDificuldade(3);
            difi = "Difícil";
        }
    }

    // Classe TecladoAdapter contém funções que tratam do pressionamento das teclas
    public class TecladoAdapter extends KeyAdapter {
        // Função que detecta o pressionamento de determinadas teclas e executam determinadas funções
        @Override
        public void keyPressed(KeyEvent e) {
            // Detecta o pressionamento da tecla P e pausa o jogo
            if (e.getKeyCode() == KeyEvent.VK_P) {
                if(pause==true)
                    pause = false;
                else
                    pause = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_F) {
                setF();
            }
            if (e.getKeyCode() == KeyEvent.VK_M) {
                setM();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                setD();
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                iniciar();
            }
            jogador.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            jogador.keyReleased(e);
        }
    }

    // Função que detecta colisões e para o jogo
    public void checarColisao() {
        Rectangle formaJogador= jogador.getBounds();
        Rectangle formaInimigo;
        for (int i = 0; i < 18; i++) {
            formaInimigo = inimigo[i].getBounds();
            if (formaJogador.intersects(formaInimigo)) {
                emJogo = false;
                difiFinal = difi;
            }
        }
    }

    // Função que determina o que será desenhado na tela.
    public void paint(Graphics g) {
        Graphics2D grafico = (Graphics2D) g;
        // Quando não for tela de início fará
        if (!inicia) {
            // Quando em Jogo fará
            if (emJogo) {
                // Desenha o fundo
                grafico.drawImage(fundo, 0, 0, this);
                // Desenha o desvie.Objeto(Smile)
                grafico.drawImage(jogador.getImagem(), jogador.getX(), jogador.getY(), this);
                // Desenha os Inimigos
                for (int i = 0; i < 18; i++) {
                    grafico.drawImage(inimigo[i].getImagem(), inimigo[i].getX(), inimigo[i].getY(), this);
                }
                // Define como branco a cor com que serão desenhadas as strings abaixo
                grafico.setColor(Color.WHITE);

                // Código que achei na internet para calcular FPS
                fps = 1000000000.0 / (System.nanoTime() - lastTime);
                lastTime = System.nanoTime();
                // Desenha o contador de FPS
                grafico.drawString("FPS: " +String.format("%.0f", fps), 10, 15);
                // Desenha o contador de pontuação (score)
                grafico.drawString("Score: " + score, 360, 15);
                // Desenha a informação da dificuldade
                grafico.drawString(difi, 760, 15);

            // Quando não estiver em jogo (e sim em GameOver)
            } else {
                // Desenha o fundo da tela de GameOver
                ImageIcon refe = new ImageIcon(getClass().getResource("/images/gameover.jpg"));
                grafico.drawImage(refe.getImage(), 0, 0, this);
                // Aumenta a escala da letra
                grafico.scale(2.5, 2.5);
                // Define como branco a cor com que serão desenhadas a string abaixo
                grafico.setColor(Color.WHITE);
                // Desenha a pontuação
                grafico.drawString("Pontuação: " + score + " (" + difiFinal + ")", 2, 10);

            }
        // Caso seja tela de início
        } else {
            // Desenha o fundo da tela de início
            ImageIcon refe = new ImageIcon(getClass().getResource("/images/inicio.jpg"));
            grafico.drawImage(refe.getImage(), 0, 0, this);
        }
        g.dispose();
    }
}

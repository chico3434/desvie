package desvie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.*;

public class Janela extends JFrame {

    public Janela() {

        //INICIO MENU

        JMenuBar barra = new JMenuBar();

        JMenu opcoes = new JMenu("Opções");

        JMenuItem site = new JMenuItem("Ver no GitHub");
        site.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    URI link = new URI("https://github.com/chico3434/desvie.git");
                    Desktop.getDesktop().browse(link);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem sair = new JMenuItem("Fechar");
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        opcoes.add(site);
        opcoes.add(sair);
        barra.add(opcoes);

        JMenu dificuldade = new JMenu("Dificuldade");

        Fase fase = new Fase();

        JMenuItem facil = new JMenuItem("Fácil");
        facil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fase.setF();
            }
        });

        JMenuItem medio = new JMenuItem("Médio");
        medio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fase.setM();
            }
        });

        JMenuItem dificil = new JMenuItem("Difícil");
        dificil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fase.setD();
            }
        });

        dificuldade.add(facil);
        dificuldade.add(medio);
        dificuldade.add(dificil);
        barra.add(dificuldade);

        JMenu ajuda = new JMenu("Ajuda");

        JMenuItem sobre = new JMenuItem("Sobre");
        sobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Jogo simples feito em Java\n"
                        + "Versão 0.7.4 Alpha\n"
                        + "Desenvolvido por chico3434", "Sobre", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem comoJogar = new JMenuItem("Como Jogar");
        comoJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Para mover o Emoji usar a setas direcionais\n"
                        + "A velocidade do Smile é alterada nos números 1, 2 e 3, sendo 1 a velocidade inicial\n"
                        + "Usar espaço para reiniciar o jogo caso tenha perdido\n"
                        + "Para alterar o Emoji aperte 'i'\n"
                        + "Para pausar o jogo aperte 'p'\n"
                        + "Para escolher a dificuldade basta clicar F - Fácil, M - Média e D - Difícil\n"
                        + "Só é possível mudar a dificuldade na tela inicial e na tela de Game Over", "Como Jogar - COMANDOS", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ajuda.add(comoJogar);
        ajuda.add(sobre);

        barra.add(ajuda);

        setJMenuBar(barra);
        //FIM MENU

        // Adiciona a desvie.Janela o Painel "fase"
        this.add(fase);
        // Configs da desvie.Janela
        this.setTitle("desvie.Desvie do desvie.Inimigo");
        this.setSize(800, 585);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/desvie.png")).getImage());
        this.setUndecorated(true);
    }
}

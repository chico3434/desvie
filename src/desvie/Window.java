package desvie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu jMenuOptions = new JMenu("Opções");

        JMenuItem jMenuItemGithub = new JMenuItem("Ver no GitHub");
        jMenuItemGithub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    URI link = new URI("https://github.com/chico3434/desvie.git");
                    Desktop.getDesktop().browse(link);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem jMenuItemExit = new JMenuItem("Fechar");
        jMenuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jMenuOptions.add(jMenuItemGithub);
        jMenuOptions.add(jMenuItemExit);
        jMenuBar.add(jMenuOptions);

        JMenu jMenuDifficulty = new JMenu("Dificuldade");

        Phase phase = new Phase();

        JMenuItem jMenuItemEasy = new JMenuItem("Fácil");
        jMenuItemEasy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                phase.setEasy();
            }
        });

        JMenuItem jMenuItemMedium = new JMenuItem("Médio");
        jMenuItemMedium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                phase.setMedium();
            }
        });

        JMenuItem jMenuItemHard = new JMenuItem("Difícil");
        jMenuItemHard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                phase.setHard();
            }
        });

        jMenuDifficulty.add(jMenuItemEasy);
        jMenuDifficulty.add(jMenuItemMedium);
        jMenuDifficulty.add(jMenuItemHard);
        jMenuBar.add(jMenuDifficulty);

        JMenu jMenuHelp = new JMenu("Ajuda");

        JMenuItem jMenuItemAbout = new JMenuItem("Sobre");
        jMenuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Jogo simples feito em Java\n"
                        + "Versão 0.7.4 Alpha\n"
                        + "Desenvolvido por chico3434", "Sobre", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem jMenuItemHowToPlay = new JMenuItem("Como Jogar");
        jMenuItemHowToPlay.addActionListener(new ActionListener() {
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

        jMenuHelp.add(jMenuItemHowToPlay);
        jMenuHelp.add(jMenuItemAbout);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        this.add(phase);
        this.setTitle("Desvie do Inimigo");
        this.setSize(800, 585);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/desvie.png")).getImage());
        this.setUndecorated(true);
    }
}

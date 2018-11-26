package ui;


import javax.swing.*;
import java.awt.*;

public class GameStart extends JFrame{
    private GamePath game;
    private GamePanel gp;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;


    public GameStart(){
        super("hell");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        gp = new GamePanel();
        game = new GamePath();

        add(gp);
        add(game, BorderLayout.SOUTH);
        pack();
        setVisible(true);


    }


    public static void main(String[] args) {
        new GameStart();
    }
}

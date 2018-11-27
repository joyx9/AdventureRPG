package ui;


import model.MainPlayer;
import model.PlayerEve;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    private static final int LBL_WIDTH = 250;
    private static final int LBL_HEIGHT = 30;
    private JLabel mainPlayerHP;
    private JLabel eveHP;
    private MainPlayer yourPlayer;
    private PlayerEve evePC;

    public StatusBar(GamePath gamePath){
        yourPlayer = gamePath.getYourPlayerFromPath();
        evePC = gamePath.getEvePC();
        mainPlayerHP = new JLabel(yourPlayer.getName() + ": HP = " + yourPlayer.getHitPoint(),
                SwingConstants.CENTER);
        mainPlayerHP.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        eveHP = new JLabel(evePC.getName() + ": HP = " + evePC.getHitPoint(),
                SwingConstants.CENTER);
        eveHP.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));


        setBackground(Color.GRAY);

        add(mainPlayerHP);
        add(Box.createHorizontalStrut(10));
        add(eveHP);
    }

    public void update(){
        mainPlayerHP.setText(yourPlayer.getName() + ": HP = " + yourPlayer.getHitPoint());
        eveHP.setText(evePC.getName() + ": HP = " + evePC.getHitPoint());
        repaint();
    }
}

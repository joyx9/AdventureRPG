package ui;


import model.PlayerEve;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel{
    Image backgroundImg;
    Image backgroudImgGray;
    Image evePortrait;
    Image eveSprite;
    Image eveSpriteDown;
    Image slimeSprite;
    Image mcSprite;

    private GamePath gp;


    public GamePanel(GamePath gp)  {
        this.gp = gp;
        loadImages();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!gp.getBattleGoingOn()){
            g.drawImage(backgroudImgGray,0,0,this);
            if(gp.isMCAlive()) {
                g.drawImage(evePortrait, 20, 0, this);
            }
        }
        else{
            g.drawImage(backgroundImg,0,0,this);
            g.drawImage(mcSprite,505,130,this);
            g.drawImage(slimeSprite,90,165, this);
            if(gp.isEveAlive()){
                g.drawImage(eveSprite,610,160,this);
            }
            else{
                g.drawImage(eveSpriteDown,610,160,this);
            }
            repaint();
        }


    }



    public void loadImages() {
        try {
            backgroudImgGray = ImageIO.read(getClass().getResource("/resources/images/backgroundgray.png"));
            backgroundImg = ImageIO.read(getClass().getResource("/resources/images/background.png"));
            evePortrait = ImageIO.read(getClass().getResource("/resources/images/evePortrait.png"));
            eveSprite = ImageIO.read(getClass().getResource("/resources/images/eveSprite.png"));
            eveSpriteDown = ImageIO.read(getClass().getResource("/resources/images/eveSpriteDown.png"));
            slimeSprite = ImageIO.read(getClass().getResource("/resources/images/slime.png"));
            mcSprite = ImageIO.read(getClass().getResource("/resources/images/mcSprite.png"));


        } catch (IOException e) {
            //
        }
    }


}

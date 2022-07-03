package jeu.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import jeu.main.GamePanel;

public class Obj_Boots extends SuperObject 
{
    GamePanel gp;
    
    public Obj_Boots(GamePanel gp)
    {
        this.name = "Boots";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/speedboots.png"));

            uTool.scaleImage(image, gp.getTailleTuile(), gp.getTailleTuile());
            
        } catch (IOException e) {e.printStackTrace();}
    }
    
}
package jeu.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import jeu.main.GamePanel;

public class Obj_Chest extends SuperObject 
{
    GamePanel gp;
    
    public Obj_Chest(GamePanel gp)
    {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));

            uTool.scaleImage(image, gp.getTailleTuile(), gp.getTailleTuile());
            
        } catch (IOException e) {e.printStackTrace();}
    }
    
}
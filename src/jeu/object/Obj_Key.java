package jeu.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import jeu.main.GamePanel;

public class Obj_Key extends SuperObject 
{   
    GamePanel gp;

    public Obj_Key(GamePanel gp)
    {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));

            uTool.scaleImage(image, gp.getTailleTuile(), gp.getTailleTuile());
            
        } catch (IOException e) {e.printStackTrace();}
    }
 
    
}

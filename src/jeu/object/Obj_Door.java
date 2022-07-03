package jeu.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import jeu.main.GamePanel;

public class Obj_Door extends SuperObject 
{
    GamePanel gp;
    
    public Obj_Door(GamePanel gp)
    {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));

            uTool.scaleImage(image, gp.getTailleTuile(), gp.getTailleTuile());
            
        } catch (IOException e) {e.printStackTrace();}

        collision = true;
    }
    
}


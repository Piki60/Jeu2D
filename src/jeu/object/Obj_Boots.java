package jeu.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Boots extends SuperObject 
{
    public Obj_Boots()
    {
        this.name = "Boots";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/speedboots.png"));
            
        } catch (IOException e) {e.printStackTrace();}
    }
    
}
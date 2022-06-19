package jeu.object;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

import jeu.object.SuperObject;

public class Obj_Door extends SuperObject 
{
    public Obj_Door()
    {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            
        } catch (IOException e) {e.printStackTrace();}

        collision = true;
    }
    
}


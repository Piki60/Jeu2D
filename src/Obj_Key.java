package jeu.object;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

import jeu.object.SuperObject;

public class Obj_Key extends SuperObject 
{
    public Obj_Key()
    {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
            
        } catch (IOException e) {e.printStackTrace();}
    }
 
    
}

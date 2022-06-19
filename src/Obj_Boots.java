package jeu.object;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

import jeu.object.SuperObject;

public class Obj_Boots extends SuperObject 
{
    public Obj_Boots()
    {
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/speedboots.png"));
            
        } catch (IOException e) {e.printStackTrace();}
    }
    
}
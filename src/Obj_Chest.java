package jeu.object;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

import jeu.object.SuperObject;

public class Obj_Chest extends SuperObject 
{
    public Obj_Chest()
    {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
            
        } catch (IOException e) {e.printStackTrace();}
    }
    
}
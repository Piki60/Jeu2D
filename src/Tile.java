package jeu.tile;

import java.awt.image.BufferedImage;

public class Tile
{
    private BufferedImage image;
    private boolean collision;

    public Tile(BufferedImage img)
    {
        this.image = img;
        this.collision = false;

    }

    public BufferedImage getImage() 
    {
        return this.image;
    }


    public void collison()
    {
        if (this.collision == true)
            this.collision = false;
        else
            this.collision = true;    

    }
}

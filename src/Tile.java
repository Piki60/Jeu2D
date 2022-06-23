package jeu.tile;

import java.awt.image.BufferedImage;

public class Tile
{
    private BufferedImage image;
    public boolean collision;

    public Tile(BufferedImage img)
    {
        this.image = img;
        this.collision = false;

    }

    public BufferedImage getImage() 
    {
        return this.image;
    }


    public void    setCollision (boolean b) { this.collision = b   ;}
    public boolean getCollision          () { return this.collision;}
}

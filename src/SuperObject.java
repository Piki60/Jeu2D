package jeu.object;

import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import jeu.main.GamePanel;
import java.awt.Rectangle;

public class SuperObject 
{
    public BufferedImage image;
    public String        name;
    public boolean       collision = false;
    public int           worldX, worldY;
    public Rectangle     solidArea = new Rectangle(0, 0, 64, 64);
    public int           solidAreaDefaultX = 0;
    public int           solidAreaDefaultY = 0;


    public void draw(Graphics2D g2, GamePanel gp)
    {
        int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().getScreenY();

        if (worldX + gp.getTailleTuile() > gp.getPlayer().worldX - gp.getPlayer().getScreenX() &&
            worldX - gp.getTailleTuile() < gp.getPlayer().worldX + gp.getPlayer().getScreenX() &&
            worldY + gp.getTailleTuile() > gp.getPlayer().worldY - gp.getPlayer().getScreenY() &&
            worldY - gp.getTailleTuile() < gp.getPlayer().worldY + gp.getPlayer().getScreenY() )
        {
            // on dessine la première tuile puis on passe à la colonne suivante
            g2.drawImage(this.image, screenX, screenY, gp.getTailleTuile(), gp.getTailleTuile(), null);
        }

    }
    
}

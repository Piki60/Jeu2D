package jeu.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import jeu.main.GamePanel;
import jeu.main.UtilityTool;

public abstract class SuperObject 
{
    protected BufferedImage image;
    protected String        name;
    protected boolean       collision = false;
    protected int           worldX, worldY;
    protected Rectangle     solidArea = new Rectangle(0, 0, 64, 64);
    protected int           solidAreaDefaultX = 0;
    protected int           solidAreaDefaultY = 0;

    protected UtilityTool uTool = new UtilityTool();



    public void draw(Graphics2D g2, GamePanel gp)
    {
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + gp.getTailleTuile() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
            worldX - gp.getTailleTuile() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
            worldY + gp.getTailleTuile() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
            worldY - gp.getTailleTuile() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY() )
        {
            // on dessine la première tuile puis on passe à la colonne suivante
            g2.drawImage(this.image, screenX, screenY, gp.getTailleTuile(), gp.getTailleTuile(), null);
        }

    }

    public BufferedImage getImage            () { return this.image            ;} 
    public String        getName             () { return this.name             ;}
    public int           getWorldX           () { return this.worldX           ;}   
    public int           getWorldY           () { return this.worldY           ;}   
    public Rectangle     getSolidArea        () { return this.solidArea        ;}
    public boolean       getCollision        () { return this.collision        ;}
    public int           getSolidAreaDefaultX() { return this.solidAreaDefaultX;}
    public int           getSolidAreaDefaultY() { return this.solidAreaDefaultY;}


    public void setWorldX (int posX)    { this.worldX = posX;}
    public void setWorldY (int posY)    { this.worldY = posY;}
}

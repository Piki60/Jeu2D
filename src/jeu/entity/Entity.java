package jeu.entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public abstract class Entity 
{
    protected int worldX, worldY;
    protected int vitesse;

    protected BufferedImage up, up1, up2, down, down1, down2, right, right1, right2, left, left1, left2;
    protected String direction;
    protected int spriteCounter = -1;
    protected int spriteNum;
    protected Rectangle solidArea;
    protected int solidAreaDefaultX, solidAreaDefaultY;
    protected boolean collisionOn = false;

    public int       getSolidAreaDefaultX() { return this.solidAreaDefaultX;}
    public int       getSolidAreaDefaultY() { return this.solidAreaDefaultY;}
    public Rectangle getSolidArea        () { return this.solidArea        ;}
    public int       getWorldX           () { return this.worldX           ;}
    public int       getWorldY           () { return this.worldY           ;}
    public String    getDirection        () { return this.direction        ;}
    public int       getVitesse          () { return this.vitesse          ;}

    public void     setCollision(boolean b) { this.collisionOn = b;}
}

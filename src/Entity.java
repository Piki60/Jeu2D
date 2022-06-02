package jeu.entity;

import java.awt.image.BufferedImage;

public class Entity 
{
    public int x, y;
    public int vitesse;

    public BufferedImage up, up1, up2, down, down1, down2, right, right1, right2, left, left1, left2;
    public String direction;
    public int spriteCounter = -1;
    public int spriteNum;
}

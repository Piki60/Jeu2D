package jeu.entity;

import jeu.main.GamePanel;
import jeu.main.KeyHandler;

import java.awt.image.BufferedImage;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.InputStream;


public class Player extends Entity
{
    private GamePanel  gp;
    private KeyHandler keyH;

    public Player (GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;
        this.setDefaultValues();
        this.getPlayerImage();

    }

    public void setDefaultValues()
    {
        x = 100;
        y = 100;
        vitesse = 4;
        direction = "down";

    }

    public void getPlayerImage()
    {

        try {
            up     = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_up.png"    ));
            up1    = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_up1.png"   ));
            up2    = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_up2.png"   ));
            down   = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_down.png"  ));
            down1  = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_down1.png" ));
            down2  = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_down2.png" ));
            right  = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_right.png" ));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_right2.png"));
            left   = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_left.png"  ));
            left1  = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_left1.png" ));
            left2  = ImageIO.read(getClass().getResourceAsStream("/res/player/girl_left2.png" ));
   
        } catch (IOException e) { e.printStackTrace();   }

    }

    public void update()
    {
        // on ne rentre dans la boucle que si on appuie sur l'une des touches claviers
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) 
        {
            if (keyH.upPressed == true) {
                direction = "up";
                y -= vitesse;
            } else if (keyH.downPressed == true) {
                direction = "down";
                y += vitesse;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                x -= vitesse;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                x += vitesse;
            }

            spriteCounter++;
            if (spriteCounter > 12) { //quand il rafraichît l'image pour la 15ème fois il switche d'image
                if (spriteNum == 0) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 0;
                }

                spriteCounter = -1; // il retourne à -1 pour débuter à 0 et recommencer la boucle
            }

        }


    }

    public void draw(Graphics2D g2)
    {
        //g2.setColor(Color.WHITE);
        //g2.fillRect(x, y, gp.tailleTuile, gp.tailleTuile); // crée un rectangle et le rempli de la couleur de notre brush

        BufferedImage image = null;

        /*choisit les images en fonction du numéro du sprite */
        switch(direction)
        {
            case "up":
                if (spriteNum == 0) { image = up;} 
                if (spriteNum == 1) { image = up1;}
                if (spriteNum == 2) { image = up2;}               
                break;
            case "down":
                if (spriteNum == 0) { image = down;} 
                if (spriteNum == 1) { image = down1;}
                if (spriteNum == 2) { image = down2;}
                break;
             case "right":
                if (spriteNum == 0) { image = right;} 
                if (spriteNum == 1) { image = right1;}
                if (spriteNum == 2) { image = right2;}
                break;
            case "left":
                if (spriteNum == 0) { image = left;} 
                if (spriteNum == 1) { image = left1;}
                if (spriteNum == 2) { image = left2;}
                break;
         }

        g2.drawImage(image, x, y, gp.tailleTuile, gp.tailleTuile, null);
    }


}

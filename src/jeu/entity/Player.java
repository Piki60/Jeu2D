package jeu.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import jeu.main.GamePanel;
import jeu.main.KeyHandler;


public class Player extends Entity
{
    private GamePanel  gp;
    private KeyHandler keyH;

    private final int SCREEN_X;
    private final int SCREEN_Y;


    private int refresh = 20;
    private int hasKey = 0;

    public Player (GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        this.SCREEN_X = gp.getLongueurEcran()  / 2 - (gp.getTailleTuile()/2);
        this.SCREEN_Y = gp.getLargeurEcran ()  / 2 - (gp.getTailleTuile()/2); 

        solidArea = new Rectangle();
        solidArea.x      = 19;
        solidArea.y      = 32;
        solidArea.width  = 26;
        solidArea.height = 28;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        this.setDefaultValues();
        this.getPlayerImage();


    }

    public void setDefaultValues()
    {
        worldX    = gp.getTailleTuile() * 22;
        worldY    = gp.getTailleTuile() * 24;
        vitesse   = 7;
        direction = "down";

    }

    public void getPlayerImage()
    {

        try {
            up     = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/up.png"    ));
            up1    = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/up1.png"   ));
            up2    = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/up2.png"   ));
            down   = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/down.png"  ));
            down1  = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/down1.png" ));
            down2  = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/down2.png" ));
            right  = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/right.png" ));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/right2.png"));
            left   = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/left.png"  ));
            left1  = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/left1.png" ));
            left2  = ImageIO.read(getClass().getResourceAsStream("/res/player/player1/left2.png" ));
   
        } catch (IOException e) { e.printStackTrace();   }

    }

    public void update()
    {
        // on ne rentre dans la boucle que si on appuie sur l'une des touches claviers
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) 
        {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";                
            } else if (keyH.leftPressed == true) {
                direction = "left";               
            } else if (keyH.rightPressed == true) {
                direction = "right";               
            }

            // Verifier la collision
            collisionOn = false;
            gp.getCchecker().checkTile(this);

            //Vérifier la collision de l'objet
            int objIndex = gp.getCchecker().checkObject(this, true);
            this.pickUpObject(objIndex);

            // si la collision est fausse, le jouer peut bouger
            if ( collisionOn == false)
            {
                switch (direction)
                {
                    case "up"    :  worldY -= vitesse; break;
                    case "down"  :  worldY += vitesse; break;
                    case "left"  :  worldX -= vitesse; break;
                    case "right" :  worldX += vitesse; break;

                }


            }

            spriteCounter++;
            if (spriteCounter > this.refresh)   //quand il rafraichît l'image pour la 12ème fois il switche d'image
            {
                if (spriteNum == 0) 
                {
                    spriteNum = 1;
                } 
                else if (spriteNum == 1) 
                {
                    spriteNum = 2;
                } 
                else if (spriteNum == 2) 
                {
                    spriteNum = 0;
                }

                spriteCounter = -1;  // il retourne à -1 pour débuter à 0 et recommencer la boucle
            }

        }


    }

    public void pickUpObject (int i)
    {
        if ( i != 999)
        {
            String objectName = gp.getObject(i).getName();

            switch (objectName)
            {
                case "Key" : 
                        gp.playerSE(1);
                        hasKey++; 
                        gp.setObject(i, null); 
                        gp.ui.showMessage("Vous avez obtenu une clé");
                        break;
                case "Door":
                        if (hasKey > 0)
                        {
                            gp.playerSE(3);
                            gp.setObject(i, null);
                            hasKey--;
                            gp.ui.showMessage("Vous avez ouvert une porte");
                        }
                        else
                        {
                            gp.ui.showMessage("Vous avez besoin d'une clé");
                        }
                        break;
                case "Boots" :
                        gp.playerSE(2);
                        this.vitesse += 2;
                        this.refresh += 2 ;
                        gp.setObject(i, null); 
                        gp.ui.showMessage("Vitesse augmentée!");
                        break;
                case "Chest" :
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                        gp.playerSE(4);

            }
        }

        

    }

    public void draw(Graphics2D g2)
    {
        //g2.setColor(Color.WHITE);
        //g2.fillRect(x, y, gp.getTailleTuile(), gp.getTailleTuile()); // crée un rectangle et le rempli de la couleur de notre brush

        BufferedImage image = null;

        /*choisit les images en fonction du numéro du sprite */
        switch(direction)
        {
            case "up":
                if (spriteNum == 0) { image = up1;} 
                if (spriteNum == 1) { image = up;}
                if (spriteNum == 2) { image = up2;}               
                break;
            case "down":
                if (spriteNum == 0) { image = down1;} 
                if (spriteNum == 1) { image = down;}
                if (spriteNum == 2) { image = down2;}
                break;
             case "right":
                if (spriteNum == 0) { image = right1;} 
                if (spriteNum == 1) { image = right;}
                if (spriteNum == 2) { image = right2;}
                break;
            case "left":
                if (spriteNum == 0) { image = left1;} 
                if (spriteNum == 1) { image = left;}
                if (spriteNum == 2) { image = left2;}
                break;
         }

        g2.drawImage(image, SCREEN_X, SCREEN_Y, gp.getTailleTuile(), gp.getTailleTuile(), null);
    }


    public int getScreenX() { return this.SCREEN_X; }
    public int getScreenY() { return this.SCREEN_Y; }
    public int getWorldX () { return this.worldX  ; }
    public int getWorldY () { return this.worldY  ; }
    public int getHasKey () { return this.hasKey  ; }


}

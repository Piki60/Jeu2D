package jeu.main;

import jeu.main.GamePanel;
import jeu.object.Obj_Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;

public class UI 
{
    GamePanel     gp;
    Font          arial_40, arial_80B;
    BufferedImage keyImage;

    public boolean messageOn  = false;
    public String  message    = "";
    public int     messageCpt = 0;

    public boolean gameFinished = false;

    public UI(GamePanel gp)
    {
        this.gp = gp;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.arial_80B = new Font("Arial", Font.BOLD, 80);
        Obj_Key key = new Obj_Key();
        this.keyImage = key.image;
    }    

    public void showMessage(String text)
    {
        message   = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2)
    {
        if (gameFinished == true )
        {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int longueurTexte;

            //PREMIER TEXTE
            text = "Tu as obtenu le tresor!";
            longueurTexte = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            int x = gp.getLongueurEcran() /2 - longueurTexte /2;
            int y = gp.getLargeurEcran () /2 - (gp.getTailleTuile()*3);

            g2.drawString(text, x, y);

            // DEUXIEME TEXTE
            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);

            text = "Felicitations!";
            longueurTexte = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.getLongueurEcran() /2 - longueurTexte /2;
            y = gp.getLargeurEcran () /2 + (gp.getTailleTuile()*2);

            g2.drawString(text, x, y);

            gp.jeuThread = null;
        }
        else
        {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.getTailleTuile()/2, gp.getTailleTuile()/2, gp.getTailleTuile(), gp.getTailleTuile(), null );
            g2.drawString("Key = " + gp.getPlayer().hasKey, 90, 80);

            //MESSAGE
            if ( messageOn == true)
            {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.getTailleTuile()/2, gp.getTailleTuile()*5);

                messageCpt++;

                if ( messageCpt  > 120)
                {
                    messageCpt = 0;
                    messageOn = false;
                }
            }
        }
    }
}

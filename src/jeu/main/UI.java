package jeu.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import jeu.object.Obj_Key;

public class UI 
{
    GamePanel     gp;
    Font          arial_40, arial_80B;
    BufferedImage keyImage;

    public boolean messageOn  = false;
    public String  message    = "";
    public int     messageCpt = 0;

    public boolean gameFinished = false;

    public double  playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp)
    {
        this.gp        = gp;
        this.arial_40  = new Font("Arial", Font.PLAIN, 40);
        this.arial_80B = new Font("Arial", Font.BOLD, 80);
        Obj_Key key    = new Obj_Key();
        this.keyImage  = key.getImage();
    }    

    public void showMessage(String text)
    {
        this.message   = text;
        this.messageOn = true;
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
            text = "Tu as obtenu le trésor!";
            longueurTexte = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            int x = gp.getLongueurEcran() /2 - longueurTexte /2;
            int y = gp.getLargeurEcran () /2 - (gp.getTailleTuile()*3);

            g2.drawString(text, x, y);

            // DEUXIEME TEXTE
            text = "Ton temps est : " + dFormat.format(playTime) + " secondes.";
            longueurTexte = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.getLongueurEcran() /2 - longueurTexte /2;
            y = gp.getLargeurEcran () /2 + (gp.getTailleTuile()*4);

            g2.drawString(text, x, y);

            // TROISIEME TEXTE (fin du jeu)
            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);

            text = "Félicitations!";
            longueurTexte = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.getLongueurEcran() /2 - longueurTexte /2;
            y = gp.getLargeurEcran () /2 + (gp.getTailleTuile()*2);

            g2.drawString(text, x, y);

            gp.finThread();
        }
        else
        {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.getTailleTuile()/2, gp.getTailleTuile()/2, gp.getTailleTuile(), gp.getTailleTuile(), null );
            g2.drawString("Key = " + gp.getPlayer().getHasKey(), 90, 80);

            // TIME

            this.playTime += (double)1/60;
            g2.drawString("Time : " + dFormat.format(playTime), gp.getTailleTuile()*11, 80);
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

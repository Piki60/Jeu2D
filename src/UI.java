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
    Font          arial_40;
    BufferedImage keyImage;

    public boolean messageOn  = false;
    public String  message    = "";
    public int     messageCpt = 0;

    public UI(GamePanel gp)
    {
        this.gp = gp;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
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

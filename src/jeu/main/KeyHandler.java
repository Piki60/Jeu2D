// ce fichier sert à utiliser/accepter l'entrée clavier pour effectuer un déplacement
package jeu.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean upReleased, downReleased, leftReleased, rightReleased;

    @Override
    public void keyPressed(KeyEvent e) 
    {
        //chaque touche de clavier correspond à un nombre précis
        //on récupère donc ce nombre (ex  : Tab --> 9)
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) { upPressed    = true; upReleased    = false;}
        if (code == KeyEvent.VK_Q) { leftPressed  = true; leftReleased  = false;}
        if (code == KeyEvent.VK_S) { downPressed  = true; downReleased  = false;}
        if (code == KeyEvent.VK_D) { rightPressed = true; rightReleased = false;}

        
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) 
        { 
            upPressed     = false; 
            upReleased    = true;

            leftReleased  = false;
            downReleased  = false;
            rightReleased = false;
        }


        if (code == KeyEvent.VK_Q) 
        { 
            leftPressed   = false; 
            leftReleased  = true;

            downReleased  = false;
            rightReleased = false;
            upReleased    = false;
        }

        if (code == KeyEvent.VK_S) 
        { 
            downPressed  = false; 
            downReleased  = true;

            leftReleased  = false;
            rightReleased = false;
            upReleased    = false;
        }

        if (code == KeyEvent.VK_D) 
        { 
            rightPressed = false; 
            rightReleased = true;

            leftReleased  = false;
            downReleased = false;
            upReleased    = false;
        }

        
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        // TODO Auto-generated method stub
        
    }
    
    
}

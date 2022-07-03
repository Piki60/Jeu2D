// ce fichier sert à utiliser/accepter l'entrée clavier pour effectuer un déplacement
package jeu.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyPressed(KeyEvent e) 
    {
        //chaque touche de clavier correspond à un nombre précis
        //on récupère donc ce nombre (ex  : Tab --> 9)
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) { upPressed    = true;}
        if (code == KeyEvent.VK_Q) { leftPressed  = true;}
        if (code == KeyEvent.VK_S) { downPressed  = true;}
        if (code == KeyEvent.VK_D) { rightPressed = true;}

        
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) { upPressed    = false;}
        if (code == KeyEvent.VK_Q) { leftPressed  = false;}
        if (code == KeyEvent.VK_S) { downPressed  = false;}
        if (code == KeyEvent.VK_D) { rightPressed = false;}

        
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        // TODO Auto-generated method stub
        
    }
    
    
}

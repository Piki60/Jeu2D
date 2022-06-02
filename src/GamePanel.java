package jeu.main;

import jeu.entity.Player;
import javax.swing.JPanel;
import jeu.tile.GestionnaireTile;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    //paramètre de l'écran
    public final int originaleTuile = 32; //tuile 32x32
    public final int echelle        = 2;

    public final int tailleTuile  = originaleTuile * echelle; // tuile 48x48
    public final int maxEcranCol  = 16;
    public final int maxEcranLig  = 12;

    public final int longueurEcran = tailleTuile * maxEcranCol; // 768 pixels
    public final int largeurEcran = tailleTuile * maxEcranLig; // 576 pixels

    KeyHandler keyH = new KeyHandler();
    Thread jeuThread; //création d'un processus
    Player player = new Player(this, keyH);
    GestionnaireTile tileG = new GestionnaireTile(this);
    //FPS
    int FPS = 60;


    public GamePanel()
    {
        //paramètre du panel (position, taille, etc)
        this.setPreferredSize(new Dimension(longueurEcran, largeurEcran));
        this.setDoubleBuffered(true); //regarder classe
        this.setBackground(Color.BLACK);

        this.addKeyListener(keyH);
        this.setFocusable(true); //le panel va être focaliser sur les entrées claviers

    }

    public void startGameThread()
    {
        jeuThread = new Thread(this);
        jeuThread.start();

    }

    public void run()
    {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while (jeuThread != null)
        {
            currentTime = System.nanoTime();
            delta += ( currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1)
            {
                // 1 UPDATE : mise à jour des informations
                update();
                // 2 DRAW : dessiner l'écran avec les informations mis à jour
                repaint();

                delta--;
            } 
        }

    }

    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; //graphics2D est une classe qui implémente Graphics, elle ouvre plus de possibilités
       
        tileG.draw(g2);
        player.draw(g2);

        g2.dispose(); //Supprime ce contexte graphique et libère toutes les ressources système qu'il utilise. 
    }
}

package jeu.main;

import jeu.entity.Player;
import javax.swing.JPanel;
import jeu.tile.GestionnaireTile;
import java.awt.*;
import java.security.cert.CollectionCertStoreParameters;

public class GamePanel extends JPanel implements Runnable
{
    //paramètre de l'écran
    private final int ORIGINALE_TUILE = 32; //tuile 32x32
    private final int ECHELLE        = 2;

    private final int TAILLE_TUILE  = ORIGINALE_TUILE * ECHELLE; // tuile 64x64
    private final int MAX_ECRAN_COL  = 16;
    private final int MAX_ECRAN_LIG  = 12;

    private final int LONGUEUR_ECRAN = TAILLE_TUILE * MAX_ECRAN_COL; // 768 pixels
    private final int LARGEUR_ECRAN = TAILLE_TUILE * MAX_ECRAN_LIG; // 576 pixels


    // paramètres du monde
    private final int MAX_WORLD_COL  = 50;
    private final int MAX_WORLD_LIG  = 50;
    private final int WORLD_LONGUEUR = TAILLE_TUILE * MAX_WORLD_COL;
    private final int WORLD_LARGEUR  = TAILLE_TUILE * MAX_WORLD_LIG;
    
    private KeyHandler       keyH      = new KeyHandler();
    private Player           player    = new Player(this, keyH);
    public  GestionnaireTile tileG     = new GestionnaireTile(this);
    private Thread           jeuThread; //création d'un processus
    public CollisionChecker cChecker = new CollisionChecker(this);
    //FPS
    int FPS = 60;


    public GamePanel()
    {
        //paramètre du panel (position, taille, etc)
        this.setPreferredSize(new Dimension(LONGUEUR_ECRAN, LARGEUR_ECRAN));
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


    public int    getMaxEcranLig  () { return this.MAX_ECRAN_LIG ; }
    public int    getMaxEcranCol  () { return this.MAX_ECRAN_COL ; }
    public int    getTailleTuile  () { return this.TAILLE_TUILE  ; }
    public int    getLongueurEcran() { return this.LONGUEUR_ECRAN; }
    public int    getLargeurEcran () { return this.LARGEUR_ECRAN ; }
    public int    getMaxWorldCol  () { return this.MAX_WORLD_COL ; }
    public int    getMaxWorldLig  () { return this.MAX_WORLD_LIG ; }
    public int    getWorldLongueur() { return this.WORLD_LONGUEUR; }
    public int    getWorldLargeur () { return this.WORLD_LARGEUR ; }
    public Player getPlayer       () { return this.player        ; }





}

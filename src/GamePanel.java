package jeu.main;

import jeu.main.*;
import jeu.entity.*;
import jeu.object.*;
import jeu.tile.*;

import javax.swing.JPanel;
import java.awt.*;

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
    
    //système
    private  KeyHandler        keyH       = new KeyHandler      ();
    private  CollisionChecker  cChecker   = new CollisionChecker(this);
    private  GestionnaireTile  tileG      = new GestionnaireTile(this);
    private  AssetSetter       aSetter    = new AssetSetter     (this);
    private  Sound             music      = new Sound           ();
    private  Sound             sEffect    = new Sound           ();
    public  UI                 ui         = new UI              (this);

    //création d'un processus
    private  Thread            jeuThread;                               

    //entité et objet
    private Player            player      = new Player(this, keyH);
    private SuperObject[]     obj        = new SuperObject[10]; 
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

    public void setupGame()
    {
        this.aSetter.setObject();
        this.playMusic(0);
    }

    public void startGameThread()
    {
        this.jeuThread = new Thread(this);
        this.jeuThread.start();

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
                this.update();
                // 2 DRAW : dessiner l'écran avec les informations mis à jour
                this.repaint();

                delta--;
            } 
        }

    }

    public void update()
    {
        this.player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; //graphics2D est une classe qui implémente Graphics, elle ouvre plus de possibilités

        // Tuile

        tileG.draw(g2);

        //Object
        for ( int cpt = 0; cpt < obj.length; cpt++)
            if ( obj[cpt] != null)
                obj[cpt].draw(g2, this);
        // Joueur
        player.draw(g2);

        this.ui.draw(g2);
        

        g2.dispose(); //Supprime ce contexte graphique et libère toutes les ressources système qu'il utilise. 
    }

    public void playMusic(int i)
    {
        this.music.setFile(i);
        this.music.play    ();
        this.music.loop    ();
    }

    public void stopMusic()
    {
        this.music.stop();
    }

    public void playerSE(int i)
    {
        this.sEffect.setFile(i);
        this.sEffect.play();
    }


    public int              getMaxEcranLig  () { return this.MAX_ECRAN_LIG ; }
    public int              getMaxEcranCol  () { return this.MAX_ECRAN_COL ; }
    public int              getTailleTuile  () { return this.TAILLE_TUILE  ; }
    public int              getLongueurEcran() { return this.LONGUEUR_ECRAN; }
    public int              getLargeurEcran () { return this.LARGEUR_ECRAN ; }
    public int              getMaxWorldCol  () { return this.MAX_WORLD_COL ; }
    public int              getMaxWorldLig  () { return this.MAX_WORLD_LIG ; }
    public Player           getPlayer       () { return this.player        ; }
    public GestionnaireTile getTile         () { return this.tileG         ; }
    public CollisionChecker getCchecker     () { return this.cChecker      ; }

    public void   finThread       () { this.jeuThread = null     ; }


    //OBJET

    public void setObject (int indice, SuperObject so)
    {
        this.obj[indice] = so;
    }
    public void setNewObject (int indice, String name) 
    { 

        switch (name)
        {
            case "Key"   : this.obj[indice] = new Obj_Key  (); break;
            case "Chest" : this.obj[indice] = new Obj_Chest(); break;
            case "Boots" : this.obj[indice] = new Obj_Boots(); break;
            case "Door"  : this.obj[indice] = new Obj_Door (); break;

        }
            
        
    }

    public SuperObject getObject (int indice) 
    { 
        return this.obj[indice];
    }

    public int getTailleObject () { return this.obj.length; }





}

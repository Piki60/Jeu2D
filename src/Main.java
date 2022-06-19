package jeu.main;

import javax.swing.*;

public class Main 
{
    public static void main(String[] args) 
    {
        // création des composants
        JFrame    window    = new JFrame   ();
        GamePanel gamePanel = new GamePanel();

        //paramètre de la fenêtre
        window.setTitle("2D Adventure");
        window.setResizable(false); //pour ne pas modifier la taille de la fenêtre
        //window.setLocationRelativeTo(null); //positionne la fenêtre au milieu

        //positonnement des composants
        window.add(gamePanel);

        
        gamePanel.startGameThread();
        gamePanel.setupGame();

        window.pack(); // Permet à cette fenêtre d'être dimensionnée pour s'adapter à la taille et aux dispositions préférées de ses sous-composants.
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        
    }
}

package jeu.tile;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import jeu.main.GamePanel;

public class GestionnaireTile 
{
    private GamePanel gp;
    private Tile[] tile;

    public GestionnaireTile(GamePanel gp)
    {
        this.gp = gp;
        this.tile = new Tile[10];

        this.getTileImage();

    }

    public void getTileImage()
    {
        try {

            this.tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/background/grass.png")));
            this.tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/background/wall.png")));
            this.tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/background/water.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2)
    {

        int col = 0;
        int lig = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxEcranCol && lig < gp.maxEcranLig)
        {
            // on dessine la première tuile puis on passe à la colonne suivante
            g2.drawImage(this.tile[0].getImage(), x, y, gp.tailleTuile, gp.tailleTuile, null);
            col++;

            // on augmente x de la taille de la tuile
            x += gp.tailleTuile;

            // si on atteint la fin de notre écran, on passe à la ligne suivante
            if (col == gp.maxEcranCol) 
            {
                col = 0;
                x = 0;
                lig++;
                y += gp.tailleTuile;

            }
        }

    }
}

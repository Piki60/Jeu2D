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
        g2.drawImage(this.tile[0].getImage(), 0, 0, gp.tailleTuile, gp.tailleTuile, null);
        g2.drawImage(this.tile[1].getImage(), 64, 0, gp.tailleTuile, gp.tailleTuile, null);
        g2.drawImage(this.tile[2].getImage(), 128, 0, gp.tailleTuile, gp.tailleTuile, null);

        int col = 0;
        int lig = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxEcranCol && lig < gp.maxEcranLig)
        {
            g2.drawImage(this.tile[0].getImage(), 0, 0, gp.tailleTuile, gp.tailleTuile, null);
            col++;
        }

    }
}

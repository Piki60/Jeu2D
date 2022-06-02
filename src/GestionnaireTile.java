package jeu.tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import jeu.main.GamePanel;

public class GestionnaireTile 
{
    private GamePanel gp;
    private Tile[] tile;
    private int mapTileNumber[] [];

    public GestionnaireTile(GamePanel gp)
    {
        this.gp = gp;
        this.tile = new Tile[10];
        this.mapTileNumber = new int[gp.maxEcranCol][gp.maxEcranLig];

        this.getTileImage();
        this.loadMap();

    }

    public void loadMap()
    {
       try {

            InputStream is = getClass().getResourceAsStream("/res/background/map/map0.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int lig = 0;

            while (col < gp.maxEcranCol && lig < gp.maxEcranLig)
            {
                String line = br.readLine();

                while (col < gp.maxEcranCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    this.mapTileNumber[col][lig] = num;
                    col++;
                }

                if (col == gp.maxEcranCol) {
                    col = 0;
                    lig++;
                }

            }

            br.close();

        } catch (IOException e) { e.printStackTrace();}


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
            int tileNum = this.mapTileNumber[col][lig];
            // on dessine la première tuile puis on passe à la colonne suivante
            g2.drawImage(this.tile[tileNum].getImage(), x, y, gp.tailleTuile, gp.tailleTuile, null);
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

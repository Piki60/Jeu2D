package jeu.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import jeu.main.GamePanel;

public class GestionnaireTile 
{
    private GamePanel gp;
    public  Tile[] tile;
    public  int mapTileNumber[][];

    public GestionnaireTile(GamePanel gp)
    {
        this.gp            = gp;
        this.tile          = new Tile[10];
        this.mapTileNumber = new int[gp.getMaxWorldCol()][gp.getMaxWorldLig()];

        this.getTileImage();
        this.loadMap("/res/background/map/map1.txt");

    }

    public void loadMap(String cheminFichier)
    {
       try {

            InputStream is = getClass().getResourceAsStream(cheminFichier);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int lig = 0;

            while (col < gp.getMaxWorldCol() && lig < gp.getMaxWorldLig())
            {
                String line = br.readLine();

                while (col < gp.getMaxWorldCol()) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    this.mapTileNumber[col][lig] = num;
                    col++;
                }

                if (col == gp.getMaxWorldCol()) {
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
            this.tile[1].setCollision(true);

            this.tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/background/water.png")));
            this.tile[2].setCollision(true);

            this.tile[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/background/dirt.png")));

            this.tile[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/background/tree.png")));
            this.tile[4].setCollision(true);

            this.tile[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/background/sand.png")));

            this.tile[6] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/background/plank.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2)
    {

        int worldCol = 0;
        int worldLig = 0;
  
        while (worldCol < gp.getMaxWorldCol() && worldLig < gp.getMaxWorldLig())
        {
            int tileNum = this.mapTileNumber[worldCol][worldLig];

            int worldX = worldCol * gp.getTailleTuile();
            int worldY = worldLig * gp.getTailleTuile();

            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            if (worldX + gp.getTailleTuile() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTailleTuile() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTailleTuile() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTailleTuile() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY() )
            {
                // on dessine la première tuile puis on passe à la colonne suivante
                g2.drawImage(this.tile[tileNum].getImage(), screenX, screenY, gp.getTailleTuile(), gp.getTailleTuile(), null);
            }

            
            worldCol++;

            // si on atteint la fin de notre écran, on passe à la ligne suivante
            if (worldCol == gp.getMaxWorldCol()) 
            {
                worldCol = 0;
                worldLig++;
            }
        }


    }
}

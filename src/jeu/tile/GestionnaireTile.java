package jeu.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import jeu.main.GamePanel;
import jeu.main.UtilityTool;

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

        this.setup(0, "grass", false);
        this.setup(1, "wall", true);
        this.setup(2, "water", true);
        this.setup(3, "dirt", false);
        this.setup(4, "tree", true);
        this.setup(5, "sand", false);

    }

    public void setup ( int index, String imageName, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();

        try 
        {
            this.tile[index] = new Tile (ImageIO.read(getClass().getResourceAsStream("/res/background/" + imageName + ".png")));
            this.tile[index].setImage( uTool.scaleImage(tile[index].getImage(), gp.getTailleTuile(), gp.getTailleTuile()) );
            this.tile[index].setCollision(collision);


        } catch (Exception e) {e.printStackTrace();}
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
                g2.drawImage(this.tile[tileNum].getImage(), screenX, screenY, null);
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

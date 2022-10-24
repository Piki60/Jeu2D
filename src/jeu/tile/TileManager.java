package jeu.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import jeu.main.GamePanel;
import jeu.main.UtilityTool;

public class TileManager 
{
    private GamePanel gp;
    public  Tile[] tile;
    public  int mapTileNumber[][];

    public TileManager(GamePanel gp)
    {
        this.gp            = gp;
        this.tile          = new Tile[53];
        this.mapTileNumber = new int[gp.getMaxWorldCol()][gp.getMaxWorldLig()];

        this.getTileImage();
        this.loadMap("/res/background/map/map3.txt");

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

                while (col < gp.getMaxWorldCol()) 
                {
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

        /*----------------------------------------------------------------------------------------------------------- */
        /*                                               TUILES NON UTILISEES                                         */
        /*----------------------------------------------------------------------------------------------------------- */
        
        // de 0 à 9

        for (int i = 0; i <= 9; i++)
        {
            this.setup(i, "grass00", false);
        }

        /*----------------------------------------------------------------------------------------------------------- */
        /*                                                 TUILES UTILISEES                                           */
        /*----------------------------------------------------------------------------------------------------------- */

        /* ------- */
        /*  grass  */
        /* ------- */

        this.setup(10, "grass00" , false);

        /* ------- */
        /*  road  */
        /* ------- */

        // de 11 à 20
        for (int i = 11; i < 21; i++ )
        {
            this.setup(i, "road0"+ (i-11), false);
        }

        /*this.setup(11, "road00"  , false);
        this.setup(12, "road01"  , false);
        this.setup(13, "road02"  , false);
        this.setup(14, "road03"  , false);
        this.setup(15, "road04"  , false);
        this.setup(16, "road05"  , false);
        this.setup(17, "road06"  , false);
        this.setup(18, "road07"  , false);
        this.setup(19, "road08"  , false);
        this.setup(20, "road09"  , false);*/


        this.setup(21, "road10"  , false);
        this.setup(22, "road11"  , false);
        this.setup(23, "road12"  , false);
        this.setup(24, "road13"  , false);
        this.setup(25, "road14"  , false);
        this.setup(26, "road15"  , false);
        this.setup(27, "road16"  , false);
        this.setup(28, "road17"  , false);
        this.setup(29, "road18"  , false);


        //tree
        this.setup(30, "tree"  , true);
        this.setup(31, "tree08"  , true);
        this.setup(32, "tree09"  , true);
        this.setup(33, "tree10"  , true);
        this.setup(34, "tree11"  , true);
        this.setup(35, "tree12"  , true);
        this.setup(36, "tree13"  , true);
        this.setup(37, "tree14"  , true);

        //water
        this.setup(38, "water00"  , true);
        this.setup(39, "water01"  , true);
        this.setup(40, "water02"  , true);
        this.setup(41, "water03"  , true);
        this.setup(42, "water04"  , true);
        this.setup(43, "water05"  , true);
        this.setup(44, "water06"  , true);
        this.setup(45, "water07"  , true);
        this.setup(46, "water08"  , true);
        this.setup(47, "water09"  , true);
        this.setup(48, "water10"  , true);
        this.setup(49, "water11"  , true);
        this.setup(50, "water12"  , true);
        this.setup(51, "water13"  , true);

        this.setup(52, "sand00", false);


    }

    public void setup ( int index, String imageName, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();

        try 
        {
            this.tile[index] = new Tile (ImageIO.read(getClass().getResourceAsStream("/res/background/tile/" + imageName + ".png")));
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

package jeu.main;

import jeu.entity.Entity;
import jeu.main.GamePanel;

public class CollisionChecker
{
    private GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile( Entity entity)
    {
        int entityLeftWorldX  = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY   = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol   = entityLeftWorldX   / gp.getTailleTuile();
        int entityRightCol  = entityRightWorldX  / gp.getTailleTuile();
        int entityTopLig    = entityTopWorldY    / gp.getTailleTuile();
        int entityBottomLig = entityBottomWorldY / gp.getTailleTuile();

        int tileNum1, tileNum2;

        switch (entity.direction)
        {
            case "up" : 

                entityTopLig = (entityTopWorldY - entity.vitesse)/ gp.getTailleTuile();

                tileNum1 = gp.tileG.mapTileNumber[entityLeftCol][entityTopLig]; 
                tileNum2 = gp.tileG.mapTileNumber[entityRightCol][entityTopLig];

                if (gp.tileG.tile[tileNum1].collision == true || gp.tileG.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;

            case "down" : 

                entityBottomLig  = (entityBottomWorldY - entity.vitesse)/ gp.getTailleTuile();

                tileNum1 = gp.tileG.mapTileNumber[entityLeftCol][entityBottomLig]; 
                tileNum2 = gp.tileG.mapTileNumber[entityRightCol][entityBottomLig];

                if (gp.tileG.tile[tileNum1].collision == true || gp.tileG.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;

            case "left" : 

                entityLeftCol = (entityLeftWorldX - entity.vitesse)/ gp.getTailleTuile();

                tileNum1 = gp.tileG.mapTileNumber[entityLeftCol][entityTopLig]; 
                tileNum2 = gp.tileG.mapTileNumber[entityLeftCol][entityBottomLig];

                if (gp.tileG.tile[tileNum1].collision == true || gp.tileG.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "right" : 
    
                entityRightCol = (entityRightWorldX + entity.vitesse)/ gp.getTailleTuile();

                tileNum1 = gp.tileG.mapTileNumber[entityRightCol][entityTopLig]; 
                tileNum2 = gp.tileG.mapTileNumber[entityRightCol][entityBottomLig];

                if (gp.tileG.tile[tileNum1].collision == true || gp.tileG.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;

        }

    }
}

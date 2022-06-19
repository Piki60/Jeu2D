package jeu.main;

import jeu.entity.Entity;
import jeu.main.GamePanel;
import java.awt.Rectangle;

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

    public int checkObject(Entity entity, boolean player)
    {
        // cette méthode vérifie si le joueur frappe l'objet et si c'est vrai alors on renvoie l'index de l'objet
        int index = 999;

        for(int cpt =0; cpt < gp.obj.length; cpt++)
        {
            if (gp.obj[cpt] != null)
            {
                // obtenir la position de la zone solide de l'entité
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //obtenir la position de la zone solide de l'objet
                gp.obj[cpt].solidArea.x = gp.obj[cpt].worldX + gp.obj[cpt].solidArea.x;
                gp.obj[cpt].solidArea.y = gp.obj[cpt].worldY + gp.obj[cpt].solidArea.y;

                switch(entity.direction)
                {
                    case"up" : 
                            entity.solidArea.y -= entity.vitesse;

                            if (entity.solidArea.intersects(gp.obj[cpt].solidArea))
                            {
                                if ( gp.obj[cpt].collision == true)    
                                    entity.collisionOn = true;

                                if ( player == true)
                                     index = cpt;

                            }


                    break;

                    case"down" : 
                            entity.solidArea.y += entity.vitesse;

                            if (entity.solidArea.intersects(gp.obj[cpt].solidArea))
                            {
                                if ( gp.obj[cpt].collision == true)    
                                    entity.collisionOn = true;

                                if ( player == true)
                                    index = cpt;

                            }

                                
                    break;

                    case"left" : 
                            entity.solidArea.x -= entity.vitesse;

                            if (entity.solidArea.intersects(gp.obj[cpt].solidArea))
                            {
                                if ( gp.obj[cpt].collision == true)    
                                    entity.collisionOn = true;

                                if ( player == true)
                                    index = cpt;

                            }

                    break;

                    case"right" :
                            entity.solidArea.x += entity.vitesse;

                            if (entity.solidArea.intersects(gp.obj[cpt].solidArea))
                            {
                                if ( gp.obj[cpt].collision == true)    
                                    entity.collisionOn = true;

                                if ( player == true)
                                    index = cpt;

                            }

                    break;

                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[cpt].solidArea.x = gp.obj[cpt].solidAreaDefaultX;
                gp.obj[cpt].solidArea.y = gp.obj[cpt].solidAreaDefaultY;
            }

        }

        return index;


    }
}

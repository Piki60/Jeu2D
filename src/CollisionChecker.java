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
        int entityLeftWorldX  = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY   = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol   = entityLeftWorldX   / gp.getTailleTuile();
        int entityRightCol  = entityRightWorldX  / gp.getTailleTuile();
        int entityTopLig    = entityTopWorldY    / gp.getTailleTuile();
        int entityBottomLig = entityBottomWorldY / gp.getTailleTuile();

        int tileNum1, tileNum2;

        switch (entity.getDirection())
        {
            case "up" : 

                entityTopLig = (entityTopWorldY - entity.getVitesse())/ gp.getTailleTuile();

                tileNum1 = gp.getTile().mapTileNumber[entityLeftCol][entityTopLig]; 
                tileNum2 = gp.getTile().mapTileNumber[entityRightCol][entityTopLig];

                if (gp.getTile().tile[tileNum1].getCollision() == true || gp.getTile().tile[tileNum2].getCollision() == true)
                {
                    entity.setCollision(true);
                }
                break;

            case "down" : 

                entityBottomLig  = (entityBottomWorldY - entity.getVitesse())/ gp.getTailleTuile();

                tileNum1 = gp.getTile().mapTileNumber[entityLeftCol][entityBottomLig]; 
                tileNum2 = gp.getTile().mapTileNumber[entityRightCol][entityBottomLig];

                if (gp.getTile().tile[tileNum1].getCollision() == true || gp.getTile().tile[tileNum2].getCollision() == true)
                {
                    entity.setCollision(true);
                }
                break;

            case "left" : 

                entityLeftCol = (entityLeftWorldX - entity.getVitesse())/ gp.getTailleTuile();

                tileNum1 = gp.getTile().mapTileNumber[entityLeftCol][entityTopLig]; 
                tileNum2 = gp.getTile().mapTileNumber[entityLeftCol][entityBottomLig];

                if (gp.getTile().tile[tileNum1].getCollision() == true || gp.getTile().tile[tileNum2].getCollision() == true)
                {
                    entity.setCollision(true);
                }
                break;
            case "right" : 
    
                entityRightCol = (entityRightWorldX + entity.getVitesse())/ gp.getTailleTuile();

                tileNum1 = gp.getTile().mapTileNumber[entityRightCol][entityTopLig]; 
                tileNum2 = gp.getTile().mapTileNumber[entityRightCol][entityBottomLig];

                if (gp.getTile().tile[tileNum1].getCollision() == true || gp.getTile().tile[tileNum2].getCollision() == true)
                {
                    entity.setCollision(true);
                }
                break;

        }

    }

    public int checkObject(Entity entity, boolean player)
    {
        // cette méthode vérifie si le joueur frappe l'objet et si c'est vrai alors on renvoie l'index de l'objet
        int index = 999;

        for(int cpt =0; cpt < gp.getTailleObject(); cpt++)
        {
            if (gp.getObject(cpt) != null)
            {
                // obtenir la position de la zone solide de l'entité
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

                //obtenir la position de la zone solide de l'objet
                gp.getObject(cpt).getSolidArea().x = gp.getObject(cpt).getWorldX() + gp.getObject(cpt).getSolidArea().x;
                gp.getObject(cpt).getSolidArea().y = gp.getObject(cpt).getWorldY() + gp.getObject(cpt).getSolidArea().y;

                switch(entity.getDirection())
                {
                    case"up" : 
                            entity.getSolidArea().y -= entity.getVitesse();

                            if (entity.getSolidArea().intersects(gp.getObject(cpt).getSolidArea()))
                            {
                                if ( gp.getObject(cpt).getCollision() == true)    
                                    entity.setCollision(true);

                                if ( player == true)
                                     index = cpt;

                            }


                    break;

                    case"down" : 
                            entity.getSolidArea().y += entity.getVitesse();

                            if (entity.getSolidArea().intersects(gp.getObject(cpt).getSolidArea()))
                            {
                                if ( gp.getObject(cpt).getCollision() == true)    
                                    entity.setCollision(true);

                                if ( player == true)
                                    index = cpt;

                            }

                                
                    break;

                    case"left" : 
                            entity.getSolidArea().x -= entity.getVitesse();

                            if (entity.getSolidArea().intersects(gp.getObject(cpt).getSolidArea()))
                            {
                                if ( gp.getObject(cpt).getCollision() == true)    
                                    entity.setCollision(true);

                                if ( player == true)
                                    index = cpt;

                            }

                    break;

                    case"right" :
                            entity.getSolidArea().x += entity.getVitesse();

                            if (entity.getSolidArea().intersects(gp.getObject(cpt).getSolidArea()))
                            {
                                if ( gp.getObject(cpt).getCollision() == true)    
                                    entity.setCollision(true);

                                if ( player == true)
                                    index = cpt;

                            }

                    break;

                }

                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gp.getObject(cpt).getSolidArea().x = gp.getObject(cpt).getSolidAreaDefaultX();
                gp.getObject(cpt).getSolidArea().y = gp.getObject(cpt).getSolidAreaDefaultY();
            }

        }

        return index;


    }
}

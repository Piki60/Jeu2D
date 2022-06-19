package jeu.main;

import jeu.main.GamePanel;
import jeu.object.*;

public class AssetSetter 
{
    private GamePanel gp;

    public AssetSetter( GamePanel gp)
    {
        this.gp = gp;


    }

    public void setObject()
    {
        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX =  23 * gp.getTailleTuile();
        gp.obj[0].worldY = 7 * gp.getTailleTuile();

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX =  37 * gp.getTailleTuile();
        gp.obj[1].worldY =  7 * gp.getTailleTuile();

        gp.obj[2] = new Obj_Door();
        gp.obj[2].worldX =  10 * gp.getTailleTuile();
        gp.obj[2].worldY =  11 * gp.getTailleTuile();

        gp.obj[3] = new Obj_Door();
        gp.obj[3].worldX =  8 * gp.getTailleTuile();
        gp.obj[3].worldY =  28 * gp.getTailleTuile();

        gp.obj[4] = new Obj_Door();
        gp.obj[4].worldX =  12 * gp.getTailleTuile();
        gp.obj[4].worldY =  22 * gp.getTailleTuile();

        gp.obj[5] = new Obj_Chest();
        gp.obj[5].worldX =  10 * gp.getTailleTuile();
        gp.obj[5].worldY =  7 * gp.getTailleTuile();

        gp.obj[6] = new Obj_Key();
        gp.obj[6].worldX =  24 * gp.getTailleTuile();
        gp.obj[6].worldY =  38 * gp.getTailleTuile();

        gp.obj[7] = new Obj_Boots();
        gp.obj[7].worldX =  37 * gp.getTailleTuile();
        gp.obj[7].worldY =  42 * gp.getTailleTuile();
    }
}

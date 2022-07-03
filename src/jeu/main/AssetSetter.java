package jeu.main;

public class AssetSetter 
{
    private GamePanel gp;


    public AssetSetter( GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        gp.setNewObject(0, "Key");
        gp.getObject   (0).setWorldX(23 * gp.getTailleTuile());
        gp.getObject   (0).setWorldY( 7 * gp.getTailleTuile());

        gp.setNewObject(1, "Key");
        gp.getObject   (1).setWorldX(37 * gp.getTailleTuile());
        gp.getObject  (1).setWorldY(7 * gp.getTailleTuile());

        gp.setNewObject(2, "Door");
        gp.getObject(2).setWorldX(10 * gp.getTailleTuile());
        gp.getObject(2).setWorldY(11 * gp.getTailleTuile());

        gp.setNewObject(3, "Door");
        gp.getObject(3).setWorldX(8 * gp.getTailleTuile());
        gp.getObject(3).setWorldY(28 * gp.getTailleTuile());

        gp.setNewObject(4, "Door");
        gp.getObject(4).setWorldX(12 * gp.getTailleTuile());
        gp.getObject(4).setWorldY(22 * gp.getTailleTuile());

        gp.setNewObject(5, "Chest");
        gp.getObject(5).setWorldX(10 * gp.getTailleTuile());
        gp.getObject(5).setWorldY(7 * gp.getTailleTuile());

        gp.setNewObject(6, "Key");
        gp.getObject(6).setWorldX(24 * gp.getTailleTuile());
        gp.getObject(6).setWorldY(38 * gp.getTailleTuile());

        gp.setNewObject(7, "Boots");
        gp.getObject(7).setWorldX(37 * gp.getTailleTuile());
        gp.getObject(7).setWorldY(42 * gp.getTailleTuile());
    }


}

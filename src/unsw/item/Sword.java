package unsw.item;
import unsw.dungeon.*;

public class Sword extends Entity implements Collectable{

    int durability;
    public Sword(int x, int y, String name){
        super(x, y, name);
        this.durability = 5;
    }

    public void collectBehaviour(Dungeon dun, Inventory inv){
        //set to durability if collect sword
        dun.getPlayer().setSwordDurability(durability);
        dun.deleteEntity(this);
        

    }


}
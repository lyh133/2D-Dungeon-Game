package unsw.item;
import unsw.dungeon.*;

public class Bow extends Entity implements Collectable{

    public Bow(int x, int y, String name){
        super(x, y, name);
    }



    public void collectBehaviour(Dungeon dun, Inventory inv){
        //set to durability if collect sword
        dun.getPlayer().getBowequipt().setValue(true);
        dun.deleteEntity(this);
        

    }


}
package unsw.item;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class Key extends Entity implements Collectable{
    //key_id matches with door_id
    String colour;
    public Key(int x, int y, String name, String colour){
        super(x, y, name );
        this.colour = colour;
        
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public void collectBehaviour(Dungeon dun, Inventory inv) {
        
        inv.addItem(this);
        dun.deleteEntity(this);
    }

}
package unsw.item;
import unsw.dungeon.*;


public class Treasure extends Entity implements Collectable{
    
    public Treasure(int x, int y, String name){
        super(x, y, name);
    }


    public void collectBehaviour(Dungeon dun, Inventory inv){
        inv.addItem(this);        
        dun.deleteEntity(this);
        dun.goalsComplete();
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        if(!super.equals(obj)) return false;
 
        return true;

    }

}
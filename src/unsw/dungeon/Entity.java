package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 * @param <Player>
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private String name;
    private int id;
    /**
     * Create an entity positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public Entity(int x, int y, String name) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.name = name;
        this.id = -1;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public int getX() {
        return x().get();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public boolean findAdjacents (int x1, int y1, int x2, int y2) {
		// Same x coordinate
		if (x1 == x2) {
			// Max vertical separation of 1 unit
			if (y1 == y2 + 1 || y1 == y2 - 1) {
				return true;
			}
		// Same y coordinate
		} else if (y1 == y2) {
			// Max horizontal separation of 1 unit
			if (x1 == x2 + 1 || x1 == x2 - 1) {
				return true;
			}
		}

		return false;
	}

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Entity en = (Entity) obj;
        return (this.x == en.x() && this.y == en.y() && this.name == en.getName() && this.id == en.getId());

    }


}

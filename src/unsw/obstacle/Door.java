package unsw.obstacle;

import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;




public class Door extends Entity implements Collidable{


    private BooleanProperty state;
    private String colour;
    public Door(int x, int y, String name, String colour) {
        super(x, y, name);
        this.colour = colour;
        this.state = new SimpleBooleanProperty();
        state.setValue(false);

        
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public BooleanProperty getState(){
        return state;
    }

    public boolean canCollide(Player player, Entity en){
        //System.out.println(player.getInventory().findKey_colour("red"));
        if(state.getValue().equals(false) && !player.hasKey_colour(getColour())) return false;
        //collidable if closed && haskey or opened
        return true;
    }
    public void open(){
        state.setValue(true);

    }

    public void collisionBehaviour(Player player){
        //if player has key of same id as this door then remove the key and open the door
        if(!state.getValue()) {
            if(player.hasKey_colour(getColour())) {
                
                player.useKey();
                open();
            }
        }
    }

    
    
}
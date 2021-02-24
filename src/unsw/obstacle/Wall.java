package unsw.obstacle;

import unsw.dungeon.Entity;
import unsw.dungeon.Player;

public class Wall extends Entity implements Collidable {

    public Wall(int x, int y, String name) {
        super(x, y, name);
    }

    public boolean canCollide(Player player, Entity en) {
        //nothing can collide wall
        return false;
    }
    public void collisionBehaviour(Player player){
        System.out.println("ouch, Im litterally in the wall right now");
        //ouch!
        //cant even collide so no behaviour
    
    }

}
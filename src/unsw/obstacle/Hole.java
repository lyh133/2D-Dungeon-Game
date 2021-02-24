package unsw.obstacle;

import unsw.dungeon.*;
public class Hole extends Entity implements Collidable {


    public Hole(int x, int y, String name) {
        super(x, y, name);
    }

    @Override
    public boolean canCollide(Player player, Entity en) {
        return true;
    }

    @Override
    public void collisionBehaviour(Player player) {
        player.die();
    }
        
}
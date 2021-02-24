package unsw.obstacle;

import unsw.dungeon.*;

public interface Collidable {
    //can this entity be on the same tile as en?
    public boolean canCollide(Player player, Entity en);
    //the behaviour when player collides with this entity
    public void collisionBehaviour(Player player);


}
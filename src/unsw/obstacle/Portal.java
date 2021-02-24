
package unsw.obstacle;

import java.util.List;

import unsw.dungeon.Entity;
import unsw.dungeon.Player;

public class Portal extends Entity implements Collidable{
    public Portal(int x, int y, String name, int id){
        super(x, y, name);
        setId(id);
    }

    //can this entity be on the same tile as en?
    public boolean canCollide(Player player, Entity en){
        //portal teleport everthing
        return true;
    }

    public void collisionBehaviour(Player player){

        teleport((Entity) player, player);

    }
    // teleport an entity to the linked one
    public void teleport(Entity en, Player player){
        List<Entity> portals = player.getDungeon().getEntitiesById("Portal", getId());

        if (portals.size() < 2) {
			return;
        }
        Portal linkedPortal = (Portal) portals.get(0) != this ? (Portal) portals.get(0) : (Portal) portals.get(1);
        en.setX(linkedPortal.getX());
        en.setY(linkedPortal.getY());
    }
 
}
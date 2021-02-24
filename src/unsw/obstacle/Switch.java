package unsw.obstacle;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

public class Switch extends Entity implements Collidable{
    private boolean Triggered;

    public Switch(int x, int y, String name) {
		  super(x, y, name);
    }



    public boolean canCollide(Player player, Entity en){
      return true;
    }

    public void collisionBehaviour(Player player){
      //do nothing for now
    }
    public void setTriggered(boolean Triggered){
      this.Triggered = Triggered;
    }
    
    public boolean getTriggered(){
      return Triggered;
    }
}
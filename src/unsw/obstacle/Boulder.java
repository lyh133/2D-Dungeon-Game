package unsw.obstacle;

import unsw.dungeon.*;
import java.util.ArrayList;

public class Boulder extends Entity implements Collidable {

	public Boulder(int x, int y, String name) {
		super(x, y, name);
	}

	public boolean canCollide(Player player, Entity en){

		if(en instanceof Boulder) return false;
		if(en instanceof Wall) return false;
		if(en instanceof Exit) return false;
		if(en instanceof Enemy) return false;
		//when the player is colliding in the direction of two boulders or one boulder and a not collidable object , player cant collide
		if(en instanceof Player){

			String direction = pushDirection(player.getX(), player.getY(), this);
			Dungeon dungeon = player.getDungeon();

			if (direction.equals("right"))  {
				if(dungeon.BoulderAtPos(this.getX()+1,this.getY()) || dungeon.BlockedAtPos(this.getX()+1,this.getY()) ) return false;
			}
			else if (direction.equals("left")) {
				if(dungeon.BoulderAtPos(this.getX()-1,this.getY()) || dungeon.BlockedAtPos(this.getX()-1,this.getY())) return false;
			}
			else if (direction.equals("up") ) {
				if(dungeon.BoulderAtPos(this.getX(),this.getY()-1) || dungeon.BlockedAtPos(this.getX(),this.getY()-1)) return false;
			}
			else if (direction.equals("down")) {
				if(dungeon.BoulderAtPos(this.getX(),this.getY()+1) || dungeon.BlockedAtPos(this.getX(),this.getY()+1)) return false;
			}

		}
		return true;
	}


	public void collisionBehaviour(Player player){
		String direction = pushDirection(player.getLast_x(), player.getLast_y(), this);
		Dungeon dungeon = player.getDungeon();

		if (direction.equals("right") && dungeon.canMoveTo(this,getX() + 1, getY()))      {
			setX(getX() + 1);
		}
		else if (direction.equals("left") && dungeon.canMoveTo(this,getX() - 1, getY()))  {
			setX(getX() - 1);
		}
		else if (direction.equals("up") && dungeon.canMoveTo(this,getX(), getY() - 1))	  {
			setY(getY() - 1);
		}
		else if (direction.equals("down") && dungeon.canMoveTo(this,getX(), getY() + 1))  {
			setY(getY() + 1);
		}
		update(player);
	}


	//the direction to move after being pushed
	private String pushDirection(int x, int y, Boulder b) {
		if (x < b.getX()) {
			return "right";
		} else if (x > b.getX()) {
			return "left";
		} else if (y > b.getY()) {
			return "up";
		} else {	
			return "down";
		}
	}
	//teleport boulder when colliding portal
	private void portalCollision(Player player){
		ArrayList<Entity> ens = player.getDungeon().getEntitiesAtPos(getX(),getY());
		for(Entity entity : ens) {
			if(entity instanceof Portal){
				Portal portal = (Portal) entity;
				portal.teleport(this,player);
			}

		}
	}
	//update map after push
	private void update(Player player){

		//check for portal collison after being collided
		portalCollision(player);
		//update all swtiches after being collided		
		player.getDungeon().updateSwitch();

	}


	
}
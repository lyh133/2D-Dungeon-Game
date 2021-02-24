package unsw.goals;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;


public class GoalExit implements GoalComponent {
	private Dungeon d;

	
	public GoalExit(Dungeon d) {
		this.d = d;
	}


	@Override
	public boolean goalCompleted() {
        Player p = d.getPlayer();
        List<Entity> eList = d.getEntitiesAtPos(p.getX(), p.getY());


		for (Entity e : eList) {
			if (e.getName().equals("Exit")) {
				return true;
			}
		}

		return false;
	}
}

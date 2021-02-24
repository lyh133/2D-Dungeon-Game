package unsw.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;


public class GoalTreasure implements GoalComponent {
	private Dungeon d;

	
	public GoalTreasure(Dungeon d) {
		this.d = d;
	}


	@Override
	public boolean goalCompleted() {
		for (Entity e : d.getEntities()){
			if (e.getName().equals("Treasure")) {
	
				return false;
			}
		}
		return true;
	}
}

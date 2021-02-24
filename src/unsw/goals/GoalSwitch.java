package unsw.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.obstacle.Switch;


public class GoalSwitch implements GoalComponent {
	private Dungeon d;

	
	public GoalSwitch(Dungeon d) {
		this.d = d;
	}


	@Override
	public boolean goalCompleted() {
		for (Entity e : d.getEntities()) {
			if (e.getName().equals("Switch") && !((Switch) e).getTriggered()) {
				return false;
			}
		}

		return true;
	}
}

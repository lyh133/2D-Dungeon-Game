package unsw.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;


public class GoalEnemy implements GoalComponent {
	private Dungeon d;


	public GoalEnemy(Dungeon d) {
		this.d = d;
	}

	@Override
	public boolean goalCompleted() {
		for (Entity e : d.getEntities()) {
			if (e.getName().equals("Enemy")) {
				return false;
			}
		}

		return true;
	}
}

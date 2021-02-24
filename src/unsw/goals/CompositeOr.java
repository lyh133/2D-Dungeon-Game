package unsw.goals;

import java.util.List;


public class CompositeOr extends GoalComposite implements GoalComponent {
	
	public CompositeOr() {
		super();
	}

	@Override
	public boolean goalCompleted() {
		// Gets list of subgoals
		List<GoalComponent> subGoals = getSubGoals();

		// Ensures that at least 1 subgoal is completed
		for (GoalComponent g : subGoals) {
			if (g.goalCompleted()) {
				return true;
			}
		}

		return false;
	}
}

package unsw.goals;

import java.util.List;


public class CompositeAnd extends GoalComposite implements GoalComponent{
    public CompositeAnd() {
		super();
	}
	
	@Override
	public boolean goalCompleted() {
		// Gets list of subgoals
		List<GoalComponent> subGoals = getSubGoals();
		
		// Ensures that all subgoals are completed
		for (GoalComponent g : subGoals) {
			if (!g.goalCompleted()) {
				return false;
			}
		}
		
		return true;
	}
}
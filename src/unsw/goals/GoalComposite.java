
package unsw.goals;

import java.util.ArrayList;
import java.util.List;


public abstract class GoalComposite {
    private List<GoalComponent> subGoals;

    public GoalComposite(){
        subGoals = new ArrayList<GoalComponent>();
    }

    public void addSubGoal(GoalComponent goal){
        subGoals.add(goal);
    }

    public List<GoalComponent> getSubGoals(){
        return subGoals;
    }

    public abstract boolean goalCompleted();
    
}
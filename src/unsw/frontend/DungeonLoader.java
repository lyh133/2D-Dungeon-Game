package unsw.frontend;

import java.io.FileNotFoundException;
import java.io.FileReader;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.*;
import unsw.obstacle.*;
import unsw.item.*;
import unsw.goals.*;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * 
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        JSONObject goals = json.getJSONObject("goal-condition");
        GoalComponent g = loadGoals(dungeon, goals);
        dungeon.setGoals(g);

        dungeon.setEnemy();

        Projectile projectile = new Projectile(0, 19, "projectile");
        // this is the prime arrow (the one player fires, its special !)
        projectile.setCollectable(false);
        onLoad(projectile);
        dungeon.setProjectile(projectile);
        dungeon.addEntity((Entity) projectile);
        //if (dungeon.getEnemy() != null) dungeon.getEnemy().activate();
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
            case "player":
                Player player = new Player(dungeon, x, y, "Player");
                dungeon.setPlayer(player);
                onLoad(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(x, y, "Wall");
                onLoad(wall);
                entity = wall;
                break;
            case "boulder":
                Boulder boulder = new Boulder(x, y, "Boulder");
                onLoad(boulder);
                entity = boulder;
                break;

            case "door":
                Door door = new Door(x, y, "Door", json.getString("colour"));
                onLoad(door);
                entity = door;
                break;
            case "switch":
                Switch Switch = new Switch(x, y, "Switch");
                onLoad(Switch);
                entity = Switch;
                break;
            case "exit":
                Exit exit = new Exit(x, y, "Exit");
                onLoad(exit);
                entity = exit;
                break;
            case "sword":
                Sword sword = new Sword(x, y, "Sword");
                onLoad(sword);
                entity = sword;
                break;
            case "treasure":
                Treasure treasure = new Treasure(x, y, "Treasure");
                onLoad(treasure);
                entity = treasure;
                break;
            case "key":
                Key key = new Key(x, y, "Key", json.getString("colour"));
                onLoad(key);
                entity = key;
                break;
            case "invincibility":
                Potion potion = new Potion(x, y, "Potion");
                onLoad(potion);
                entity = potion;
                break;
            case "portal":
                Portal portal = new Portal(x, y, "Portal", json.getInt("id"));
                onLoad(portal);
                entity = portal;
                break;
            case "enemy":
                String name = json.getString("name");
                Enemy enemy = new Enemy(dungeon, x, y, name);

                if(enemy.getName().equals("boss")){
                    enemy.setHealth(500);
                    enemy.setSpeed(10);;
                    onLoadboss(enemy);
                    entity = enemy;
                    break;

                }
                onLoad(enemy);
                entity = enemy;
                break;
            case "hole":
                Hole hole = new Hole(x, y, "Hole");
                onLoad(hole);
                entity = hole;
                break;
            case "projectile":
                Projectile projectile = new Projectile(x, y, "projectile");
                onLoad(projectile);
                entity = projectile;
                break; 
            case "bow":
                Bow bow = new Bow(x, y, "bow");
                onLoad(bow);
                entity = bow;
                break;            

        }
        dungeon.addEntity(entity);
    }

    public void loadGoal(Dungeon dungeon, GoalComposite composite, JSONArray subGoal){
        for(int i = 0; i < subGoal.length(); i++){
            JSONObject goal = subGoal.getJSONObject(i);

            GoalComponent g = loadGoals(dungeon, goal);

            if(g != null){
                composite.addSubGoal(g);
            }
        }
    }

    public GoalComponent loadGoals(Dungeon dungeon, JSONObject jsonGoal) {
		String goal = jsonGoal.getString("goal");

		GoalComponent g = null;
		switch (goal) {
		case "exit":
			g = new GoalExit(dungeon);
			break;
		case "enemies":
			g = new GoalEnemy(dungeon);
			break;
		case "treasure":
			g = new GoalTreasure(dungeon);
			break;
		case "boulders":
			g = new GoalSwitch(dungeon);
			break;
		case "OR":
			g = new CompositeOr();
			JSONArray orSubGoals = jsonGoal.getJSONArray("subgoals");
			loadGoal(dungeon, (CompositeOr) g, orSubGoals);
			break;
		case "AND":
			g = new CompositeAnd();
			JSONArray andSubGoals = jsonGoal.getJSONArray("subgoals");
			loadGoal(dungeon, (CompositeAnd) g, andSubGoals);
			break;
		}
		return g;
	}



    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Switch Switch);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Potion potion);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoadboss(Enemy enemy);
    public abstract void onLoad(Hole hole);
    public abstract void onLoad(Projectile projectile);
    public abstract void onLoad(Bow bow);




}

      

    
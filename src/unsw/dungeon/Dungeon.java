/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import unsw.item.Collectable;
import unsw.item.Projectile;
import unsw.obstacle.*;
import unsw.goals.*;




/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Projectile projectile;
    private GoalComponent goals;
    private StringProperty gameState;
    private ArrayList<Enemy> enemies;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.enemies = new ArrayList<>();
        //this.enemy = findEnemyOBJ();
        gameState = new SimpleStringProperty();
        gameState.setValue("Playing");
    }

    //search for all enemies and add em all!
    public void setEnemy() {
        for(Entity en : this.entities){
            if(en instanceof Enemy){
                this.enemies.add((Enemy)en);
            }
        }
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }
    public Projectile getProjectile() {
        return projectile;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities(){
        return entities;
    }
    public void deleteEntity(Collectable item) {
        Entity en = (Entity) item;
        en.setY(19);
        en.setX(0);
        entities.remove(en);
    }
    public void deleteEntity(Collidable clb) {
        Entity en = (Entity) clb;
        en.setY(19);
        en.setX(0);
        entities.remove(en);
    }

    public boolean removeEntity(Entity e){
        e.setX(19);
        e.setY(0);
        return entities.remove(e);
    }
    public void deleteEntity(Enemy enemy) {
        entities.remove(enemy);
    }
    //enemy moves one step based on its ai
    public void enemyMove(Enemy en){
        en.makeDecision();
    }


    //can entity move to x,y?
    public boolean canMoveTo(Entity me,int x, int y){

        for (Entity entity: entities){
            if(entity != null){
                if(entity.getX() == x && entity.getY() == y){

                    if(entity instanceof Collidable) {
                        Collidable clb = (Collidable) entity;
                        if(!clb.canCollide(player,me)){
                            return false;
                        }
                    }
                    
                }
            }
        }
        return true;
    }

    //there exist Boulder at (x,y)?
    public boolean BoulderAtPos(int x, int y ){
        ArrayList<Entity> eList = getEntitiesAtPos(x, y);

        for(Entity en : eList){
            if(en instanceof Boulder) return true;
        }

        return false;
    }
    public boolean SwtichAtPos(int x, int y){
        ArrayList<Entity> entities = getEntitiesAtPos(x, y);

        for(Entity en : entities){
            if(en instanceof Switch) return true;
        }

        return false;
    }
    //is there a not collidable obj at x,y
    public boolean BlockedAtPos(int x, int y){
        ArrayList<Entity> entities = getEntitiesAtPos(x, y);
        for(Entity en : entities){
            if(en instanceof Collidable){
                Collidable clb = (Collidable) en;
                if(!clb.canCollide(player, player)) return true;
            }
        }
        return false;
    }
    //is enemy blocked at X,Y
    public boolean EnemyBlockedAtPos(Enemy enemy,int x, int y){
        ArrayList<Entity> entities = getEntitiesAtPos(x, y);
        for(Entity en : entities){
            if(en instanceof Collidable){
                Collidable clb = (Collidable) en;
                if(!clb.canCollide(player, enemy)) return true;
            }
        }
        return false;
    }


    public ArrayList<Entity> getEntitiesAtPos(int x, int y) {
		ArrayList<Entity> eList = new ArrayList<Entity>();
		// Goes through entities
		for (Entity e : entities) {
			if (e.getX() == x && e.getY() == y) {
				eList.add(e);
			}

		}

		return eList;
    }
	public boolean existEntity(Entity ene,int x, int y){
        ArrayList<Entity> en = getEntitiesAtPos(x,y);
        for(Entity entity : en){
            if(entity.equals(ene)){
                return true;
            }
        }
        return false;
    }

    //there exist a swtich and a  boulder at the same tile?
    public boolean boulderOnswtich(int x, int y){
        return BoulderAtPos(x, y) && SwtichAtPos(x, y);
    }

    
    //update all switches
    public void updateSwitch(){
        for(Entity en : this.entities){
            if(en instanceof Switch){
                Switch sw = (Switch) en;
                if(BoulderAtPos(sw.getX(), sw.getY())){
                    sw.setTriggered(true);
                }
                else{
                    sw.setTriggered(false);
                }
            }
        }

    }
    
	
    
    public List<Entity> getEntitiesById(String name, int id) {
		List<Entity> eList = new ArrayList<Entity>();
		for (Entity e : entities) {
			if (e != null) {
				if (e.getName().equals(name) && e.getId() == id) {
					eList.add(e);
				}
			}
		}

		return eList;
    }
    
    public void setGoals(GoalComponent goals) {
        this.goals = goals;
    }
    
    public boolean goalsComplete(){
        if(goals.goalCompleted()){
            gameState.setValue("Win");
            return true;
            
        }
        return false;
    }

    

    public StringProperty getGameState(){
        return gameState;
    }
    



}

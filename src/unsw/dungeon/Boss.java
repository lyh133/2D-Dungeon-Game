package unsw.dungeon;

import unsw.enemy.EnemyAi;
import unsw.obstacle.*;


public class Boss extends Entity implements Collidable{

    private EnemyAi ai;
    private Dungeon dungeon;
    private boolean isAlive;
    private int speed;
    private int refreshMax;
    private int refreshPoint;
    private int health;
    public Boss(Dungeon dungeon, int x, int y, String name){
        super(x, y, name);
        this.dungeon = dungeon;
        ai = new EnemyAi(this,this.dungeon);
        this.isAlive = true;
        this.health = 500;
        this.speed = 10;
        this.refreshMax = 1000;
        this.refreshPoint = 0;
    }
    // how much refreshpoint increase by per game update
    public int getSpeed() {
        return speed;
    }
    // when refresh point exceed refresh max, update enemy
    public int getRefreshMax() {
        return refreshMax;
    }
    public int getRefreshPoint() {
        return refreshPoint;
    }
    public void setRefreshPoint(int refreshPoint) {
        this.refreshPoint = refreshPoint;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    //can this entity be on the same tile as en?
    public boolean canCollide(Player player, Entity en){
        if(en instanceof Collidable){
            if(en instanceof Boulder) return false;
            if(en instanceof Door){
                Door door = (Door) en;
                if(door.getState().getValue() == false) return false;
            }
            if(en instanceof Wall) return false;
            if(en instanceof Exit) return false;




        }
        return true;
    }
    public void collisionBehaviour(Player player){
        //kills player
        kill(player);
        
    }

    public void makeDecision(){
        this.ai.ai_Run();
    }

    public void kill(Player player){
        player.die();
    }
    
    public boolean getisAlive(){
        return this.isAlive;
    }
    public void takeDamage(int dmg){
        this.health -= dmg;
    }

    public void die(Dungeon dungeon){
        if(this.isAlive == false) return;
        isAlive = false;
        dungeon.deleteEntity(this);
    }








    
}
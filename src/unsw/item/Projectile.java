package unsw.item;

import unsw.dungeon.*;
import unsw.obstacle.*;

public class Projectile extends Entity implements Collectable{
    
    private int speed;
    private int refreshMax;
    private int refreshPoint;
    private boolean inMotion;
    private String direction;
    private int damage;
    private boolean collectable;
	public Projectile(int x, int y, String name) {
        super(x, y, name);
        this.speed = 150;
        this.refreshMax = 1000;
        this.refreshPoint = 0;
        this.inMotion = false;
        this.damage = 50;
        this.collectable = true;
    }
    // each game update, point gets increased by speed,
    // when point => max, this object updates once
    public int getSpeed() {
        return speed;
    }
    public int getRefreshMax() {
        return refreshMax;
    }
    public int getRefreshPoint() {
        return refreshPoint;
    }
    public void setRefreshPoint(int refreshPoint) {
        this.refreshPoint = refreshPoint;
    }
    public boolean getInmotion(){
        return this.inMotion;
    }
    public boolean getcollectable(){
        return collectable;
    }
    public void setCollectable(boolean collectable) {
        this.collectable = collectable;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    // what happens when it collides
    public void onCollision(Entity entity){
        if(entity instanceof  Wall){
            removethis();
            setMotion(false);
        }
        if(entity instanceof Enemy){
            ( (Enemy) entity).takeDamage(damage);
            System.out.println("dealt"+damage+"damage to enemy!");
            removethis();
            setMotion(false);
        }
        if(entity instanceof Door){
            if(((Door) entity).getState().getValue() == false){
                removethis();
                setMotion(false);
            }
        }

    }
    //make arrow "disappear"
    private void removethis(){
        super.x().set(0);
        super.y().set(19);
    }
    //instantiate an arrow at player pos
    public void instantiate(Player player){
        super.x().set(player.getX());
        super.y().set(player.getY());
    }
    //move according to player's facing
    public void moveOnce(Player player){
        
        if(direction == "^") {
            super.y().set(super.getY()-1);
            return;
        }
        if(direction == "v") {
            super.y().set(super.getY()+1);
            return;
        }
        if(direction == ">") {
            super.x().set(super.getX()+1);
            return;
        }
        if(direction == "<") {
            super.x().set(super.getX()-1);
            return;
        }

        
    }


    public void collectBehaviour(Dungeon dun, Inventory inventory){
        // the arrow player fires is none collectable !
        if(collectable == false) return;
        dun.deleteEntity(this);
        dun.getPlayer().getInventory().getArrowCount().set(dun.getPlayer().getInventory().getArrowCount().get() + 10);
    }

    public void setMotion(boolean bool){
        this.inMotion = bool;
    }
}
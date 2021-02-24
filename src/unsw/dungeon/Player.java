package unsw.dungeon;

import java.util.ArrayList;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.item.*;
import unsw.obstacle.*;
/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Inventory inventory;
    private int last_x,last_y;
    private IntegerProperty swordDurability;
    private boolean isInvincible;
    private int invincibleTime;
    private boolean isAlive;
    private BooleanProperty invincible;
    private BooleanProperty swordequipt;
    private String facingDirection;
    private BooleanProperty bowequipt;

    /**
     * Create a player positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, String name) {
        super(x, y, name);
        this.dungeon = dungeon;
        this.inventory = new Inventory();
        this.last_x = x;
        this.last_y = y;
        this.swordDurability = new SimpleIntegerProperty(0);
        this.isInvincible = false;
        this.invincibleTime = 0;
        this.isAlive = true;
        this.invincible = new SimpleBooleanProperty();
        this.invincible.setValue(false);
        this.swordequipt = new SimpleBooleanProperty();
        this.swordequipt.setValue(false);
        this.bowequipt = new SimpleBooleanProperty();
        this.facingDirection = "";
    }
    public BooleanProperty getInvincible() {
        return invincible;
    }
    public BooleanProperty getBowequipt() {
        return bowequipt;
    }

    public boolean getisAlive(){
        return this.isAlive;
    }
    public IntegerProperty getSwordDurability() {
        return swordDurability;
    }
    public boolean getisInvincible(){
        return isInvincible;
    }
    public void setInvincibleTime(int invincibleTime) {
        this.invincibleTime = invincibleTime;
    }
    public int getInvincibleTime() {
        return invincibleTime;
    }
    public void setInvincible(boolean bool){
        this.isInvincible = bool;
    }
    public BooleanProperty getSwordequipt() {
        return swordequipt;
    }
    public void setSwordDurability(int num){
        this.swordDurability.set(num);
        if(this.swordDurability.get() != 0){
            this.swordequipt.setValue(true);
        }
    }
    public void swinSword(Entity en){
        if (en instanceof Enemy){
            Enemy enemy = (Enemy) en;
            enemy.die(this.dungeon);
            this.swordDurability.set(swordDurability.get() - 1);;
            if(this.swordDurability.get() == 0){
                this.swordequipt.setValue(false);
            }
        }
    }

    public Dungeon getDungeon() {
        return dungeon;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public int getLast_x() {
        return last_x;
    }
    public int getLast_y() {
        return last_y;
    }

    public void useKey(){
        this.inventory.setKeyColour("default");
    }

    //player has key of id?
    public boolean hasKey_colour(String colour){
        return inventory.hasKey_colour(colour);
    }
    public void die(){
        this.isAlive = false;
        
    }



    private boolean canMoveUp(){
        return (getY() > 0 && dungeon.canMoveTo(this, getX(), getY() - 1));
    }
    private boolean canMoveDown(){
        return (getY() < dungeon.getHeight() - 1 && dungeon.canMoveTo(this, getX(), getY() + 1));
    }
    private boolean canMoveLeft(){
        return (getX() > 0 && dungeon.canMoveTo(this, getX()-1, getY()));
    }
    private boolean canMoveRight(){
        return (getX() < dungeon.getWidth() - 1 && dungeon.canMoveTo(this, getX()+1, getY()));
    }

    private void setFacing(int x, int y,int last_x, int last_y){
        if(x == last_x && last_y > y){
            this.facingDirection = "^";
            return;
        }
        if(x == last_x && last_y < y){
            this.facingDirection = "v";
            return;
        }
        if(y == last_y && x < last_x){
            this.facingDirection = "<";
            return;
        }
        if(y == last_y && x > last_x) {
            this.facingDirection = ">";
            return;
        }

    }

    public String getFacingDirection() {
        return facingDirection;
    }

    public void moveUp() {
        if (canMoveUp()) {
            this.last_y = getY();
            this.last_x = getX();
            y().set(getY() - 1);
            setFacing(getX(),getY(), getLast_x(), getLast_y());

        }
    }
    public void moveDown() {
        if (canMoveDown()) {
            this.last_y = getY();
            this.last_x = getX();
            y().set(getY() + 1);
            setFacing(getX(),getY(), getLast_x(), getLast_y());
        }

    }

    public void moveLeft() {
        if (canMoveLeft()) {
            this.last_y = getY();
            this.last_x = getX();
            x().set(getX() - 1);
            setFacing(getX(),getY(), getLast_x(), getLast_y());
        }

    }

    public void moveRight() {
        if (canMoveRight()){
            this.last_y = getY();
            this.last_x = getX();
            x().set(getX() + 1);
            setFacing(getX(),getY(), getLast_x(), getLast_y());
        }

    }
  
    //player use potion
    public void usePotion(Potion potion){
        // if no potion do nothing
        if(inventory.getPotionCount().get() == 0) return;

        potion.startPotionEffect(this);
        this.inventory.getPotionCount().set(inventory.getPotionCount().get() - 1);
    }






    // player's duration time tick by milisecond
    public void elapseTime(int milisecond){
        this.invincibleTime -= milisecond;
    }
    

    public boolean isCollision(){

        for(Entity en : dungeon.getEntities() ) {
            if(this.getX() == en.getX() && this.getY() == en.getY() && !(en instanceof Player)){

                return true;
            }
        }
        return false;
    }

    //return a list of objects that is in the same tile as the player
    public ArrayList<Entity> getCollisionObj(){
        ArrayList<Entity> entities = new ArrayList<>(); 
        for(Entity en : dungeon.getEntities() ) {   

            if(this.getX() == en.getX() && this.getY() == en.getY() ){
                entities.add(en);
            }
        }
        return entities;
    }

    // handle collision behaviours
    public void collisionHandler(){

        if(isCollision()){
        
            ArrayList<Entity> entities = getCollisionObj();
            for(Entity en : entities){
                if(en instanceof Collectable){
                    collectEffect( (Collectable) en);
                }
                else if(en instanceof Collidable){
                    collisionEffect( (Collidable) en);
                }
            }

        }
    }

    private void collectEffect(Collectable item){
        item.collectBehaviour(dungeon, inventory);
        //System.out.println(inventory.getCount(item));

    }
    private void collisionEffect(Collidable clb) {
        clb.collisionBehaviour(this);
        
    }

    
}
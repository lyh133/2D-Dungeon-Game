package unsw.item;
import unsw.dungeon.*;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Inventory{

    private ArrayList<Collectable> items;
    private HashMap<Collectable, Integer> itemCount;
    //private Collectable collectable;
    private IntegerProperty arrowCount;
    private IntegerProperty treasureCount;
    private IntegerProperty potionCount;
    private StringProperty keyColour;
    private BooleanProperty  is_red;
    private BooleanProperty is_blue;
    private BooleanProperty is_yellow;
    private BooleanProperty no_key;

    public Inventory() {
        //super(x, y, name);
        this.items = new ArrayList<>();
        this.arrowCount = new SimpleIntegerProperty(0);
        this.treasureCount = new SimpleIntegerProperty(0);
        this.potionCount = new SimpleIntegerProperty(0);
        this.keyColour = new SimpleStringProperty("default");
        this.is_blue= new SimpleBooleanProperty(false);
        this.is_red =new SimpleBooleanProperty(false);
        this.is_yellow = new SimpleBooleanProperty(false);
        this.no_key = new SimpleBooleanProperty(true);
        this.itemCount= new HashMap<>();
    }
    
    public IntegerProperty getTreasureCount() {
        return treasureCount;
    }
    public IntegerProperty getPotionCount() {
        return potionCount;
    }
    public IntegerProperty getArrowCount() {
        return arrowCount;
    }
    public StringProperty getKeyColour() {
        return keyColour;
    }
    public BooleanProperty getIs_blue() {
        return is_blue;
    }
    public BooleanProperty getIs_yellow() {
        return is_yellow;
    }
    public BooleanProperty getIs_red() {
        return is_red;
    }
    public BooleanProperty getnokey() {
        return no_key;
    }

    public void addItem(Collectable itm){

        if(itm instanceof Treasure) {
            this.treasureCount.set(this.treasureCount.get() + 1);
        }
        if(itm instanceof Potion) {
            this.potionCount.set(this.potionCount.get() + 1);
        }
        if(itm instanceof Key) {
            Key key = (Key) itm;
            setKeyColour(key.getColour());
        }


        items.add(itm);
        if(itemCount.get(itm) == null  ){
            itemCount.put(itm, 1);
        }
        else{
            int count = itemCount.get(itm);
            count += 1;
            itemCount.put(itm,count);
        }
    }
    public void deleteItem(Collectable itm){
        this.items.remove(itm);
        this.itemCount.remove(itm);
    }
    public boolean hasItem(Collectable itm){
        for(Collectable clb : this.items){
            if(itm.equals(clb)) return true;
        }
        return false;
    }
    //has a key of id?
    public boolean hasKey_colour(String colour){
        if(this.keyColour.getValue().equals(colour)) return true;
        return false;
    }

    
    public void setKeyColour(String colour) {
        this.keyColour.setValue(colour);
        if(colour.equals("red")){
            this.is_blue.setValue(false);
            this.is_red.setValue(true);
            this.is_yellow.setValue(false);
            return;
        }
        if(colour.equals("yellow")){
            this.is_blue.setValue(false);
            this.is_red.setValue(false);
            this.is_yellow.setValue(true);
            return;
        }
        if(colour.equals("blue")){
            this.is_blue.setValue(true);
            this.is_red.setValue(false);
            this.is_yellow.setValue(false);
            return;
        }
        this.is_blue.setValue(false);
        this.is_red.setValue(false);
        this.is_yellow.setValue(false);
        this.no_key.setValue(true);
        
    }
    
    public Entity findByName(String name){
        for(Collectable clb : this.items){
            Entity en = (Entity) clb;
            if(en.getName().equals(name)) return en;
        }
        return null;
    }
    
    
    public int getCount(Collectable item) {
        if(itemCount.get(item) == null) return 0;
        //System.out.println(clb);

        return itemCount.get(item);
    }




}
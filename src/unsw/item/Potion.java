package unsw.item;

import unsw.dungeon.*;


public class Potion extends Entity implements Collectable{

    //how long potion last in milisecond 
    public int duration;
    public Potion(int x, int y, String name){
        super(x, y, name);
        this.duration = 1000;
    }

    public void collectBehaviour(Dungeon dun, Inventory inv) {
        inv.addItem(this);
        dun.deleteEntity(this);
        
        
        //potion effect fades away after duration
        // new java.util.Timer().schedule( 
        //     new java.util.TimerTask() {
        //         @Override
        //         public void run() {
        //             dun.getPlayer().setInvincible(false);
        //             System.out.println("Potion effect faded away");
        //         }
        //     }, 
        //     this.duration
        // );

    
    }

    public void startPotionEffect(Player player){
        player.setInvincible(true);
        player.getInvincible().setValue(true);
        player.setInvincibleTime(this.duration);
    }



}
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.item.Sword;
import unsw.item.*;


public class US2_2{

    Dungeon dun;  		// the dungeon
    Player p1;			// the playe
    Sword s1;           // the sword
    

    // Colliding with a sword without one already equipped will delete sword from the grid, gains its effect and does not go into inventory
    @Test
    public void testUS2_2_1() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        // Places sword at coordinates (2, 1)
        s1 = new Sword(2, 3, "Sword");
		dun.addEntity(s1);
        assertEquals(dun.existEntity(s1, 2, 3),true);
		// Player pick up the sword
        p1.moveDown();
        assertEquals(p1.getInventory().hasItem(s1), false);
        //has effect
        assertEquals(p1.getSwordDurability(), 5);
        //no sword anymore
        assertEquals(dun.existEntity(s1, 2, 3),false);
        

    }

// If the player carries a sword then upon collision while facing the enemy, the enemy is deleted from the grid
    @Test
    public void testUS2_2_2() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        Enemy e1 = new Enemy(2, 3, "Enemy");
        dun.addEntity(e1);
        assertEquals(dun.existEntity(e1, 2, 3),true);
        // give player sword
        p1.addSwordDurability(5);
        p1.moveDown();
        //enemy got deleted
        assertEquals(dun.existEntity(e1, 2, 3),false);
        




    }
// Newly equip sword has 5 durability
    @Test
    public void testUS2_2_3() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        s1 = new Sword(2, 3, "Sword");
		dun.addEntity(s1);
        assertEquals(dun.existEntity(s1, 2, 3),true);
		// Player pick up the sword
        p1.moveDown();
        assertEquals(p1.getSwordDurability(), 5);


    }
// If killing enemy then sword lose one durability
    @Test
    public void testUS2_2_4() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        Enemy e1 = new Enemy(2, 3, "Enemy");
        dun.addEntity(e1);
        assertEquals(dun.existEntity(e1, 2, 3),true);
        // give player sword
        p1.addSwordDurability(5);
        assertEquals(p1.getSwordDurability(), 5);
        p1.moveDown();

        //enemy got deleted
        assertEquals(dun.existEntity(e1, 2, 3),false);
        // now has 4 durability
        assertEquals(p1.getSwordDurability(), 4);
        

    }
// while under the effect of invincibility potion, sword don't lose durability
    @Test
    public void testUS2_2_5() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        Enemy e1 = new Enemy(2, 3, "Enemy");
        dun.addEntity(e1);
        assertEquals(dun.existEntity(e1, 2, 3),true);
        // give player sword
        p1.addSwordDurability(5);
        assertEquals(p1.getSwordDurability(), 5);
        
        Potion po1 = new Potion(1,2, "Potion");
        dun.addEntity(po1);
        //picks up potion
        p1.moveLeft();
        assertEquals(p1.getisInvincible(), true);
        p1.moveRight();
        p1.moveDown();
        //enemy got deleted
        assertEquals(dun.existEntity(e1, 2, 3),false);
        // still has 5 durability
        assertEquals(p1.getSwordDurability(), 5);      

        

    }
// When the sword's durability becomes 0 it disappears and the player loses its effect
    @Test
    public void testUS2_2_6() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        dun.addEntity(p1);
        Enemy e1 = new Enemy(2, 3, "Enemy");
        dun.addEntity(e1);
        assertEquals(dun.existEntity(e1, 2, 3),true);
        // give player sword
        p1.addSwordDurability(1);
        assertEquals(p1.getSwordDurability(), 1);

        p1.moveDown();

        //enemy got deleted
        assertEquals(dun.existEntity(e1, 2, 3),false);


        //now player dont have a sword
        assertEquals(p1.getSwordDurability(), 0);

        Enemy e2 = new Enemy(2, 4, "Enemy2");
        dun.addEntity(e2);

        // enemy won't die
        p1.moveLeft();
        assertEquals(dun.existEntity(e2, 2, 4),true);



        


    }

}
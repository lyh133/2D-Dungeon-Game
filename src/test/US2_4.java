package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.item.Treasure;

public class US2_4 {
    Dungeon dun;  		// the dungeon
	Player p1;			// the player
// Game starts with recorded treasure = 0
    @Test
    public void testUS2_4_1() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        Treasure t1 = new Treasure(2,3,"Treasure");
        dun.addEntity(t1);
        dun.setPlayer(p1);
        assertEquals(p1.getInventory().getCount(t1), 0);
        

        

    }
// when player collide with treasure, they are deleted from the grid
    @Test
    public void testUS2_4_2() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        Treasure t1 = new Treasure(2,3,"Treasure");
        dun.addEntity(t1);



    }
// Treasure when collected have their recorded total amount increased by one
    @Test
    public void testUS2_4_3() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        Treasure t1 = new Treasure(2,3,"Treasure");
        dun.addEntity(t1);
        dun.setPlayer(p1);
        // has 0
        assertEquals(p1.getInventory().getCount(t1), 0);
        p1.moveDown();
        // now has 1
        assertEquals(p1.getInventory().getCount(t1), 1);
        p1.moveUp();
        p1.moveDown();
        // wont increase casue deleted
        assertEquals(p1.getInventory().getCount(t1), 1);




    }

}
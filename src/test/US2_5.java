package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.item.*;
import unsw.obstacle.*;

public class US2_5 {
    Dungeon dun;  		// the dungeon
    Player p1;			// the player
    

// Game starts with no recorded key
    @Test
    public void testUS2_5_1() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        Key k1 = new Key(2,3 ,"Key",1);
        dun.addEntity(k1);
        assertEquals(p1.getInventory().getCount(k1), 0);
    }
// Player can collect key by colliding with it
    @Test
    public void testUS2_5_2() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        Key k1 = new Key(2,3 ,"Key",1);
        dun.addEntity(k1);
        assertEquals(p1.getInventory().getCount(k1), 0);

        p1.moveDown();
        assertEquals(p1.getInventory().getCount(k1), 1);

    }
// Player can carry one key at a time
    @Test
    public void testUS2_5_3() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// Collected key is recorded
    @Test
    public void testUS2_5_4() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        Key k1 = new Key(2,3 ,"Key",1);
        dun.addEntity(k1);
        assertEquals(p1.getInventory().getCount(k1), 0);

        p1.moveDown();
        assertEquals(p1.getInventory().getCount(k1), 1);

    }
// Key is deleted from the grid when collected by the player
    @Test
    public void testUS2_5_5() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        Key k1 = new Key(2,3 ,"Key",1);
        dun.addEntity(k1);
        assertEquals(p1.getInventory().getCount(k1), 0);

        p1.moveDown();
        assertEquals(p1.getInventory().getCount(k1), 1);
        assertEquals(dun.existEntity(k1, 2, 3),false);

    }
// Colliding with a key do nothing if a player already carry a key
    @Test
    public void testUS2_5_6() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        Key k1 = new Key(2,3 ,"Key",1);
        dun.addEntity(k1);
        assertEquals(p1.getInventory().getCount(k1), 0);

        p1.moveDown();
        assertEquals(p1.getInventory().getCount(k1), 1);
        Key k2 = new Key(2,4 ,"Key",2);
        dun.addEntity(k2);
        p1.moveDown();
        
        assertEquals(p1.getInventory().getCount(k2), 1);



    }
// The key can only open the corresponding door
    @Test
    public void testUS2_5_7() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        Key k1 = new Key(2,3 ,"Key",1);
        dun.addEntity(k1);
        assertEquals(p1.getInventory().getCount(k1), 0);

        p1.moveDown();
        assertEquals(p1.getInventory().getCount(k1), 1);

        Door d1 = new Door (2,4, "Door1",2);
        dun.addEntity(d1);
        Door d2 = new Door (1,3, "Door2",1);
        dun.addEntity(d1);
        p1.moveDown();
        //player was blocked becasue d1 didnt match and open
        assertEquals(p1.getX(), 2);
        assertEquals(p1.getY(), 3);
        //player was able to move left becasue d2 matched and opened
        p1.moveLeft();
        assertEquals(p1.getX(), 1);
        assertEquals(p1.getY(), 3);



    }
}


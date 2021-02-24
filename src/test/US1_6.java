package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.obstacle.*;



public class US1_6 {


    Dungeon dun;  		// the dungeon
    Player p1;			// the player
    Portal portal1a;    // portal1a
    Portal portal1b;    // protal1
    Boulder boulder;    // the boulder

    
    
    // Every portal should have a link to another portal with the same id.
    @Test
	public void testUS1_6_1() {

		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        portal1a = new Portal(2, 3, "Portal", 1);
        dun.addEntity(portal1a);
        portal1b = new Portal(5, 5, "Portal", 1);
        dun.addEntity(portal1b);

        
        p1.moveDown();

        
        assertEquals(p1.getX(), 5);
		assertEquals(p1.getY(), 5);

    }
    // If a player/boulder/enemy collides a portal they are to be teleported to the corresponding linked portal.   
    @Test
	public void testUS1_6_2() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 1, "Player");
        dun.setPlayer(p1);
        portal1a = new Portal(2, 3, "Portal", 1);
        dun.addEntity(portal1a);
        portal1b = new Portal(5, 5, "Portal", 1);
        dun.addEntity(portal1b);
        boulder = new Boulder(2,2, "Boulder");
        dun.addEntity(boulder);
        p1.moveDown();

        
        assertEquals(boulder.getX(), 5);
		assertEquals(boulder.getY(), 5);

       
        



    }
}
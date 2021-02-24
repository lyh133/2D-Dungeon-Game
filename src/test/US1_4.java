package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.obstacle.*;


public class US1_4 {
    Dungeon dun;  		// the dungeon
    Player p1;			// the player
    Entity boulder;     // the boulder
    Entity boulder2;    // the second boulder
    Entity door;        // the closed door
    Enemy Enemy;        // the enemy
    
    

    
// The player can only push one boulder at each time.
    @Test
	public void testUS1_4_1() {
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        
        //add a new boulder
        boulder = new Boulder(2, 3, "Boulder");
        dun.addEntity(boulder);
        boulder2 = new Boulder(2, 4, "Boulder");
        dun.addEntity(boulder2);

        //player cannot move down to push the boulder
        p1.moveDown();
		assertEquals(p1.getX(), 2);
        assertEquals(p1.getY(), 2);

        

    }
// The player can only push a boulder where adjacent.
    @Test
	public void testUS1_4_2() {
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        
        //add a new boulder
        boulder = new Boulder(2, 3, "Boulder");
        dun.addEntity(boulder);
        
        
        //player can move down to push the boulder
        p1.moveDown();
		assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 4);
        
    
    }
// Boulder cannot collide with Wall/Monster/Boulder/ClosedDoor/Exit
    @Test
	public void testUS1_4_3() {
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);


        //add a new boulder
        boulder = new Boulder(2, 3, "Boulder");
        dun.addEntity(boulder);

        //add a new Door
        door = new Door(2, 4, "Door", 1);
        dun.addEntity(door);

        //player cannot move dowe, stay at(2,2)
        p1.moveDown();
		assertEquals(p1.getX(), 2);
        assertEquals(p1.getY(), 2);

    }
}
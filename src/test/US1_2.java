package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.obstacle.*;


public class US1_2 {
    Dungeon dun;  		// the dungeon
    Player p1;			// the player
    Entity wall;        // the wall
    Entity boulder;     // the boulder
    
// player/enemy can't move through a wall
    @Test
	public void testUS1_2_1() {
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");

        wall = new Wall(2,3,"Wall");
		dun.addEntity(wall);


		p1.moveDown();
		assertEquals(p1.getX(), 2);
		assertEquals(p1.getY(), 2);
    }
// boulder cannot be moved into a wall
    @Test
	public void testUS1_2_2() {
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);
        
        //add a new boulder
        boulder = new Boulder(2, 3, "Boulder");
        dun.addEntity(boulder);
        // add a new wall below of the boulder
        wall = new Wall(2, 4, "Wall");
        dun.addEntity(wall);

        //player cannot move down to push the boulder
        p1.moveDown();
		assertEquals(p1.getX(), 2);
		assertEquals(p1.getY(), 2);
        

    }

    // enemy cannot be moved into a wall
    @Test
	public void testUS1_2_3() {
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
    }
}
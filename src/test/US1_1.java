
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.obstacle.*;
import unsw.item.*;

public class US1_1 {
    Dungeon dun;  		// the dungeon
	Player player;		// the player
	Entity w1;			// the wall
	Entity d1;			// the closed door
	Entity k1;			// the key
	Entity s1;			// the sword
	Entity t1;			// the treasure
	Entity p1;			// the potion
    

    //test for the player can only move up, down, left and right one square at a time.
    @Test
	public void testUS1_1_1() {
		dun = new Dungeon(10,10);
		player = new Player(dun, 2, 2, "Player");

		//player move to up square
		player.moveUp();   
        assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);
		
		//move to down square
        player.moveDown();   
        assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 2);
		

		//move to right square
		player.moveRight();  
		assertEquals(player.getX(), 3);
		assertEquals(player.getY(), 2);

		//move to left square
		player.moveLeft();   
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 2);
		
	}

	// The player can't move into a square that is an obstacle (a wall or closed door).
	@Test
	public void testUS1_1_2() {
		dun = new Dungeon(10,10);
		player = new Player(dun, 2, 2, "Player");
		dun.setPlayer(player);
		//test for a wall

		w1 = new Wall(2,3,"Wall");
		dun.addEntity(w1);


		player.moveDown();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 2);

		//test for a closed door
		//create a new closed door
		d1 = new Door(3,2, "Door", 1);
		dun.addEntity(d1);

		//player cannot move into a closed door
		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 2);


	}

	// The player can move across floor switches.
	@Test
	public void testUS1_1_3() {
		dun = new Dungeon(10,10);
		player = new Player(dun, 2, 2, "Player");
		dun.setPlayer(player);

		//test for switch

		s1 = new Switch(2,3,"Switch");
		dun.addEntity(s1);


		player.moveDown();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 3);
	}


	// The player can pass through treasure, keys, swords and potions.
	@Test
	public void testUS1_1_4() {
		dun = new Dungeon(10,10);
		player = new Player(dun, 2, 2, "Player");
		dun.setPlayer(player);
		
		
		//test for a treasure
		t1 = new Treasure(2,3,"Treasure");
		dun.addEntity(t1);


		player.moveDown();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 3);

		//test for a key
		k1 = new Key(2,4,"Key",1);
		dun.addEntity(k1);


		player.moveDown();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 4);

		//test for a potion
		p1 = new Potion(2,5,"Potion");
		dun.addEntity(k1);


		player.moveDown();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 5);
		
	}



	// Test for The player can't move out of dungeon grid
	@Test
	public void testUS1_1_5() {
		dun = new Dungeon(10,10);
		player = new Player(dun, 0, 0, "Player");

		player.moveUp();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);
	}

}
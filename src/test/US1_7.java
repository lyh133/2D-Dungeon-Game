package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.obstacle.Door;
import unsw.item.*;


public class US1_7 {

    Dungeon dun;  		// the dungeon
    Door  door;       // the closed door
    Player  p1;			// the player
    Key  key;        // a key
    Inventory inventory;
    

// Doors have two states, Open and closed
    @Test
	public void testUS1_7_1() {
        //create new dungeon and add a new closed door into the dungeon at (2,2)
		dun = new Dungeon(10,10);
        door = new Door(2, 2, "Door", 1);
        dun.addEntity(door);

        //check door state is closed
        assertEquals(door.getState(), "closed");


        //change the door state into open and check it
        door.open();
        assertEquals(door.getState(), "opened");

    }
        
// Closed Doors blocks player movement similar to a wall
    @Test
	public void testUS1_7_2() {
        //create new dungeon and add a player into the dungeon at (2,2)
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        //create a closed dorr at (3,2)
        door = new Door(3, 2, "Door", 1);
        dun.addEntity(door);
        
        //player cannot move righ into (3,2)
        p1.moveRight();
        assertEquals(p1.getX(), 2);
        assertEquals(p1.getY(), 2);


    }
        
// Opened doors act as an empty tile, other game Objects can be on top of it
    @Test
	public void testUS1_7_3() {
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);


        //create a closed dorr at (3,2)
        door = new Door(3, 2, "Door", 1);
        dun.addEntity(door);

        //change the door state into open
        door.open();

        //player can move righ into (3,2)
        p1.moveRight();
        assertEquals(p1.getX(), 3);
        assertEquals(p1.getY(), 2);
    }
        
// Every Door have a specific key with the same id as the door
// Closed Door change state to the Opened door if the player collides with it while having the corresponding key
    @Test
	public void testUS1_7_5() {
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        //create a closed dorr at (3,2)
        door = new Door(4, 2, "Door", 1);
        dun.addEntity(door);

        //create a key with a same id with created door
        key = new Key(3, 2, "Key", 1);
        Inventory inventory = new Inventory();

        inventory = new Inventory(); 

        // p1.moveRight();
        // assertTrue(inventory.hasItem(key));
        // //player can move righ into (4,2)
        // p1.moveRight();
        // assertEquals(p1.getX(), 4);
        // assertEquals(p1.getY(), 2);

    }


}
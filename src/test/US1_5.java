package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.obstacle.*;


public class US1_5 {
    Dungeon dun;  		// the dungeon
    Player p1;			// the player
    Entity s1;          // the switch
    Entity b1;          // the boulder
// Switches are like empty tiles, other game objects can be on top of it
    @Test
	public void testUS1_5_1() {
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        s1 = new Switch(2, 3, "Swtich");
        dun.addEntity(s1);

        p1.moveDown();
		assertEquals(p1.getX(), 2);
        assertEquals(p1.getY(), 3);


    }
// When a boulder is pushed onto a floor switch, it is triggered.
    @Test
	public void testUS1_5_2() {

        //create a new dungeon and a player at (2,2)
		dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        //create a new boulder at 2,3
        b1 = new Boulder(2, 3, "Boulder");
        dun.addEntity(b1);

        //create a new Swtich entity at 2,4
        s1 = new Switch(2, 4, "Swtich");
        dun.addEntity(s1);

        //the switch should be false at the begin
        assertEquals(((Switch) s1).getTriggered(), false);


        //the switch will be triggered when the boulder on it 
        p1.moveDown();
        assertEquals(((Switch) s1).getTriggered(), true);
        

    // When a boulder is pushed off a floor switch, it is untriggered.
        p1.moveDown();

        assertEquals(((Switch) s1).getTriggered(), false);

    }

}
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.item.*;
import unsw.obstacle.*;


public class US2_3{
    Dungeon dun;  		// the dungeon
    Player p1;			// the player
    
    
// When the player collides with invincibility potion, the player instantly become invincible (kill the enemy on collision)for a period of time(the 20s for now)
    @Test
    public void testUS2_3_1() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

        Enemy e1 = new Enemy(2, 3, "Enemy");
        dun.addEntity(e1);
        assertEquals(dun.existEntity(e1, 2, 3),true);

        Potion po1 = new Potion(1,2, "Potion");
        dun.addEntity(po1);
        //picks up potion
        p1.moveLeft();
        assertEquals(p1.getisInvincible(), true);


    }
// invincibility potion is deleted from the grid on a collision
@Test
    public void testUS2_3_2() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// when the player is invincible, sword don't lose durability
@Test
    public void testUS2_3_3() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
}

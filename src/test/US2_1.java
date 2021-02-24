package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class US2_1 {

    Dungeon dun;  		// the dungeon
    Player p1;			// the player
    

// If the player is not under potion effect when they collide with an enemy, they die and the game ends with fail
    @Test
    public void testUS2_1_1() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// Enemy constantly try to minimise the distance between the player if the player is not under potion duration
    @Test
    public void testUS2_1_2() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// Enemy constantly try to maximise distance from the player if the player is under potion duration
    @Test
    public void testUS2_1_3() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// Enemy stop moving if can't get any closer (when blocked by obstacles)
    @Test
    public void testUS2_1_4() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// Enemy follows the same rule as a player when interacting with portals/door/wall/boulder
    @Test
    public void testUS2_1_5() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// Enemy can't collect collectables
    @Test
    public void testUS2_1_6() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// Enemy can't push boulders
    @Test
    public void testUS2_1_7() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// Enemy is deleted from the dungeon on collision with a player under invincibility potion effect
    @Test
    public void testUS2_1_8() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
// Enemy is deleted from the dungeon on collision with Player equipped with a sword AND facing it
    @Test
    public void testUS2_1_9() {
        dun = new Dungeon(10,10);
        p1 = new Player(dun, 2, 2, "Player");
        dun.setPlayer(p1);

    }
}
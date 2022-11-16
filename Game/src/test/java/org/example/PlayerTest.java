/*  
 *  PlayerTest.java
 *  
 *  Description: Tests methods of Player class.
 * 
 *  Last changed: Nov 15th, 2022
 *
*/

package org.example;
import junit.framework.Test;
import junit.framework.TestSuite;
import main.*;
import entities.Player;
import entities.Coordinate;

public class PlayerTest extends AppTest 
{
    // Attributes
    private Player player;
    private KeyBoard key;

    /**
     * Create the test case.
     * @param testName name of the test case
     */
    public PlayerTest(String testName)
    {
        super(testName);
        Simulator sim = new Simulator();
        this.key = new KeyBoard(sim);
        this.player = new Player(sim, key, new CheckCollision(sim, key, new EntityList()), 0, 0);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(PlayerTest.class);
    }

    /**
     * @Test moving character position up by movespeed
    */
    public void moveUp()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedUp = true;
        player.update();
        player.get_keyboard().PressedUp = false;
        assertEquals(player.get_coordinate_Y(), 3);
    }

    /**
     * @Test moving character position down by movespeed
    */
    public void moveDown()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedDown = true;
        player.update();
        player.get_keyboard().PressedDown = false;
        assertEquals(player.get_coordinate_Y(), -3);
    }

    /**
     * @Test moving character position right by movespeed
    */
    public void moveRight()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedRT = true;
        player.update();
        player.get_keyboard().PressedRT = false;
        assertEquals(player.get_coordinate_X(), 3);
    }

    /**
     * @Test moving character position right by movespeed
    */
    public void moveLeft()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedLF = true;
        player.update();
        player.get_keyboard().PressedLF = false;
        assertEquals(player.get_coordinate_X(), -3);
    }

    /**
     * @Test player reset to start position
    */
    public void resetPosition()
    {
        player.setDefaultPosition();
        Simulator tmp = new Simulator();
        assertEquals(player.get_coordinate(), new Coordinate(tmp.get_player_default_x(), tmp.get_player_default_y()));
    }
}

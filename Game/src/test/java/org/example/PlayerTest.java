/*  
 *  PlayerTest.java
 *  
 *  Description: Stores X and Y coordinates.
 * 
 *  Author: Lionel
 * 
 *  Last changed: Oct 27th, 2022
 *
*/

package org.example;
import junit.framework.Test;
import junit.framework.TestSuite;
import main.*;
import entities.Player;

public class PlayerTest extends AppTest 
{
    // Attributes
    private Player player;
    private KeyBoard key;

    /**
     * Create the test case
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
     * @Test 
    */
    public void moveUp()
    {
        this.key.setKeyEvent('w');
        this.player.update();
        assertEquals(player.get_coordinate_Y(), 3);
    }
}

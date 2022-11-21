/* 
 *  EnemyTest.java
 * 
 *  Description: 
 * 
 *  Last changed: Nov 20th, 2022
 *
*/
 
package org.example;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;
import entities.Enemy;
import entities.Coordinate;
 
public class EnemyTest extends TestCase
{
    // Attributes
    private Enemy enemy;
    private Simulator sim;
 
    /**
     * Create the test case.
     * @param testName name of the test case
    */
    public EnemyTest(String testName)
    {
        super(testName);
        sim = new Simulator();
        KeyBoard key = new KeyBoard(sim);
        this.enemy = new Enemy(sim, new CheckCollision(sim, key, new EntityList()), 0, 0);
    }
 
    /**
    * @return the suite of tests being tested
    */
    public static Test suite()
    {
        TestSuite suite = new TestSuite(EnemyTest.class);
        suite.addTest(new EnemyTest("testDirectionGeneration"));
        suite.addTest(new EnemyTest("onPlayerCollisionLives"));
        suite.addTest(new EnemyTest("onPlayerCollisionPosition"));
        return suite;
    }
 
    /**
     * @Test if enemy generated a new direction successfully
    */
    public void testDirectionGeneration()
    {
        enemy.set_actionInterval(74);
        enemy.nextMove();
        assertEquals(0, enemy.get_actionInterval());
    }

    /**
     * @Test if enemy collsion deducts player lives
     */
    public void onPlayerCollisionLives()
    {
        int lives = sim.get_player().get_lives();
        enemy.player_onCollision();
        assertEquals(lives-1, sim.get_player().get_lives());
    }

    /**
     * @Test if enemy collsion resets player position
     */
    public void onPlayerCollisionPosition()
    {
        Coordinate position = sim.get_player().get_coordinate();
        enemy.player_onCollision();
        assertTrue(position.equals(sim.get_player().get_coordinate()));
    }
}

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
        player.set_canCollide(false);
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
        player.set_canCollide(false);
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
        player.set_canCollide(false);
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
        player.set_canCollide(false);
        player.update();
        player.get_keyboard().PressedLF = false;
        assertEquals(player.get_coordinate_X(), -3);
    }

    /**
     * @Test movement when canCOllide is true
    */
    public void moveWhenCollision()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedUp = true;
        player.set_canCollide(true);
        player.update();
        player.get_keyboard().PressedUp = false;
        assertEquals(player.get_coordinate_Y(), 3);
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

    /**
     * @Test object collision with banana
    */
    public void bananaCollision()
    {
        int score = player.get_score();
        player.object_collision("Banana");
        assertEquals(score+20, player.get_score());
    }

    /**
     * @Test object collision with apple
    */
    public void appleCollision()
    {
        int lives = player.get_lives();
        player.object_collision("Apple");
        assertEquals(lives+1, player.get_lives());
    }

    /**
     * @Test object collision with trap
    */
    public void trapCollision()
    {
        int lives = player.get_lives();
        player.object_collision("Trap");
        assertEquals(lives-1, player.get_lives());
    }

    /**
     * @Test collision with invalid object
    */
    public void invalidCollision()
    {
        int lives = player.get_lives();
        int score = player.get_score();
        player.object_collision("");
        assertEquals(lives, player.get_lives());
        assertEquals(score, player.get_score());
    }

    /**
     * @Test collision with enemy resets player position and deducts a life
    */
    public void enemyCollision()
    {
        int lives = player.get_lives();
        player.set_coordinate(0, 0);
        player.enemy_onCollision(0);
        assertEquals(lives-1, player.get_lives());
        assertEquals(player.get_coordinate(), new Coordinate(tmp.get_player_default_x(), tmp.get_player_default_y()));
    }

    /**
     * @Test collision with enemy that does not exist
    */
    public void nullEnemyCollision()
    {
        int lives = player.get_lives();
        player.set_coordinate(0, 0);
        player.enemy_onCollision(-1);
        assertEquals(lives, player.get_lives());
        assertEquals(player.get_coordinate(), new Coordinate(0, 0));
    }
}

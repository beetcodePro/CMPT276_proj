package org.example;

import entities.Coordinate;
import entities.Player;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.CheckCollision;
import main.EntityList;
import main.KeyBoard;
import main.Simulator;
import objects.obj_apple;

import java.awt.*;

public class SimulatorTest extends TestCase {
    //Attributes
    private Simulator sim;

    /**
     * Create the test case.
     * @param testName name of the test case
     */
    SimulatorTest(String testName){
        super(testName);
        sim = new Simulator();
    }
    /**
     * @return the suite of tests being tested
     */
    public static Test suit(){
        TestSuite suite = new TestSuite(SimulatorTest.class);
        suite.addTest(new SimulatorTest("setupGame"));
        return suite;
    }

    /**
     * @test sets up the game
     */
    public void setupGame(){
        sim.game_setup();
        assertEquals(sim.gameState, 5);
    }
    /**
     * @test resets game stats
     */
    public void resetStats(){
        sim.reset();
        assertEquals(sim.player.get_lives(), 3);
    }
    /**
     * @test resets the objects of the game
     */
    public void resetObjects(){
        sim.restart();
        Coordinate tempX = sim.player.get_coordinate();
        assertEquals(tempX.get_X(), 96);
    }
    /**
     * @test screen width
     */
    public void screenWidth(){
        assertEquals(sim.get_screen_width(), 1296);
    }
    /**
     * @test screen height
     */
    public void screenHeight(){
        assertEquals(sim.get_screen_height(), 720);
    }
    /**
     * @test tile size
     */
    public void tileSize(){
        assertEquals(sim.get_tileSize(), 48);
    }
    /**
     * @test player is created
     */
    public void testPlayer(){
        Simulator tempSim = new Simulator();
        KeyBoard tempKey = new KeyBoard(tempSim);
        CheckCollision tempCheck = new CheckCollision(tempSim, tempKey, new EntityList());
        Player tempPlayer = new Player(tempSim, tempKey, tempCheck, 96, 144);
        assertEquals(sim.get_player(), tempPlayer);
    }
    /**
     * @test player defaulted x location
     */
    public void defaultXCoordinate(){
        assertEquals(sim.get_player_default_x(), 48*2);
    }
    /**
     * @test player defaulted y location
     */
    public void defaultYCoordinate(){
        assertEquals(sim.get_player_default_y(), 48*3);
    }
    /**
     * @test gets the current list of entities
     */
    public void testEntityList(){
        sim.get_entitylist().clear_objList();
        sim.get_entitylist().clear_enemyList();
        assertEquals(sim.get_entitylist(), new EntityList());
    }
    /**
     * @test game thread
     */
    public void startingGameThread(){
        Simulator tempSim = new Simulator();
        Thread tempThread = new Thread(tempSim);
        tempThread.start();
        sim.startGameThread();
        assertEquals(sim.gameThread, tempThread);
    }
    /**
     * @test updates game
     */
    public void updateGame(){
        sim.gameState = sim.playGameState;
        sim.count = 450;
        sim.get_entitylist().clear_enemyList();
        sim.get_entitylist().clear_objList();
        sim.get_entitylist().add_obj(new obj_apple(5, 10));
        sim.update();
        assertEquals(sim.get_entitylist().get_objList_size(), 0);
    }
    /**
     * @test draws the updates onto the UI
     */
    public void testDrawUpdate(){
        sim.gameState = sim.playGameState;
        sim.paintComponent(sim.getGraphics());
        assertEquals(sim.ui.getPlayTime(), 1/60);
    }
    /**
     * @test sound effect
     */
    public void testSoundEffect(){
        assertEquals(sim.PlaySoundEffect(0), 0);

    }

    /**
     * @test resets the player position
     */
    public void resetPlayerPosition(){
        sim.reset_player_position();
        assertEquals(sim.player.get_coordinate_X(), 2*48);
    }
    /**
     * @test adding the player lives
     */
    public void addPlayerLives(){
        int tempLives = 1;
        sim.add_player_lives(tempLives);
        assertEquals(sim.player.get_lives(), 4);
    }



}

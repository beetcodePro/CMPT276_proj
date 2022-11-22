package org.example;

import entities.Coordinate;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.Simulator;

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
     * @test
     */



}

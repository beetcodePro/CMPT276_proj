package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.Simulator;
import main.UI;

import java.awt.*;

public class UITest extends TestCase {
    //Attributes
    UI test;
    Graphics2D g2;


    /**
     * Create the test case.
     * @param testName name of the test case
     */
    public UITest(String testName){
        test = new UI(new Simulator());
    }
    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        TestSuite suite = new TestSuite(UITest.class);
        suite.addTest(new UITest("messageAppear"));
        suite.addTest(new UITest("drawTitleScreen"));
        suite.addTest(new UITest("drawPauseScreen"));
        suite.addTest(new UITest("drawGameOverScreen"));
        suite.addTest(new UITest("drawWinScreen"));
        suite.addTest(new UITest("centreTheX"));
        suite.addTest(new UITest("centreTheX2"));
        suite.addTest(new UITest("getPlayTime"));
        return suite;
    }
    /**
     * @test message is changed to chosen message
     */
    public void messageAppear(){
        test.showMessage("Hello World!");
        assertEquals(test.messageOn, true);
    }
    /**
     * @test objects are shown on the screen
     */
    public void testDrawing(){

    }

    /**
     * @test draw the title on the screen
     */
    public void drawTitleScreen(){
        test.drawTitle();
        assertEquals(test.tester, 2);
    }
    /**
     * @test draw the pause screen
     */
    public void drawPauseScreen(){
        test.drawPauseScreen();
        assertEquals(test.tester, 3);
    }
    /**
     * @test draw the game over screen
     */
    public void drawGameOverScreen(){
        test.drawGameOverScreen();
        assertEquals(test.tester, 4);
    }
    /**
     * @test draw the winning screen
     */
    public void drawWinScreen(){
        test.drawGameWinScreen();
        assertEquals(test.tester, 5);
    }
    /**
     * @test centre the text of string
     */
    public void centreTheX(){
        int tempX = test.getXforCenteredText("Hello World!");
        int length = (int)g2.getFontMetrics().getStringBounds("Hello World!", g2).getWidth();
        int x=test.sim.get_screen_width()/3- length/2;
        assertEquals(tempX, x);
    }
    /**
     * @test alternative way of centering the text of string
     */
    public void centreTheX2(){
        int tempX = test.getXforCenteredText2("Hello World!");
        int length = (int)g2.getFontMetrics().getStringBounds("Hello World!", g2).getWidth();
        int x=test.sim.get_screen_width()/2- length/2;
        assertEquals(tempX, x);
    }
    /**
     * @test get the playtime variable
     */
    public void getPlayTime(){
        test.setPlayTime(1.0);
        assertEquals(test.getPlayTime(), 1.0);
    }
}

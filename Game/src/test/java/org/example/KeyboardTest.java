package org.example;

import entities.Player;
import junit.framework.Test;
import junit.framework.TestCase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import junit.framework.TestSuite;
import main.CheckCollision;
import main.EntityList;
import main.KeyBoard;
import main.Simulator;

public class KeyboardTest extends TestCase {
    private Simulator sim;



    /**
     * Create the test case.
     * @param testName name of the test case
     */

    public KeyboardTest(String testName)
    {
        super(testName);


    }
    /**
     * @test for W key on the keyboard
     */
    public void keyPressedUPTest() {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 87, 'W');
        assertEquals(KeyEvent.VK_W, e.getKeyCode() );
        KeyEvent e2 = new KeyEvent(a, 1, 20, 1, 38, 'U');
        assertEquals(KeyEvent.VK_UP, e2.getKeyCode());

    }
    /**
     * @test for S key on the keyboard
     */
    public void keyPressedDNTest() {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 83, 'S');
        assertEquals( KeyEvent.VK_S, e.getKeyCode());
        KeyEvent e2 = new KeyEvent(a, 1, 20, 1, 40, 'D');
        assertEquals(KeyEvent.VK_DOWN, e2.getKeyCode());
    }
    /**
     * @test for A key on the keyboard
     */
    public void keyPressedLFTest( ) {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 65, 'A');
        assertEquals(KeyEvent.VK_A, e.getKeyCode());
        KeyEvent e2 = new KeyEvent(a, 1, 20, 1, 37, 'L');
        assertEquals(KeyEvent.VK_LEFT, e2.getKeyCode());
    }
    /**
     * @test for D key on the keyboard
     */
    public void keyPressedRTTest() {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 68, 'D');
        assertEquals(KeyEvent.VK_D, e.getKeyCode() );
        KeyEvent e2 = new KeyEvent(a, 1, 20, 1, 39, 'R');
        assertEquals(KeyEvent.VK_RIGHT, e2.getKeyCode());
    }
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
            TestSuite suite = new TestSuite(KeyboardTest.class);
            suite.addTest(new KeyboardTest("keyPressedUPTest"));
            suite.addTest(new KeyboardTest("keyPressedDNTest"));
            suite.addTest(new KeyboardTest("keyPressedLFTest"));
            suite.addTest(new KeyboardTest("keyPressedRTTest"));
            return suite;

    }
}

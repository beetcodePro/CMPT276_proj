package org.example;

import entities.Player;
import junit.framework.Test;
import junit.framework.TestCase;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import junit.framework.TestSuite;
import main.CheckCollision;
import main.EntityList;
import main.KeyBoard;
import main.Simulator;

public class KeyboardTest extends TestCase {
    private Simulator sim;
    KeyEvent e;



    public KeyboardTest(String testName)
    {
        super(testName);
    }
    /**
     * @test for W key on the keyboard
     */
    public void keyPressedUPTest() {
        e.setKeyChar('W');
        assertEquals(e.getKeyCode(), KeyEvent.VK_W);
    }
    /**
     * @test for S key on the keyboard
     */
    public void keyPressedDNTest() {
        e.setKeyChar('S');
        assertEquals(e.getKeyCode(), KeyEvent.VK_S);
    }
    /**
     * @test for A key on the keyboard
     */
    public void keyPressedLFTest() {
        e.setKeyChar('A');
        assertEquals(e.getKeyCode(), KeyEvent.VK_A);
    }
    /**
     * @test for D key on the keyboard
     */
    public void keyPressedRTTest(char C) {
        e.setKeyChar('D');
        assertEquals(e.getKeyCode(), KeyEvent.VK_D);
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

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
    public boolean PressedUp, PressedDown, PressedRT, PressedLF= false;
    @Override
    public void keyTyped(KeyEvent e) {
        //not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code= e.getKeyCode();
        if (code==KeyEvent.VK_UP)
        {
            PressedUp= true;
        }
        if (code==KeyEvent.VK_DOWN)
        {
            PressedDown= true;
        }
        if (code==KeyEvent.VK_LEFT)
        {
            PressedLF= true;
        }
        if (code==KeyEvent.VK_RIGHT)
        {
            PressedRT= true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code= e.getKeyCode();

        if (code==KeyEvent.VK_UP)
        {
            PressedUp= false;
        }
        if (code==KeyEvent.VK_DOWN)
        {
            PressedDown= false;
        }
        if (code==KeyEvent.VK_LEFT)
        {
            PressedLF= false;
        }
        if (code==KeyEvent.VK_RIGHT)
        {
            PressedRT= false;
        }
    }
}

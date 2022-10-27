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
        if (code==KeyEvent.VK_UP || code==KeyEvent.VK_W)
        {
            PressedUp= true;
        }
        if (code==KeyEvent.VK_DOWN || code==KeyEvent.VK_S)
        {
            PressedDown= true;
        }
        if (code==KeyEvent.VK_LEFT || code==KeyEvent.VK_A)
        {
            PressedLF= true;
        }
        if (code==KeyEvent.VK_RIGHT || code==KeyEvent.VK_D)
        {
            PressedRT= true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code= e.getKeyCode();

        if (code==KeyEvent.VK_UP || code==KeyEvent.VK_W)
        {
            PressedUp= false;
        }
        if (code==KeyEvent.VK_DOWN || code==KeyEvent.VK_S)
        {
            PressedDown= false;
        }
        if (code==KeyEvent.VK_LEFT || code==KeyEvent.VK_A)
        {
            PressedLF= false;
        }
        if (code==KeyEvent.VK_RIGHT || code==KeyEvent.VK_D)
        {
            PressedRT= false;
        }
    }
}

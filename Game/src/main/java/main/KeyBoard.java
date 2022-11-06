package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
    Simulator sim;
    public boolean PressedUp, PressedDown, PressedRT, PressedLF= false;

    public KeyBoard(Simulator sim) {
        this.sim = sim;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //not used
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code= e.getKeyCode();

        //Title state
        if(sim.gameState == sim.titleState) {
            if (code==KeyEvent.VK_UP || code==KeyEvent.VK_W) {
                sim.ui.commandNum--;
                if(sim.ui.commandNum < 0) {
                    sim.ui.commandNum = 1;
                }
            }
            if (code==KeyEvent.VK_DOWN || code==KeyEvent.VK_S) {
                sim.ui.commandNum++;
                if(sim.ui.commandNum > 1) {
                    sim.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(sim.ui.commandNum == 0) {
                    sim.gameState = sim.playGameState;
                }
                if(sim.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        }
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
        //Pause Game Shortcut
        if (code==KeyEvent.VK_P)
        {
            if(sim.gameState == sim.playGameState){
                sim.gameState = sim.pauseState;
            }
            else if(sim.gameState == sim.pauseState){
                sim.gameState = sim.playGameState;
            }
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

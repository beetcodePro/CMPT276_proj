package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
    Simulator sim;
    public boolean PressedUp, PressedDown, PressedRT, PressedLF = false;

    public KeyBoard(Simulator sim) {
        this.sim = sim;
    }

    // Methods
    @Override
    public void keyTyped(KeyEvent e) {
        //not used
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //Title state
        if(sim.gameState == sim.titleState || sim.gameState == sim.gameOverSate || sim.gameState == sim.gameWinSate || sim.gameState == sim.transitionState) {
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                sim.ui.commandNum--;
                if (sim.ui.commandNum < 0) {
                    sim.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                sim.ui.commandNum++;
                if (sim.ui.commandNum > 1) {
                    sim.ui.commandNum = 0;
                }
            }
            //Game Title Options
            if (code == KeyEvent.VK_ENTER) {
                if (sim.ui.commandNum == 0 && sim.gameState == sim.titleState) {
                    sim.gameState = sim.playGameState;
                }
                if (sim.ui.commandNum == 1 && sim.gameState == sim.titleState) {
                    System.exit(0);
                }
                //Game Over options
                if (sim.ui.commandNum == 0 && sim.gameState == sim.gameOverSate) {
                    sim.gameState = sim.playGameState;
                    sim.reset();
                    sim.restart();

                }
                if (sim.ui.commandNum == 1 && sim.gameState == sim.gameOverSate) {
                    sim.gameState = sim.titleState;
                    sim.reset();
                    sim.restart();
                }
                //Game Win Screen Options
                if (sim.ui.commandNum == 0 && sim.gameState == sim.gameWinSate) {
                    sim.gameState = sim.playGameState;
                    sim.reset();
                    sim.restart();
                }
                if (sim.ui.commandNum == 1 && sim.gameState == sim.gameWinSate) {
                    System.exit(0);
                }
                //Game Transition Options
                if (sim.ui.commandNum == 0 && sim.gameState == sim.transitionState) {
                    sim.gameState = sim.playGameState;
                }
                if (sim.ui.commandNum == 1 && sim.gameState == sim.transitionState) {
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

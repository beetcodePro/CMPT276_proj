package main;

import javax.swing.*;

public class main {
    public static void main(String [] args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Minions Laboratory Game");
        window.pack();
        Simulator simulator= new Simulator();
        window.setSize(simulator.get_screen_width()+16, simulator.get_screen_height()+38);
        window.add(simulator);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //this is to draw bananas
        simulator.game_setup();
        simulator.startGameThread();
    }


}

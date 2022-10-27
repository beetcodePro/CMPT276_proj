package main;

import javax.swing.*;

public class main {
    public static void main(String [] args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Minions Laboratory Game");
        Simulator simulator= new Simulator();

        window.add(simulator);
        window.setResizable(true);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        simulator.startGameThread();
    }


}

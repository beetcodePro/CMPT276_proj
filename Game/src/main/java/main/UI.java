package main;

import objects.obj_heart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    Simulator sim;
    Font arial_40;
    BufferedImage heartImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(Simulator gp) {
        this.sim = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        obj_heart heart = new obj_heart();
        heartImage = heart.image;

    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;

    }
    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(heartImage,40, 6, sim.tileSize-5, sim.tileSize-5, null);
        g2.drawString("x" + sim.player.lives, 90 ,40);

        //Timer
         playTime +=(double)1/60;
         g2.drawString("Time:"+ dFormat.format(playTime), sim.tileSize*11,40);

         //Score
         g2.drawString("Score:"+ sim.player.get_score(), 250, 40);

        //message function
        if(messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, sim.tileSize/2,sim.tileSize*5);


            messageCounter++;

            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

}

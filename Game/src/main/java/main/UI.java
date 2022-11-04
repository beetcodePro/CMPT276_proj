package main;

import objects.obj_heart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    Graphics2D g2;
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
        this.g2=g2;
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
        if(sim.gameState==sim.gameOverSate)
        {
            drawGameOverScreen();
        }
    }
    public void drawGameOverScreen()
    {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0 , sim.ScreenWidth, sim.ScreenHeight);
        g2.setColor(Color.black);
        String text= "GAME OVER";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));
        int x= getXforCenteredText(text);
        int y=sim.get_tileSize()*4;
        g2.drawString(text, x,y);
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

    }
    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=sim.get_screen_width()/2- length/2;
        return x;
    }

}

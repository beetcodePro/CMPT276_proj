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
    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int commandNum = 0;


    public UI(Simulator sim) {
        this.sim = sim;

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

        //title state
        if(sim.gameState == sim.titleState) {
            drawTitle();
        }

        //Add play state things in here
        if(sim.gameState == sim.playGameState){
            // Timer
            playTime +=(double)1/60;
            //Draw time
            g2.drawString("Time:"+ dFormat.format(playTime), sim.tileSize*11,40);
            //Score
            g2.drawString("Score:"+ sim.player.get_score(), 250, 40);
        }
        //Pause state
        if(sim.gameState == sim.pauseState){
            drawPauseScreen();
            g2.setFont(arial_40);
        }
        //Game Over state
        if(sim.gameState==sim.gameOverSate)
        {
            drawGameOverScreen();
        }
        if(sim.gameState==sim.gameWinSate)
        {
            drawGameWinScreen();
        }


        //message function
        if(messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, sim.tileSize/2,sim.tileSize*5);
            //timer for message time
            messageCounter++;

            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
    public void drawTitle() {
        g2.setColor(new Color(178,102,255));
        g2.fillRect(0,0,sim.ScreenWidth,sim.ScreenHeight);
        //Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Minion Laboratory";
        int x = getXforCenteredText(text)+192;
        int y = sim.tileSize*3;
        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //title image
        x = sim.ScreenWidth/2 - 60;
        y += sim.tileSize*2;
        g2.drawImage(sim.player.down1,x,y,sim.tileSize*2,sim.tileSize*2,null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "START GAME";
        x = getXforCenteredText(text)+194;
        y += sim.tileSize*3.5;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">",x-sim.tileSize,y);
        }

        text = "QUIT";
        x = getXforCenteredText(text)+194;
        y += sim.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">",x-sim.tileSize,y);
        }


    }
    public void drawPauseScreen() {
        //Paused Message

        String text = "PAUSED";
        int x= getXforCenteredText2(text);
        int y=sim.ScreenHeight/2;
        g2.setColor(new Color(50,50,50,150));
        g2.fillRect(x-100,y-80,368, 140);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        g2.drawString(text, x-80, y);
        g2.setFont(arial_40);
        g2.drawString("Time:"+ dFormat.format(playTime),555,408);
        g2.drawString("Score:"+ sim.player.get_score(), 250, 40);
    }
    public void drawGameOverScreen()
    {
        String text= "GAME OVER";
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0 , sim.ScreenWidth, sim.ScreenHeight);
        int x= getXforCenteredText(text);
        int y=sim.get_tileSize()*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));
        g2.setColor(Color.white);
        g2.drawString(text, x+48, y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text= "Retry";
        x=getXforCenteredText2(text);
        y+=sim.get_tileSize()*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">",x-sim.tileSize,y);
        }

        text= "Menu";
        x=getXforCenteredText2(text);
        y+= 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">",x-sim.tileSize,y);
        }

        g2.setFont(g2.getFont().deriveFont(80f));
        x-=96;
        y-= 160;
        g2.drawString("Score:"+ sim.player.get_score(),x,y);


    }
    public void drawGameWinScreen()
    {
        String text= "YOU WON!";
        g2.setColor(new Color(0,100,0,150));
        g2.fillRect(0,0 , sim.ScreenWidth, sim.ScreenHeight);
        int x= getXforCenteredText(text);
        int y=sim.get_tileSize()*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));
        g2.setColor(Color.pink);
        g2.drawString(text, x+48, y-4);
        //shadow
        g2.setColor(Color.white);
        g2.drawString(text, x+53, y+1);

        //End Image
        x = sim.ScreenWidth/2 - 60;
        y += sim.tileSize*2;
        g2.drawImage(sim.player.down1,x,y,sim.tileSize*2,sim.tileSize*2,null);


        //Options
        g2.setFont(g2.getFont().deriveFont(50f));
        text= "Retry";
        x=getXforCenteredText2(text);
        y+=sim.get_tileSize()*6;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">",x-sim.tileSize,y);
        }

        text= "Quit";
        x=getXforCenteredText2(text);
        y+= 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">",x-sim.tileSize,y);
        }


        g2.setFont(g2.getFont().deriveFont(80f));
        x-= 96;
        y+= 112;
        g2.drawString("Score:"+ sim.player.get_score(),x,y);


    }
    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=sim.get_screen_width()/3- length/2;
        return x;
    }
    public int getXforCenteredText2(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=sim.get_screen_width()/2- length/2;
        return x;
    }

}

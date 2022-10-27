package main;

import javax.swing.JPanel;
import java.awt.*;

public class Simulator extends JPanel implements Runnable{
    //Screen Settings

    final int originalTileSize= 16; //16x16 size for tile
    final int scale= 3;  //3*16 =48 to scale the resolution
    final int tileSize= originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int ScreenWidth= tileSize*maxScreenCol; //768 pixels
    final int ScreenHeight= tileSize*maxScreenRow; //576 pixels
    Thread gameThread;
    KeyBoard Key= new KeyBoard();
    int PlayerPositionX= 100;
    int PlayerPositionY= 100;
    int PlayerV=5;
    int FPS=60;
    //constructor
    public Simulator()
    {
        this.setSize(new Dimension(ScreenWidth,ScreenHeight ));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(Key);
        this.setFocusable(true);

    }
    public void startGameThread()
    {
        gameThread= new Thread(this);
        gameThread.start();
    }


    //game loop
    public void run()
    {
        double TimeInterval= 1000000000/FPS;
        double DrawingTime= System.nanoTime()+TimeInterval ;
        while (gameThread != null)//repeat the process
        {
            //update information like:
            update();
            //character positions
            //draw the screen with the updated information
            repaint();
            try {
                double TimeLeft= DrawingTime- System.nanoTime();
                Thread.sleep((long)TimeLeft /1000000);
                if (TimeLeft < 0)
                {
                    TimeLeft=0;
                }
                DrawingTime= DrawingTime+TimeInterval;
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }


        }

    }

    public void update()
    {
        if (Key.PressedUp==true)
        {
            PlayerPositionY= PlayerPositionY-PlayerV ;
        }
        if (Key.PressedDown==true)
        {
            PlayerPositionY= PlayerPositionY+PlayerV ;
        }
        if (Key.PressedRT==true)
        {
            PlayerPositionX= PlayerPositionX+PlayerV;
        }
        if (Key.PressedLF==true)
        {
            PlayerPositionX= PlayerPositionX-PlayerV;
        }
    }
    //to draw on the screen
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        g2.setColor(Color.GRAY);
        g2.fillRect(PlayerPositionX,PlayerPositionY,tileSize, tileSize);
        g2.dispose();
    }
}

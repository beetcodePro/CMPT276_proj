package main;

import javax.swing.JPanel;
import java.awt.*;
import main.entities.Player;

public class Simulator extends JPanel implements Runnable
{
    // Screen Settings
    final int originalTileSize = 16; //16x16 size for tile
    final int scale = 3;  //3*16 =48 to scale the resolution
    final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int ScreenWidth = tileSize*maxScreenCol; //768 pixels
    final int ScreenHeight = tileSize*maxScreenRow; //576 pixels
    
    // Simulator attributes
    Thread gameThread;
    KeyBoard Key = new KeyBoard();
    int DefaultPlayerPositionX = 100;
    int DefaultPlayerPositionY = 100;
    int DefaultPlayerV = 5;
    int FPS = 60;
    Player player = new Player(this, Key, DefaultPlayerPositionX, DefaultPlayerPositionY, DefaultPlayerV);

    // Constructor
    public Simulator()
    {
        this.setSize(new Dimension(ScreenWidth,ScreenHeight ));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(Key);
        this.setFocusable(true);

    }

    // Get simulator tileSize
    public int get_tileSize() { return this.tileSize; }

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

    // Update game
    public void update()
    {
        player.update();
    }

    // Draw updates onto UI
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}

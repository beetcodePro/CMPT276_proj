package main;

import javax.swing.JPanel;
import java.awt.*;
import entities.Player;
import main.tile.tiles_controller;
import objects.allObjects;
import main.objects_controller;


public class Simulator extends JPanel implements Runnable
{
    // Screen Settings
    final int originalTileSize = 16; //16x16 size for tile
    final int scale = 3;  //3*16 =48 to scale the resolution
    final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 13;
    final int ScreenWidth = tileSize*maxScreenCol; //1200 pixels
    final int ScreenHeight = tileSize*maxScreenRow; //624 pixels

    public int num_of_bananas=12;


    // Simulator attributes
    Thread gameThread;
    KeyBoard Key = new KeyBoard();
    int DefaultPlayerPositionX = 1100;
    int DefaultPlayerPositionY = 525;
    int FPS = 60;
    tiles_controller Tile_c= new tiles_controller(this);
    CheckCollision cCheck = new CheckCollision(this, Key);
<<<<<<< HEAD

=======

    // FIXME: Entities
    Player player = new Player(this, Key, cCheck, DefaultPlayerPositionX, DefaultPlayerPositionY);

>>>>>>> a255aaf6059f0c818568bb067a0c87109b4b65f7
    // Constructor
    public Simulator()
    {
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(Key);
        this.setFocusable(true);

    }
    public void game_setup()
    {
        Obj_C.setObject();
    }

    // Getters
    public int get_screen_width() { return this.ScreenWidth; }
    public int get_screen_height() { return this.ScreenHeight; }
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
                if (TimeLeft < 0)
                {
                    TimeLeft=0;
                }
                Thread.sleep((long)TimeLeft /1000000);
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
        Tile_c.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}

package main;

import javax.swing.JPanel;
import java.awt.*;
import entities.*;
import main.tile.tiles_controller;
import objects.allObjects;


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
    tiles_controller Tile_c = new tiles_controller(this);
    private EntityList entityList = new EntityList();
    CheckCollision cCheck = new CheckCollision(this, Key, entityList);
    AssetCreator createAssets = new AssetCreator(this, entityList);

    // Entities
    public Player player = new Player(this, Key, cCheck, DefaultPlayerPositionX, DefaultPlayerPositionY);
    public allObjects obj[]= new allObjects[num_of_bananas];

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
        this.createAssets.setObject();
        this.createAssets.setEnemy(cCheck);
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
        this.entityList.update_enemyList();
    }

    // Draw updates onto UI
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Tile_c.draw(g2);
        for (int i=0; i< obj.length; i++)
        {
            if (obj[i]!= null)
            {
                obj[i].draw(g2, this);
            }
        }
        this.entityList.draw_enemyList(g2);
        player.draw(g2);
        g2.dispose();
    }
}

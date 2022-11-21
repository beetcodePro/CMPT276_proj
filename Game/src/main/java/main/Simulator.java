package main;

import javax.swing.JPanel;
import java.awt.*;
import entities.*;
import tile.tiles_controller;


public class Simulator extends JPanel implements Runnable
{
    // Screen Settings
    final int originalTileSize = 16; //16x16 size for tile
    final int scale = 3;  //3*16 =48 to scale the resolution
    final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 27;
    public final int maxScreenRow = 15;
    final int ScreenWidth = tileSize*(maxScreenCol); //1200 pixels
    final int ScreenHeight = tileSize*(maxScreenRow); //624 pixels

    //Game States
    public int gameState=0;
    public final int playGameState=1;
    public final int pauseState =2;
    public final int gameOverSate=3;
    public final int gameWinSate =4;
    public final int titleState =5;
    int count=1;
    // Simulator attributes
    Thread gameThread;
    KeyBoard Key = new KeyBoard(this);
    int DefaultPlayerPositionX = tileSize*2;
    int DefaultPlayerPositionY = tileSize*3;
    int FPS = 60;
    tiles_controller Tile_c = new tiles_controller(this);
    private EntityList entityList = new EntityList();
    CheckCollision cCheck = new CheckCollision(this, Key, entityList);
    AssetCreator createAssets = new AssetCreator(this, entityList);

    //UI
    public UI ui = new UI(this);

    sound Sound= new sound();

    // Entities
    public Player player = new Player(this, Key, cCheck, DefaultPlayerPositionX, DefaultPlayerPositionY);



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
        gameState = titleState;
    }
    //reset game stats
    public void reset(){
        ui.playTime = 0;
        player.set_score(0);
        player.set_lives(3);
    }
    //restart objects
    public void restart() {
        entityList.clear_objList();
        entityList.clear_enemyList();
        createAssets.setObject();
        createAssets.setEnemy(cCheck);
        player.setDefaultPosition();
    }
    // Getters
    public int get_screen_width() { return this.ScreenWidth; }
    public int get_screen_height() { return this.ScreenHeight; }
    public int get_tileSize() { return this.tileSize; }
    public Player get_player() { return this.player; }
    public int get_player_default_x() { return DefaultPlayerPositionX; }
    public int get_player_default_y() { return DefaultPlayerPositionY; }

    public void startGameThread()
    {
        gameThread= new Thread(this);
        gameThread.start();
    }

    // Game loop
    public void run()
    {
        double TimeInterval= 1000000000/FPS;
        double DrawingTime= System.nanoTime()+TimeInterval ;
        while (gameThread != null)//repeat the process
        {
            // Updates all entity info
            update();

            // Draw all entities with the updated information
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
        if(gameState == playGameState) {
            player.update();
            this.entityList.update_enemyList();
            count++;

            if (count== 450)
            {
                //delete the apples
                for (int i=0 ; i< entityList.get_objList_size(); i++)

               if (entityList.get_obj_at_index(i).get_name() == "Apple")
               {
                   entityList.delete_obj_at_index(i);
               }

            }
            if (count ==900)
            {
                createAssets.addApple();
                count =0;
            }
        }
        if(gameState == pauseState) {
            //nothing when paused
        }

    }

    // Draw updates onto UI
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //title state
        if(gameState == titleState) {
            ui.draw(g2);
        }

        else {
            // Draw all tiles
            Tile_c.draw(g2);

            // Draw all objects (inanimateEntities)
            entityList.draw_objList(g2, this);

            // Draw all enemies
            entityList.draw_enemyList(g2);

            // Draw the player
            player.draw(g2);

            //UI
            ui.draw(g2);

            g2.dispose();
        }
    }

    public void PlaySound(int x)
    {
        Sound.setFile(x);
        Sound.play();
        Sound.loop();
    }
    public void StopSound()
    {
        Sound.stop();
    }

    //Plays sound effect of chosen file
    public void PlaySoundEffect(int x)
    {
      Sound.setFile(x);
      Sound.play();
    }

    // Player function wrappers
    public void reset_player_position() { this.player.set_coordinate(DefaultPlayerPositionX, DefaultPlayerPositionY); }
    public void add_player_lives(int val) { this.player.add_lives(val); }
}

package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen Settings

    final int originalTileSize= 16; //16x16 size for tile
    final int scale= 3;  //3*16 =48 to scale the resolution
    final int tileSize= originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int ScreenWidth= tileSize*maxScreenCol; //768 pixels
    final int ScreenHeight= tileSize*maxScreenRow; //576 pixels
    Thread gameThread;
    //constructor
    public GamePanel()
    {
        this.setSize(new Dimension(ScreenWidth,ScreenHeight ));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);

    }
    public void startGameThread()
    {
        gameThread= new Thread(this);
        gameThread.start();
    }


    //game loop
    public void run() {

    }
}

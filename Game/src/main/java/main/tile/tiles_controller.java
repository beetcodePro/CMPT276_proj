package main.tile;

import main.Simulator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class tiles_controller {
    Simulator sim;
    Tiles[] tile;
    public tiles_controller(Simulator sim)
    {
        this.sim=sim;
        tile= new Tiles[2]; //number of different tiles
        get_tile_png();
    }
    public void get_tile_png()
    {
        try{
            tile[0]= new Tiles();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/tile.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public void draw (Graphics2D g)
    {
        int column=0;
        int row=0;
        int x=0;
        int y=0;
        while (column<sim.maxScreenCol && row< sim.maxScreenRow)
        {
            g.drawImage(tile[0].image,x,y, sim.get_tileSize(),sim.get_tileSize(), null);
            column++;
            x+= sim.get_tileSize()/2;
            if (column== sim.maxScreenCol)
            {
                column=0;
                x=0;
                row++;
                y+= sim.get_tileSize()/2;

            }
        }

    }
}

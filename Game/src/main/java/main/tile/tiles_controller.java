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
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/white_tiles.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public void draw (Graphics2D g)
    {
        g.drawImage(tile[0].image,0,0, sim.get_tileSize(),sim.get_tileSize(), null);
    }
}

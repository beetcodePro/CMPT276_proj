package tile;

import main.Simulator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class tiles_controller {
    Simulator sim;
    public Tiles[] tile;
    public int mapTileNum[][];
    public tiles_controller(Simulator sim)
    {
        this.sim=sim;
        tile= new Tiles[10]; //number of different tiles
        mapTileNum = new int[sim.maxScreenCol][sim.maxScreenRow];
        get_tile_png();
        String maps[]= {"/maps/map01.txt", "/maps/map02.txt", "/maps/map03.txt","/maps/map04.txt","/maps/map05.txt" };
        Random random = new Random();
        int x = random.nextInt(4);

        mapLoad(maps[x]);
    }
    public void get_tile_png()
    {
        try{
            tile[0]= new Tiles();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/tile.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/wall/wall.png"));
            tile[1].collision = true;
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void mapLoad(String file){
        try{
            InputStream is = getClass().getResourceAsStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < sim.maxScreenCol && row < sim.maxScreenRow){
                String line = br.readLine();

                while (col < sim.maxScreenCol){
                    String num[] = line.split(" ");

                    int number = Integer.parseInt(num[col]);

                    mapTileNum[col][row] = number;
                    col++;
                }
                if (col == sim.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){

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
            int tileNum = mapTileNum[column][row];
            g.drawImage(tile[tileNum].image,x,y, sim.get_tileSize(),sim.get_tileSize(), null);

            column++;
            x+= sim.get_tileSize();
            if (column== sim.maxScreenCol)
            {
                column=0;
                x=0;
                row++;
                y+= sim.get_tileSize();

            }
        }

    }
}

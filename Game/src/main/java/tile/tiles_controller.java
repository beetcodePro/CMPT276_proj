package tile;

import main.Simulator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tiles_controller 
{
    Simulator sim;
    public Tiles[] tile;
    public int mapTileNum[][][];

    public tiles_controller(Simulator sim)
    {
        this.sim=sim;
        tile= new Tiles[35]; //number of different tiles
        mapTileNum = new int[sim.maxMap][sim.maxScreenCol][sim.maxScreenRow];

        get_tile_png();
        mapLoad("/maps/map01.txt",0);
        mapLoad("/maps/map02.txt",1);
        mapLoad("/maps/map03.txt",2);

    }

    public int get_currentMap() { return sim.currentMap; }

    public void get_tile_png()
    {
        try {
            tile[0]= new Tiles();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/blank.png"));
            tile[1].collision = true;

            tile[2]= new Tiles();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_side_down.png"));

            tile[3]= new Tiles();
            tile[3].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_side_up.png"));

            tile[4]= new Tiles();
            tile[4].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_side_right.png"));

            tile[5]= new Tiles();
            tile[5].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_side_left.png"));

            tile[6]= new Tiles();
            tile[6].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_corner_1.png"));

            tile[7]= new Tiles();
            tile[7].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_corner_2.png"));

            tile[8]= new Tiles();
            tile[8].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_corner_3.png"));

            tile[9]= new Tiles();
            tile[9].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_corner_4.png"));

            tile[10]= new Tiles();
            tile[10].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_inner_1.png"));

            tile[11]= new Tiles();
            tile[11].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_inner_2.png"));

            tile[12]= new Tiles();
            tile[12].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_inner_3.png"));

            tile[13]= new Tiles();
            tile[13].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_inner_4.png"));

            tile[14]= new Tiles();
            tile[14].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_horizontal.png"));
            tile[14].isBridge = true;

            tile[15]= new Tiles();
            tile[15].image= ImageIO.read(getClass().getResourceAsStream("/tiles/floors/floor_vertical.png"));
            tile[15].isBridge = true;

            tile[16]= new Tiles();
            tile[16].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall.png"));
            tile[16].collision = true;

            tile[17]= new Tiles();
            tile[17].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall_side_right.png"));
            tile[17].collision = true;

            tile[18]= new Tiles();
            tile[18].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall_side_left.png"));
            tile[18].collision = true;

            tile[19]= new Tiles();
            tile[19].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall_side_up.png"));
            tile[19].collision = true;

            tile[20]= new Tiles();
            tile[20].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall_corner_1.png"));
            tile[20].collision = true;

            tile[21]= new Tiles();
            tile[21].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall_corner_2.png"));
            tile[21].collision = true;

            tile[22]= new Tiles();
            tile[22].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall_ledge.png"));
            tile[22].collision = true;

            tile[23]= new Tiles();
            tile[23].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall_ledge_right.png"));
            tile[23].collision = true;

            tile[24]= new Tiles();
            tile[24].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall_ledge_left.png"));
            tile[24].collision = true;

            tile[25]= new Tiles();
            tile[25].image= ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall_door.png"));
            tile[25].collision = true;

            tile[26]= new Tiles();
            tile[26].image= ImageIO.read(getClass().getResourceAsStream("/tiles/blank_alt_1.png"));
            tile[26].collision = true;

            tile[27]= new Tiles();
            tile[27].image= ImageIO.read(getClass().getResourceAsStream("/tiles/blank_alt_2.png"));
            tile[27].collision = true;

            tile[28]= new Tiles();
            tile[28].image= ImageIO.read(getClass().getResourceAsStream("/tiles/borders/border_horizontal.png"));
            tile[28].collision = true;

            tile[29]= new Tiles();
            tile[29].image= ImageIO.read(getClass().getResourceAsStream("/tiles/borders/border_vertical.png"));
            tile[29].collision = true;

            tile[30]= new Tiles();
            tile[30].image= ImageIO.read(getClass().getResourceAsStream("/tiles/borders/border_corner_1.png"));
            tile[30].collision = true;

            tile[31]= new Tiles();
            tile[31].image= ImageIO.read(getClass().getResourceAsStream("/tiles/borders/border_corner_2.png"));
            tile[31].collision = true;

            tile[32]= new Tiles();
            tile[32].image= ImageIO.read(getClass().getResourceAsStream("/tiles/borders/border_corner_3.png"));
            tile[32].collision = true;

            tile[33]= new Tiles();
            tile[33].image= ImageIO.read(getClass().getResourceAsStream("/tiles/borders/border_corner_4.png"));
            tile[33].collision = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void mapLoad(String file, int map){
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

                    mapTileNum[map][col][row] = number;
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
            int tileNum = mapTileNum[sim.currentMap][column][row];
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

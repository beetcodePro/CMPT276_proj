package main;
import entities.*;
import objects.*;

import java.util.Random;

public class AssetCreator 
{
    Simulator sim;
    EntityList entityList;

    // Default constructor
    public AssetCreator(Simulator sim, EntityList eList){
        this.sim = sim;
        this.entityList = eList;
    }

    // Create and set objects
    public void setObject() 
    {
        int tileSize = sim.get_tileSize();
        for (int i=0; i<13; i++)
        {
            //placing bananas randomly
            Random random = new Random();
            int x = random.nextInt(22)+1;
            int y = random.nextInt(10)+1;

            // Generate a coordinate without a collidable tile
            while(sim.Tile_c.tile[sim.Tile_c.mapTileNum[x][y]].collision == true)
            {
                x = random.nextInt(22)+1;
                y = random.nextInt(10)+1; 
            }
            this.entityList.add_obj(new obj_banana(tileSize*x, tileSize*y));
            i++;
        }
    }

    // Create and set enemy entities
    public void setEnemy(CheckCollision cCheck) 
    {
        int tileSize = sim.get_tileSize();
        this.entityList.add_enemy(new Enemy(this.sim, cCheck, tileSize*10, tileSize*10));
        this.entityList.add_enemy(new Enemy(this.sim, cCheck, tileSize*3, tileSize*7));
        this.entityList.add_enemy(new Enemy(this.sim, cCheck, tileSize*15, tileSize*7));
    }
}

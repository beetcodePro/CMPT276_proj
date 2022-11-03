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

    // Helper function: Checks if an object exists at specified coordinate
    private boolean checkObjectAtCoordinate(int x, int y)
    {
        for(int j=0; j<entityList.get_objList_size(); j++)
        {
            int tmpX = entityList.get_obj_at_index(j).get_coordinate_X()/sim.get_tileSize();
            int tmpY = entityList.get_obj_at_index(j).get_coordinate_Y()/sim.get_tileSize();
            if(tmpX == x && tmpY == y)
                return true;
        }
        return false;
    }

    // Create and set objects
    public void setObject() 
    {
        int tileSize = sim.get_tileSize();

        // placing bananas randomly
        for (int i=0; i<8; i++)
        {
            Random random = new Random();
            int x = random.nextInt(22)+1;
            int y = random.nextInt(10)+1;

            // Generate a coordinate without a collidable tile
            while(sim.Tile_c.tile[sim.Tile_c.mapTileNum[x][y]].collision == true || checkObjectAtCoordinate(x, y) == true)
            {
                x = random.nextInt(22)+1;
                y = random.nextInt(10)+1;
            }
            this.entityList.add_obj(new obj_banana(tileSize*x, tileSize*y));
        }

        // placing apples randomly
        for (int i=0; i<3; i++)
        {
            Random random = new Random();
            int x = random.nextInt(22)+1;
            int y = random.nextInt(10)+1;

            // Generate a coordinate without a collidable tile
            while(sim.Tile_c.tile[sim.Tile_c.mapTileNum[x][y]].collision == true || checkObjectAtCoordinate(x, y) == true)
            {
                x = random.nextInt(22)+1;
                y = random.nextInt(10)+1; 
            }
            this.entityList.add_obj(new obj_apple(tileSize*x, tileSize*y));
        }

        //placing traps randomly
        for (int i=0; i<5; i++)
        {
            Random random = new Random();
            int x = random.nextInt(22)+1;
            int y = random.nextInt(10)+1;


            // Generate a coordinate without a collidable tile
            while(sim.Tile_c.tile[sim.Tile_c.mapTileNum[x][y]].collision == true || checkObjectAtCoordinate(x, y) == true)
            {
                x = random.nextInt(22)+1;
                y = random.nextInt(10)+1;
            }
            this.entityList.add_obj(new obj_trap(tileSize*x, tileSize*y));
        }
    }


    // Create and set enemy entities
    public void setEnemy(CheckCollision cCheck) 
    {
        int tileSize = sim.get_tileSize();
        this.entityList.add_enemy(new Enemy(this.sim, cCheck, tileSize*15, tileSize*4));
        this.entityList.add_enemy(new Enemy(this.sim, cCheck, tileSize*23, tileSize*6));
        this.entityList.add_enemy(new Enemy(this.sim, cCheck, tileSize*12, tileSize*10));
        this.entityList.add_enemy(new Enemy(this.sim, cCheck, tileSize*6, tileSize*10));
    }
}

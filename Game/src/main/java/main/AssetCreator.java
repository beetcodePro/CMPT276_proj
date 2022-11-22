package main;
import entities.*;
import objects.*;

import java.util.Random;

public class AssetCreator 
{
    Simulator sim;
    public EntityList entityList;
    int mapBoundaryX;
    int mapBoundaryY;

    // Default constructor
    public AssetCreator(Simulator sim, EntityList eList){
        this.sim = sim;
        this.entityList = eList;
        this.mapBoundaryX = sim.maxScreenCol-2;
        this.mapBoundaryY = sim.maxScreenRow-2;
    }

    // Helper function: Checks if an object exists at specified coordinate
    public boolean checkObjectAtCoordinate(int x, int y, boolean isTrap)
    {
        for(int j=0; j<entityList.get_objList_size(); j++)
        {
            int tmpX = entityList.get_obj_at_index(j).get_coordinate_X()/sim.get_tileSize();
            int tmpY = entityList.get_obj_at_index(j).get_coordinate_Y()/sim.get_tileSize();
            if(tmpX == x && tmpY == y)
                return true;
            if(tmpX == 2 && tmpY == 3)
                return true;

            // Prevent traps from spawning in adjacent tiles to other traps
            if(isTrap == true)
            {
                if(tmpX+1 == x && tmpY == y)
                    return true;
                if(tmpX-1 == x && tmpY == y)
                    return true;
                if(tmpX == x && tmpY+1 == y)
                    return true;
                if(tmpX == x && tmpY-1 == y)
                    return true;
                if(tmpX+1 == x && tmpY+1 == y)
                    return true;
                if(tmpX-1 == x && tmpY+1 == y)
                    return true;
                if(tmpX-1 == x && tmpY-1 == y)
                    return true;
                if(tmpX+1 == x && tmpY-1 == y)
                    return true;
            }
        }
        return false;
    }

    // Helper function: Checks if a collidable tile exists at specified coordinate
    private boolean checkTileAtCoordinate(int x, int y, boolean isTrap)
    {
        if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[x][y]].collision == true)
            return true;

        // Checks if a bridge is nearby and (for traps only) if near the exit door
        if(isTrap == true)
        {
            //  (*NEEDS CLEAN UP, HARD-CODED VALUES*)
            // These values represent the floor in the room that has the door
            if (((x >= 3 && x <= 7) && (y >=9 && y <= 11)))
                return true;
            if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[x+1][y]].isBridge == true)
                return true;
            if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[x-1][y]].isBridge == true)
                return true;
            if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[x][y+1]].isBridge == true)
                return true;
            if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[x][y-1]].isBridge == true)
                return true;
        }

        return false;
    }

    // Create and set objects
    public void setObject() 
    {
        int tileSize = sim.get_tileSize();

        // place door at fixed location (1 per map)
        this.entityList.add_obj(new obj_door(tileSize*3, tileSize*10));

        // placing bananas randomly (8 bananas per map)
        for (int i=0; i<8; i++)
        {
            Random random = new Random();
            int x, y;

            // Generate a coordinate without a collidable tile
            do {
                x = random.nextInt(mapBoundaryX)+1;
                y = random.nextInt(mapBoundaryY)+1;
            } while(checkTileAtCoordinate(x, y, false) == true || checkObjectAtCoordinate(x, y, false) == true);
            this.entityList.add_obj(new obj_banana(tileSize*x, tileSize*y));
        }

        // placing apples randomly (2 apples per map)
        for (int i=0; i<2; i++)
        {
            Random random = new Random();
            int x,y;

            // Generate a coordinate without a collidable tile
            do {
                x = random.nextInt(mapBoundaryX)+1;
                y = random.nextInt(mapBoundaryY)+1;
            } while(checkTileAtCoordinate(x, y, false) == true || checkObjectAtCoordinate(x, y, false) == true);
            this.entityList.add_obj(new obj_apple(tileSize*x, tileSize*y));
        }

        //placing traps randomly (7 traps per map)
        for (int i=0; i<7; i++)
        {
            Random random = new Random();
            int x, y;

            // Generate a coordinate without a collidable tile

            do {
                x = random.nextInt(mapBoundaryX)+1;
                y = random.nextInt(mapBoundaryY)+1;
            } while(checkTileAtCoordinate(x, y, true) == true || checkObjectAtCoordinate(x, y, true) == true);
            this.entityList.add_obj(new obj_trap(tileSize*x, tileSize*y));
        }
    }

public void addApple()
{
    int tileSize = sim.get_tileSize();
    for (int i=0; i<1; i++)
    {
        Random random = new Random();
        int x,y;


        // Generate a coordinate without a collidable tile

        do {
            x = random.nextInt(mapBoundaryX)+1;
            y = random.nextInt(mapBoundaryY)+1;
        }while(checkTileAtCoordinate(x, y, false) == true || checkObjectAtCoordinate(x, y, false) == true);
        this.entityList.add_obj(new obj_apple(tileSize*x, tileSize*y));
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

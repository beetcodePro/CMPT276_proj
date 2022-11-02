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
        this.entityList.add_obj(new obj_banana(tileSize*10, tileSize*5)); // creates banana at (15, 12)
        this.entityList.add_obj(new obj_banana(tileSize*7, tileSize*11));
        for (int i=0; i<13; i++)
        {
            //placing bananas randomly
            Random random = new Random();
            int x = random.nextInt(20 )+1;
            int y = random.nextInt(12 )+1;
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

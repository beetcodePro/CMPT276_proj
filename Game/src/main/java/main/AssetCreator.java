package main;
import objects.obj_banana;
import entities.Enemy;

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
        sim.obj[0]= new obj_banana();
        sim.obj[0].world_x= 15*sim.get_tileSize();
        sim.obj[0].world_x= 12*sim.get_tileSize();

        sim.obj[1]= new obj_banana();
        sim.obj[1].world_x= 7*sim.get_tileSize();
        sim.obj[1].world_x= 12*sim.get_tileSize();
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

package main;
import objects.obj_banana;

public class objects_controller {
    Simulator sim;
    public objects_controller(Simulator sim){
        this.sim= sim;
    }
    public void setObject() {
        sim.obj[0]= new obj_banana();
        sim.obj[0].world_x= 15*sim.get_tileSize();
        sim.obj[0].world_x= 12*sim.get_tileSize();

        sim.obj[1]= new obj_banana();
        sim.obj[1].world_x= 7*sim.get_tileSize();
        sim.obj[1].world_x= 12*sim.get_tileSize();
    }
}

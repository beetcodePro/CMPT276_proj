package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

public class obj_banana extends allObjects{
    public obj_banana(){
        name= "banana";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/reward/banana.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

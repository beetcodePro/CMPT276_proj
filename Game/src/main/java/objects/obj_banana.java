package objects;

import java.io.IOException;
import javax.imageio.ImageIO;
import entities.InanimateEntity;

public class obj_banana extends InanimateEntity
{
    public obj_banana(int setX, int setY)
    {
        super(setX, setY);
        this.name = "Banana";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/reward/banana.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

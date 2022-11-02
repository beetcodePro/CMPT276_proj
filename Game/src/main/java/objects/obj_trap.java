package objects;

import entities.InanimateEntity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_trap extends InanimateEntity
{
    public obj_trap(int setX, int setY)
    {
        super(setX, setY);
        set_name("Trap");
        set_canCollide(false);

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/traps/oil_trap.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

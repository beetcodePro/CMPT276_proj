package objects;

import java.io.IOException;
import javax.imageio.ImageIO;
import entities.InanimateEntity;

public class obj_apple extends InanimateEntity
{
    public obj_apple(int setX, int setY)
    {
        super(setX, setY);
        set_name("Apple");
        set_canCollide(true);

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/reward/apple.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

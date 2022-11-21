package objects;

import java.io.IOException;
import javax.imageio.ImageIO;
import entities.InanimateEntity;
import java.awt.Rectangle;

public class obj_door extends InanimateEntity
{
    public obj_door(int setX, int setY)
    {
        super(setX, setY);
        this.config_hitbox();
        set_name("Door");
        set_canCollide(false);

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/endMark/Door.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Overide default configure hitbox (called on constructor ONLY)
    private void config_hitbox()
    {
        Rectangle config = new Rectangle();
        config.x = 3;           // hitbox border width
        config.y = 3;           // hitbox border height
        config.width = 42;      // hitbox width
        config.height = 20;     // hitbox height
        this.set_hitbox(config);
    }
}

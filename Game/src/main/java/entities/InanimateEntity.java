package entities;
import java.awt.image.BufferedImage;
import main.Simulator;
import java.awt.Graphics2D;

public class InanimateEntity extends Entity 
{
    // Attributes
    public BufferedImage image;
    public String name;
    public boolean collision = false;

    // Default constructor
    public InanimateEntity(int setX, int setY) 
    { 
        super(setX, setY); 
    }

    // Draws object on UI
    public void draw(Graphics2D g2, Simulator sim)
    {
        int X = this.get_coordinate_X();
        int Y = this.get_coordinate_Y();
        int tileSize = sim.get_tileSize();

        g2.drawImage(image, X, Y, tileSize, tileSize, null);
    }
}

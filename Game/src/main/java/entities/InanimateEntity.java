package entities;
import java.awt.image.BufferedImage;
import main.Simulator;
import java.awt.Graphics2D;

public class InanimateEntity extends Entity 
{
    // Attributes
    public BufferedImage image;
    private String name;

    // Default constructor
    public InanimateEntity(int setX, int setY) 
    { 
        super(setX, setY); 
    }

    public InanimateEntity() {
    }

    // Getters
    public BufferedImage get_image() { return this.image; }
    public String get_name() { return this.name; }

    // Setters
    public void set_image(BufferedImage val) { this.image = val; }
    public void set_name(String val) { this.name = val; }

    // Draws object on UI
    public void draw(Graphics2D g2, Simulator sim)
    {
        int X = this.get_coordinate_X();
        int Y = this.get_coordinate_Y();
        int tileSize = sim.get_tileSize();

        g2.drawImage(image, X, Y, tileSize, tileSize, null);
    }
}

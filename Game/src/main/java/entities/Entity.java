/*  
 *  Entity.java
 *  
 *  Description: Abstract class, has a coordinate with corresponding get/set methods.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Nov 1st, 2022
 *
*/

package entities;
import java.awt.Rectangle;

public abstract class Entity 
{
    // Attributes
    private Coordinate position;
    public Rectangle hitbox;
    private boolean canCollide = false;

    // Default constructor (must pass X & Y coordinates)
    public Entity(int setX, int setY)
    {
        this.position = new Coordinate(setX, setY);
        this.config_hitbox();
    }

    protected Entity() {
    }

    // Getters
    public Coordinate get_coordinate() { return this.position; }
    public int get_coordinate_X() { return this.position.get_X(); }
    public int get_coordinate_Y() { return this.position.get_Y(); }
    public Rectangle get_hitbox() { return this.hitbox; }
    public boolean get_canCollide() { return this.canCollide; }

    // Setters
    public void set_coordinate_X(int setX) { this.position.set_X(setX); }
    public void set_coordinate_Y(int setY) { this.position.set_Y(setY); }
    public void set_hitbox(Rectangle val) { this.hitbox = val; }
    public void set_hitbox_x(int val) { this.hitbox.x = val; }
    public void set_hitbox_y(int val) { this.hitbox.y = val; }
    public void set_canCollide(boolean val) { this.canCollide = val; }
    public void set_coordinate(int setX, int setY) 
    {
        this.position.set_X(setX);
        this.position.set_Y(setY);
    }

    // Default configure hitbox (called on constructor ONLY)
    private void config_hitbox()
    {
        Rectangle config = new Rectangle();
        config.x = 0;           // hitbox border width
        config.y = 0;           // hitbox border height
        config.width = 48;      // hitbox width
        config.height = 48;     // hitbox height
        this.set_hitbox(config);
    }
}

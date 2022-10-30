/*  
 *  Entity.java
 *  
 *  Description: Abstract class, has a coordinate with corresponding get/set methods.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Oct 29th, 2022
 *
*/

package entities;

public abstract class Entity 
{
    // Attributes
    private Coordinate position;

    // Default constructor (must pass X & Y coordinates)
    public Entity(int setX, int setY)
    {
        this.position = new Coordinate(setX, setY);
    }

    // Getters
    public Coordinate get_coordinate() { return this.position; }
    public int get_coordinate_X() { return this.position.get_X(); }
    public int get_coordinate_Y() { return this.position.get_Y(); }

    // Setters
    public void set_coordinate_X(int setX) { this.position.set_X(setX); }
    public void set_coordinate_Y(int setY) { this.position.set_Y(setY); }
    public void set_coordinate(int setX, int setY) 
    {
        this.position.set_X(setX);
        this.position.set_Y(setY);
    }
}

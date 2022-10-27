/*  
 *  Coordinate.java
 *  
 *  Description: Stores X and Y coordinates.
 * 
 *  Author: Lionel
 * 
 *  Last changed: Oct 27th, 2022
 *
*/

package main.entities;

public class Coordinate 
{
    // Attributes
    private int X;
    private int Y;

    // Default constructor (must pass X & Y coordinates)
    public Coordinate(int setX, int setY)
    {
        this.X = setX;
        this.Y = setY;
    }

    // Getters
    public int get_X() { return this.X; }
    public int get_Y() { return this.Y; }

    // Setters
    public void set_X(int val) { this.X = val; }
    public void set_Y(int val) { this.Y = val; }
}

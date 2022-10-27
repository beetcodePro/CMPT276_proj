/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherites Entity and has movespeed.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Oct 27th, 2022
 *
*/

package main.entities;

abstract class AnimateEntity extends Entity
{
    // Attributes
    private int moveSpeed;

    // Default constructor (must pass X/Y coordinates and speed)
    public AnimateEntity(int setX, int setY, int setSpeed)
    {
        super(setX, setY);
        this.moveSpeed = setSpeed;
    }

    // Getters
    public int get_moveSpeed() { return this.moveSpeed; }

    // Setters
    public void set_moveSpeed(int val) { this.moveSpeed = val; }
}

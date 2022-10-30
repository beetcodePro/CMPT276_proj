/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherites Entity and has movespeed.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Oct 29th, 2022
 *
*/

package entities;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AnimateEntity extends Entity
{
    // Default attributes
    private int spriteCnt = 0;
    private int spriteNum = 1;
    private int moveSpeed = 5;
    private boolean canCollide = false;

    // Constructed attributes
    protected BufferedImage down1, down2, down3, up1, up2, up3, right1, right2, left1, left2;
    private String direction;
    private Rectangle hitbox;

    // Default constructor (must pass X/Y coordinates and sets moveSpeed to default)
    public AnimateEntity(int setX, int setY)
    {
        super(setX, setY);
        this.direction = "down";
    }

    // Parameterized constructor (must pass X/Y coordinates and custom moveSpeed)
    public AnimateEntity(int setX, int setY, int setSpeed)
    {
        super(setX, setY);
        this.moveSpeed = setSpeed;
        this.direction = "down";
    }

    // Getters
    public int get_moveSpeed() { return this.moveSpeed; }
    public String get_direction() { return this.direction; }
    public int get_spriteCnt() { return this.spriteCnt; }
    public int get_spriteNum() { return this.spriteNum; }
    public Rectangle get_hitbox() { return this.hitbox; }
    public boolean get_canCollide() { return this.canCollide; }

    // Setters
    public void set_moveSpeed(int val) { this.moveSpeed = val; }
    public void set_direction(String val) { this.direction = val; }
    public void set_spriteCnt(int val) { this.spriteCnt = val; }
    public void set_spriteNum(int val) { this.spriteNum = val; }
    public void set_hitbox(Rectangle val) { this.hitbox = val; }
    public void set_canCollide(boolean val) { this.canCollide = val; }

    // Increase sprite counter, used for sprite animation
    public void increase_spriteCnt() 
    { 
        this.spriteCnt++;

        if(spriteCnt > 18)  // Increase/decrease this num to change animation frequency
        {
            if(spriteNum < 4)
                this.spriteNum++;
            else
                this.spriteNum = 1;
            this.spriteCnt = 0;
        }
    }
}

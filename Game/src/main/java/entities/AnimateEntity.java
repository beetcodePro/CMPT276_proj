/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherites Entity and has movespeed.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Oct 28th, 2022
 *
*/

package entities;
import java.awt.image.BufferedImage;

abstract class AnimateEntity extends Entity
{
    // Attributes
    private int moveSpeed;
    protected BufferedImage down1, down2, down3, up1, up2, up3, right1, right2, left1, left2;
    private String direction;
    private int spriteCnt;
    private int spriteNum;

    // Default constructor (must pass X/Y coordinates and sets moveSpeed to default)
    public AnimateEntity(int setX, int setY)
    {
        super(setX, setY);
        this.moveSpeed = 5;
        this.direction = "down";
        this.spriteCnt = 0;
        this.spriteNum = 1;
    }

    // Parameterized constructor (must pass X/Y coordinates and custom moveSpeed)
    public AnimateEntity(int setX, int setY, int setSpeed)
    {
        super(setX, setY);
        this.moveSpeed = setSpeed;
        this.direction = "down";
        this.spriteCnt = 0;
        this.spriteNum = 1;
    }

    // Getters
    public int get_moveSpeed() { return this.moveSpeed; }
    public String get_direction() { return this.direction; }
    public int get_spriteCnt() { return this.spriteCnt; }
    public int get_spriteNum() { return this.spriteNum; }

    // Setters
    public void set_moveSpeed(int val) { this.moveSpeed = val; }
    public void set_direction(String val) { this.direction = val; }
    public void set_spriteCnt(int val) { this.spriteCnt = val; }
    public void set_spriteNum(int val) { this.spriteNum = val; }

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

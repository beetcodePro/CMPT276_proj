/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherites Entity and has movespeed.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Nov 1st, 2022
 *
*/

package entities;
import java.awt.image.BufferedImage;
import main.Simulator;
import main.CheckCollision;

public abstract class AnimateEntity extends Entity
{
    // Default attributes
    private int spriteCnt = 0;
    private int spriteNum = 1;
    private int moveSpeed = 4;

    // Constructed attributes
    protected Simulator sim;
    protected CheckCollision collideCheck;
    protected BufferedImage down1, down2, down3, up1, up2, up3, right1, right2, left1, left2;
    private String direction;

    // Default constructor (must pass X/Y coordinates and sets moveSpeed to default)
    public AnimateEntity(int setX, int setY, Simulator setSim, CheckCollision setCol)
    {
        super(setX, setY);
        this.sim = setSim;
        this.collideCheck = setCol;
        this.direction = "down";
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

    // Default update, updates AnimateEntity movement
    public void update()
    {
        nextMove();
        this.set_canCollide(false);
        this.collideCheck.checkTile(this);
        this.collideCheck.checkPlayer(this);
        this.collideCheck.checkEnemy(this);

        if(this.get_canCollide() == false)
        {
            switch(this.get_direction())
            {
                case "right":
                {
                    int x = this.get_coordinate_X();
                    x = x + this.get_moveSpeed();
                    this.set_coordinate_X(x);
                    break;
                }
                case "left":
                {
                    int x = this.get_coordinate_X();
                    x = x - this.get_moveSpeed();
                    this.set_coordinate_X(x);
                    break;
                }
                case "up":
                {
                    int y = this.get_coordinate_Y();
                    y = y - this.get_moveSpeed();
                    this.set_coordinate_Y(y);
                    break;
                }
                case "down":
                {
                    int y = this.get_coordinate_Y();
                    y = y + this.get_moveSpeed();
                    this.set_coordinate_Y(y);
                    break;
                }
            }
        }
        // Animation change
        this.increase_spriteCnt();
    }

    // TO BE OVERRIDDEN: Set the direction to move in next time update is called
    public void nextMove() {}

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

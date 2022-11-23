/*  
 *  Enemy.java
 *  
 *  Description: Base enemy with basic movement.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Nov 20th, 2022
 *
*/

package entities;
import main.Simulator;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;
import main.CheckCollision;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Enemy extends AnimateEntity 
{
    // Attributes
    private int actionInterval = 0;

    // Default constructor
    public Enemy(Simulator setSim, int setX, int setY)
    {
        super(setX, setY, setSim, setSim.get_collisionChecker());
        this.set_moveSpeed(2);
        this.get_sprite();
        this.config_hitbox();
    }

    // Parametrized constructor
    public Enemy(Simulator setSim, CheckCollision setCol, int setX, int setY)
    {
        super(setX, setY, setSim, setCol);
        this.set_moveSpeed(2);
        this.get_sprite();
        this.config_hitbox();
    }

    // Configure hitbox (called on constructor ONLY)
    private void config_hitbox()
    {
        Rectangle config = new Rectangle();
        config.x = 9;           // hitbox border width
        config.y = 9;           // hitbox border height
        config.width = 33;      // hitbox width
        config.height = 36;     // hitbox height
        this.set_hitbox(config);
        generate_direction();
    }

    // Load enemy sprites
    public void get_sprite() 
    {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_down_3.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_up_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/enemy/evilon_left_2.png"));
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    // Getters
    public int get_actionInterval() { return this.actionInterval; }

    // Setters
    public void set_actionInterval(int val) { this.actionInterval = val; }

    // Draw enemy on user interface
    public void draw(Graphics2D g2)
    {
        BufferedImage img = null;
        int X = this.get_coordinate_X();
        int Y = this.get_coordinate_Y();
        int tileSize = this.sim.get_tileSize();

        switch(this.get_direction())
        {
            case "down":
                if(this.get_spriteNum() == 1)
                    img = down1;
                if(this.get_spriteNum() == 2)
                    img = down2;
                if(this.get_spriteNum() == 3)
                    img = down1;
                if(this.get_spriteNum() == 4)
                    img = down3;
                break;
            case "up":
                if(this.get_spriteNum() == 1)
                    img = up1;
                if(this.get_spriteNum() == 2)
                    img = up2;
                if(this.get_spriteNum() == 3)
                    img = up1;
                if(this.get_spriteNum() == 4)
                    img = up3;
                break;
            case "right":
                if(this.get_spriteNum() == 1)
                    img = right1;
                if(this.get_spriteNum() == 2)
                    img = right1;
                if(this.get_spriteNum() == 3)
                    img = right2;
                if(this.get_spriteNum() == 4)
                    img = right2;
                break;
            case "left":
                if(this.get_spriteNum() == 1)
                    img = left1;
                if(this.get_spriteNum() == 2)
                    img = left1;
                if(this.get_spriteNum() == 3)
                    img = left2;
                if(this.get_spriteNum() == 4)
                    img = left2;
                break;
        }
        g2.drawImage(img, X, Y, tileSize, tileSize, null);
    }

    // Set the direction to move in next time update is called
    public void nextMove()
    {
        this.actionInterval++;

        // Switch direction after 90 frames
        if(this.actionInterval == 75) { generate_direction(); }
    }

    // Sets enemy to move in random direction
    private void generate_direction()
    {
        Random generate = new Random();
        int rng = generate.nextInt(100)+1;  // generates int from 0-100

        if(rng <= 25) 
            set_direction("down");
        else if(rng <= 50)
            set_direction("up");
        else if(rng <= 75)
            set_direction("right");
        else
            set_direction("left");

        this.actionInterval = 0;
    }

    // Overrides default update, updates AnimateEntity movement
    public void update()
    {
        nextMove();
        this.set_canCollide(false);
        this.collideCheck.checkTile(this);
        if(this.collideCheck.checkPlayer(this) == true)
            player_onCollision();
        this.collideCheck.checkEnemy(this);

        if(this.get_canCollide() == false)
        {
            move_enemy();
        }
        // Animation change
        this.increase_spriteCnt();
    }

    // Checks direction and moves enemy
    public void move_enemy()
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

    // This function runs when enemy collides into player
    public void player_onCollision()
    {
        sim.PlaySoundEffect(3);
        sim.reset_player_position();
        sim.add_player_lives(-1);
    }
}

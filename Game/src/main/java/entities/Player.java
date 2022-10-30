/*  
 *  Player.java
 *  
 *  Description: Controls player UI movement and player stats.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Oct 29th, 2022
 *
*/

package entities;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import main.Simulator;
import main.KeyBoard;
import main.CheckCollision;

public class Player extends AnimateEntity
{
    // Default attributes
    private int score = 0;
    private int lives = 0;

    // Constructed attributes
    private Simulator sim;
    private KeyBoard keyboard;
    private CheckCollision colideCheck;

    // Default constructor (must pass specified parameters, sets moveSpeed to default)
    public Player(Simulator setSim, KeyBoard setKey, CheckCollision setColl, int setX, int setY)
    {
        super(setX, setY);
        this.sim = setSim;
        this.keyboard = setKey;
        this.colideCheck = setColl;
        this.get_sprite();
        this.config_hitbox();
    }

    // Parameterized constructor (must pass specified parameters)
    public Player(Simulator setSim, KeyBoard setKey, CheckCollision setColl, int setX, int setY, int setSpeed)
    {
        super(setX, setY, setSpeed);
        this.sim = setSim;
        this.keyboard = setKey;
        this.colideCheck = setColl;
        this.get_sprite();
        this.config_hitbox();
    }

    // Getters
    public int get_score() { return this.score; }
    public int get_lives() { return this.lives; }

    // Setters
    public void change_score(int change) { this.score = this.score + change; }
    public void change_lives(int change) { this.lives = this.lives + change; }

    // Configure hitbox (called on constructor ONLY)
    private void config_hitbox()
    {
        Rectangle config = new Rectangle();
        config.x = 6;           // hitbox border width
        config.y = 9;           // hitbox border height
        config.width = 36;      // hitbox width
        config.height = 36;     // hitbox height
        this.set_hitbox(config);
    }

    // Update player movement
    public void update()
    {
        if(keyboard.PressedRT == true || keyboard.PressedLF == true || keyboard.PressedUp == true || keyboard.PressedDown == true)
        {
            // Check and set direction based on keyboard inpute
            if (keyboard.PressedRT == true)
                this.set_direction("right");
            if (keyboard.PressedLF == true)
                this.set_direction("left");
            if (keyboard.PressedUp == true)
                this.set_direction("up");
            if (keyboard.PressedDown == true)
                this.set_direction("down");

            // Check tile collision
            this.set_canCollide(false);
            this.colideCheck.checkTileForPlayer(this);

            // Move player if canCollide is false
            if(get_canCollide() == false)
            { 
                if(keyboard.PressedRT == true)
                {
                    int x = this.get_coordinate_X();
                    x = x + this.get_moveSpeed();
                    this.set_coordinate_X(x);
                }
                if(keyboard.PressedLF == true)
                {
                    int x = this.get_coordinate_X();
                    x = x - this.get_moveSpeed();
                    this.set_coordinate_X(x);
                }
                if(keyboard.PressedUp == true)
                {
                    int y = this.get_coordinate_Y();
                    y = y - this.get_moveSpeed();
                    this.set_coordinate_Y(y);
                }
                if(keyboard.PressedDown == true)
                {
                    int y = this.get_coordinate_Y();
                    y = y + this.get_moveSpeed();
                    this.set_coordinate_Y(y);
                }
            }
            // Animation change
            this.increase_spriteCnt();
        }
    }

    // Draw player on user interface
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

    // Load player sprites
    public void get_sprite() 
    {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/minion_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/minion_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/minion_down_3.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/minion_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/minion_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/minion_up_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/minion_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/minion_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/minion_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/minion_left_2.png"));
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    // Checks if player has no lives left
    public boolean check_if_no_lives()
    {
        if(this.lives <= 0)
            return true;
        else
            return false;
    }

    // TODO: collision detection
    // public string check_tile();

    // TODO: pauses player input if game is paused
    // public boolean is_game_paused();

    // TODO: checks if all rewards are collected on the map
    // public boolean all_rewards_collected();

    // TODO: checks if player coordinate is equal to endGoal
    // public boolean goal_reached();
}


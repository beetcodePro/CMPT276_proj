/*  
 *  Player.java
 *  
 *  Description: Controls player UI movement and player stats.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Nov 15th, 2022
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
    private int lives = 3;

    // Constructed attributes
    private KeyBoard keyboard;

    // Default constructor (must pass specified parameters, sets moveSpeed to default)
    public Player(Simulator setSim, KeyBoard setKey, CheckCollision setCol, int setX, int setY)
    {
        super(setX, setY, setSim, setCol);
        this.keyboard = setKey;
        this.get_sprite();
        this.config_hitbox();
    }

    // Getters
    public int get_score() { return this.score; }
    public int get_lives() { return this.lives; }
    public KeyBoard get_keyboard() { return this.keyboard; }

    // Setters
    public void set_score(int val) { this.score = val; }
    public void set_lives(int val) { this.lives = val; }
    public void add_score(int change) { this.score = this.score + change; }
    public void add_lives(int change) { this.lives = this.lives + change; }

    // Configure hitbox (called on constructor ONLY)
    private void config_hitbox()
    {
        Rectangle config = new Rectangle();
        config.x = 5;           // hitbox border width
        config.y = 12;           // hitbox border height
        config.width = 34;      // hitbox width
        config.height = 30;     // hitbox height
        this.set_hitbox(config);
    }

    // Reset player position function
    public void setDefaultPosition() { set_coordinate(sim.get_player_default_x(), sim.get_player_default_y()); }

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
            this.collideCheck.checkTileForPlayer(this);

            // Check object collision
            int objIndex = this.collideCheck.checkObject(this, true);
            obj_onCollision(objIndex);

            // Check enemy collision
            int enemyIndex = this.collideCheck.checkEnemy(this);
            enemy_onCollision(enemyIndex);

            // Move player if canCollide is false
            if(get_canCollide() == false)
            { 
                this.move_player();
            }
            // Animation change
            this.increase_spriteCnt();
            if (check_if_no_lives() || (score<0))
            {
                sim.gameState=sim.gameOverSate;
            }
            if (lives >=0  || (score > 0))
            {
                //System.out.println(this.get_coordinate_X());
                //System.out.println(this.get_coordinate_Y());
                if(this.get_coordinate_X()==90 && this.get_coordinate_Y()==474)
                {
                    sim.gameState=sim.gameWinSate;
                }

            }
        }
    }

    // Checks keyboard and moves player
    public void move_player()
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

    // This function runs when player collides with object
    private void obj_onCollision(int index)
    {
        if(index != -1)
        {
            String objectName = this.collideCheck.getObjectName(index);

            object_collision(objectName);

            if(objectName == "Banana")
                this.collideCheck.deleteObject(index);

            if(objectName == "Apple")
                this.collideCheck.deleteObject(index);

            if (objectName == "Trap")
                sim.reset_player_position();
        }
    }

    // Object collision instructions
    public void object_collision(String objName)
    {
        if(objName == "Banana")
        {
            sim.PlaySoundEffect(2);
            score = score + 20;
            sim.ui.showMessage("+20 Score");
        }
        if(objName == "Apple")
        {
            sim.PlaySoundEffect(1);
            lives = lives + 1;
            sim.ui.showMessage("+1 Life");
        }
        if(objName == "Trap")
        {
            sim.PlaySoundEffect(3);
            lives = lives - 1;
            sim.ui.showMessage("-1 Life");
        }
    }

    // This function runs when player collides with enemy
    public void enemy_onCollision(int index)
    {
        if(index != -1)
        {
            sim.PlaySoundEffect(3);
            this.set_coordinate(sim.get_player_default_x(), sim.get_player_default_y());
            lives = lives - 1;
            sim.ui.showMessage("-1 Life");
        }
    }

    // Checks if player has no lives left
    public boolean check_if_no_lives()
    {
        if(this.lives < 0)
            return true;
        else
            return false;
    }
}


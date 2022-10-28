/*  
 *  Player.java
 *  
 *  Description: Controls player UI movement and player stats.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Oct 27th, 2022
 *
*/

package main.entities;
import java.awt.*;
import main.Simulator;
import main.KeyBoard;

public class Player extends AnimateEntity
{
    // Attributes
    private Simulator sim;
    private KeyBoard keyboard;
    private int score;
    private int lives;
    // private int rewardsCollected;
    // private Coordinate endGoal;

    // Default constructor (must pass specified parameters, sets moveSpeed to default)
    public Player(Simulator setSim, KeyBoard setKey, int setX, int setY)
    {
        super(setX, setY);
        this.sim = setSim;
        this.keyboard = setKey;
        this.score = 0;
        this.lives = 0;
    }

    // Parameterized constructor (must pass specified parameters)
    public Player(Simulator setSim, KeyBoard setKey, int setX, int setY, int setSpeed)
    {
        super(setX, setY, setSpeed);
        this.sim = setSim;
        this.keyboard = setKey;
        this.score = 0;
        this.lives = 0;
    }

    // Getters
    public int get_score() { return this.score; }
    public int get_lives() { return this.lives; }

    // Setters
    public void change_score(int change) { this.score = this.score + change; }
    public void change_lives(int change) { this.lives = this.lives + change; }

    // Update player movement
    public void update()
    {
        if (keyboard.PressedRT == true)
        {
            int x = this.get_coordinate_X();
            x = x + this.get_moveSpeed();
            this.set_coordinate_X(x);
        }
        if (keyboard.PressedLF == true)
        {
            int x = this.get_coordinate_X();
            x = x - this.get_moveSpeed();
            this.set_coordinate_X(x);
        }
        if (keyboard.PressedUp == true)
        {
            int y = this.get_coordinate_Y();
            y = y - this.get_moveSpeed();
            this.set_coordinate_Y(y);
        }
        if (keyboard.PressedDown == true)
        {
            int y = this.get_coordinate_Y();
            y = y + this.get_moveSpeed();
            this.set_coordinate_Y(y);
        }
    }

    // Draw player on user interface
    public void draw(Graphics2D g2)
    {
        g2.setColor(Color.GRAY);
        g2.fillRect(this.get_coordinate_X(), this.get_coordinate_Y(), this.sim.get_tileSize(), this.sim.get_tileSize());
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


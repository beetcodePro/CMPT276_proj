/*  
 *  Player.java
 *  
 *  Description: Placeholder
 * 
 *  Author: Lionel
 *
*/

package main.entities;

public class Player 
{
    // Attributes
    private int score;
    private int lives;
    private int rewardsCollected;
    // private Main simulator
    // private Coordinate endGoal

    // Default constructor
    public Player()
    {
        this.score = 0;
        this.lives = 0;
        this.rewardsCollected = 0;
    }

    // Getters
    public int get_score() { return this.score; }
    public int get_lives() { return this.lives; }

    // Setters
    public void change_score(int change) { this.score = this.score + change; }
    public void change_lives(int change) { this.lives = this.lives + change; }

    // Checks if player has no lives left
    public boolean check_if_no_lives()
    {
        if(this.lives <= 0)
            return true;
        else
            return false;
    }

    // TODO: move entity by keyboard input, calls check_tile()
    public void move_entity();

    // TODO: checks if tile has been occupied by another entity
    public string check_tile();

    // TODO: pauses player input if game is paused
    public boolean is_game_paused();

    // TODO: checks if all rewards are collected on the map
    public boolean all_rewards_collected();

    // TODO: checks if player coordinate is equal to endGoal
    public boolean goal_reached();
}


/*  
 *  EntityList.java
 *  
 *  Description: Stores array of existing entity classes.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Oct 30th, 2022
 *
*/

package main;
import entities.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;

public class EntityList 
{
    // List of entity arrays
    private List<Enemy> enemyList;

    // Default constructor
    public EntityList()
    {
        this.enemyList = new ArrayList<Enemy>();
    }

    // Getters
    public List<Enemy> get_enemyList() { return this.enemyList; }
    public int get_enemyList_size() { return this.enemyList.size(); }

    // EnemyList functions
    public void add_enemy(Enemy toAdd) { this.enemyList.add(toAdd);}

    public void update_enemyList()
    {
        for(int i = 0; i < enemyList.size(); i++)
            this.enemyList.get(i).update();
    }

    public void draw_enemyList(Graphics2D g2)
    {
        for(int i = 0; i < enemyList.size(); i++)
            this.enemyList.get(i).draw(g2);
    }
}

package org.example;

import entities.Player;
import junit.framework.TestCase;
import main.*;

public class SoundTest extends TestCase {
    sound Sound= new sound();
    Simulator sim = new Simulator();
    KeyBoard key = new KeyBoard(sim);
    Player player= new Player(sim, key, new CheckCollision(sim, key, new EntityList()), 0, 0);
    /**
     * @Test object collision with banana sound effect
     */
    public void bananaCollisionSound()
    {
        player.object_collision("Banana");
        assertEquals(sim.PlaySoundEffect(1), Sound.getSoundIndex("Banana"));
    }
    /**
     * @Test object collision with apple sound effect
     */
    public void appleCollisionSound()
    {
        player.object_collision("Apple");
        assertEquals(sim.PlaySoundEffect(1), Sound.getSoundIndex("Apple"));
    }
    /**
     * @Test object collision with trap sound effect
     */
    public void trapCollisionSound()
    {
        player.object_collision("Trap");
        assertEquals(sim.PlaySoundEffect(3), Sound.getSoundIndex("Trap"));
    }
    /**
     * @Test object collision with enemy sound effect
     */
    public void enemyCollisionSound()
    {
        player.object_collision("Enemy");
        assertEquals(sim.PlaySoundEffect(3), Sound.getSoundIndex("Enemy"));
    }
    /**
     * @Test object collision with door sound effect
     */
    public void DoorCollisionSound()
    {
        player.object_collision("Door");
        assertEquals(sim.PlaySoundEffect(0), Sound.getSoundIndex("Door"));
    }

}

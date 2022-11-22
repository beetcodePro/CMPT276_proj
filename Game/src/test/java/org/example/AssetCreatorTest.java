package org.example;

import entities.Enemy;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;
import objects.obj_apple;
import objects.obj_banana;
import objects.obj_door;
import objects.obj_trap;

public class AssetCreatorTest extends TestCase {
    //Attributes
    private Simulator sim;
    private AssetCreator assets;

    /**
     * Create the test case.
     * @param testName name of the test case
     */
    public AssetCreatorTest(String testName){
        super(testName);
        sim = new Simulator();
        assets = new AssetCreator(sim, new EntityList());
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite(EntityListTest.class);
        suite.addTest(new AssetCreatorTest("checkIfObjectExists"));
        suite.addTest(new AssetCreatorTest("checkIfTileExists"));
        suite.addTest(new AssetCreatorTest("creatingObjects"));
        suite.addTest(new AssetCreatorTest("creatingEnemies"));
        suite.addTest(new AssetCreatorTest("addingApple"));
        return suite;
    }


    /**
     * @return checking if coordinate isn't occupied by other objects
     */
    public void checkIfCoordinatePasses(){
        assets.entityList.clear_objList();
        int tempX = 5, tempY = 10;
        obj_trap Trap = new obj_trap(tempX, tempY);
        assets.entityList.add_obj(Trap);
        boolean spaceIsOkay = assets.checkObjectAtCoordinate(assets.entityList.get_obj_at_index(0).get_coordinate_X(),
                assets.entityList.get_obj_at_index(0).get_coordinate_Y(),true );
        assertEquals(spaceIsOkay, false);
    }


    /**
     * @return checking if tile exists so object can be placed
     */
    public void checkIfTileExists(){
        assets.entityList.clear_objList();
        int tempX = 5, tempY = 10;
        obj_trap Trap = new obj_trap(tempX, tempY);
        assets.entityList.add_obj(Trap);
        boolean tileIsReal = assets.checkObjectAtCoordinate(assets.entityList.get_obj_at_index(0).get_coordinate_X(),
                assets.entityList.get_obj_at_index(0).get_coordinate_Y(), true);
        assertEquals(tileIsReal, false);

    }

    /**
     * @test creates set of objects for game
     */
    public void creatingObjects(){
        assets.entityList.clear_objList();
        assets.setObject();
        obj_trap trap = new obj_trap(5, 10);
        assertEquals(assets.entityList.get_obj_at_index(17), trap);

    }

    /**
     * @test creates set of enemies
     */
    public void creatingEnemies(){
        assets.entityList.clear_enemyList();
        CheckCollision check = new CheckCollision(sim, new KeyBoard(sim), assets.entityList);
        assets.setEnemy(check);
        assertEquals(assets.entityList.get_enemy_at_index(3), new Enemy(sim, check, 5, 10));
    }
    /**
     * @test creates a set of apples for them to reappear on the map
     */
    public void addingApple(){
        assets.entityList.clear_objList();
        assets.addApple();
        obj_apple apple = new obj_apple(5, 10);
        assertEquals(assets.entityList.get_obj_at_index(1), apple);
    }

}

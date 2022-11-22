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
        obj_door door = new obj_door(5, 10);
        obj_trap trap = new obj_trap(5, 10);
        obj_apple apple = new obj_apple(5, 10);
        obj_banana banana = new obj_banana(5, 10);
        for (int i = 0; i < 18; i++){
            if (i == 0){
                assertEquals(assets.entityList.get_obj_at_index(i), door);
            }else if (i >= 1 && i <= 8){
                assertEquals(assets.entityList.get_obj_at_index(i), banana);
            }else if (i >= 9 && i <= 10){
                assertEquals(assets.entityList.get_obj_at_index(i), apple);
            }else{
                assertEquals(assets.entityList.get_obj_at_index(i), trap);
            }
        }
    }

    /**
     * @test creates set of enemies
     */
    public void creatingEnemies(){
        assets.entityList.clear_enemyList();
        CheckCollision check = new CheckCollision(sim, new KeyBoard(sim), assets.entityList);
        assets.setEnemy(check);
        for (int i = 0; i < 4; i++){
            assertEquals(assets.entityList.get_enemy_at_index(i), new Enemy(sim, check, 5, 10));
        }
    }

}

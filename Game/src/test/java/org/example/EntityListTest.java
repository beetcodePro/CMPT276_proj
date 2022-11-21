/* 
 *  EntityListTest.java
 * 
 *  Description: Tests methods of EntityList class.
 * 
 *  Last changed: Nov 20th, 2022
 *
*/

package org.example;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;
import entities.*;
import objects.*;

public class EntityListTest extends TestCase
{
    // Attributes
    private EntityList list;

    /**
     * Create the test case.
     * @param testName name of the test case
    */
    public EntityListTest(String testName)
    {
        super(testName);
        list = new EntityList();
    }

    /**
    * @return the suite of tests being tested
    */
    public static Test suite()
    {
        TestSuite suite = new TestSuite(EntityListTest.class);
        suite.addTest(new EntityListTest("addObject"));
        return suite;
    }

    /**
     * @Test adding object to object list
    */
    public void addObject()
    {
        list.add_obj(new obj_apple(0, 0));
    }

}

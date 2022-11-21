package org.example;

import junit.framework.TestCase;
import main.AssetCreator;
import main.EntityList;
import main.Simulator;

public class AssetCreatorTest extends TestCase {
    //Attributes
    private Simulator sim;
    private EntityList list;

    /**
     * Create the test case.
     * @param testName name of the test case
     */
    public AssetCreatorTest(String testName){
        super(testName);
        sim = new Simulator();
        list = new EntityList();
    }
}

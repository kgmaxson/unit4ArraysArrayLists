

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a description of test class RadarTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RadarTest
{
    /** description of instance variable x (add comment for each instance variable) */

    /**
     * Default constructor for objects of class RadarTest
     */
    public RadarTest()
    {
        // initialise instance variables

    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void test1()
    {
        
        Radar radar = new Radar(100, 100, 10, 15);
        radar.setNoiseFraction(0.005);
        radar.scan(4,2);

        // perform 30 scans of the radar
        for(int i = 0; i < 30; i++)
        {
            radar.scan(4*i,2*i);
        }
        int[] compare = new int[2];
        compare[0]=4;
        compare[1]=2;
        assertEquals(radar.getVelocity()[0],compare[0]);
        assertEquals(radar.getVelocity()[1],compare[1]);
    }
    
    @Test
    public void test2()
    {
        
        Radar radar = new Radar(100, 100, 20, 35);
        radar.setNoiseFraction(0.005);
        radar.scan(3,-4);

        // perform 30 scans of the radar
        for(int i = 0; i < 30; i++)
        {
            radar.scan(3*i,-4*i);
        }
        int[] compare = new int[2];
        compare[0]=3;
        compare[1]=-4;
        assertEquals(radar.getVelocity()[0],compare[0]);
        assertEquals(radar.getVelocity()[1],compare[1]);
    }
    
    @Test
    public void test3()
    {
        
        Radar radar = new Radar(100, 100, 89, 12);
        radar.setNoiseFraction(0.005);
        radar.scan(-2,1);

        // perform 30 scans of the radar
        for(int i = 0; i < 30; i++)
        {
            radar.scan(-2*i,1*i);
        }
        int[] compare = new int[2];
        compare[0]=-2;
        compare[1]=1;
        assertEquals(radar.getVelocity()[0],compare[0]);
        assertEquals(radar.getVelocity()[1],compare[1]);
    }
}

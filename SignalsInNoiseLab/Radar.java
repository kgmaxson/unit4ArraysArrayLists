
/**
 * The model for radar scan and accumulator
 * 
 * @author @kgmaxson
 * @version 12 December 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    private boolean[][] currentScan;
    
    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulator;
    private int[][] updatedAccumulator = new int[11][11];
    
    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;
    
    // speed of the monster
    private int dx;
    private int dy;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;
    
    private boolean[][] pastScan = new boolean [100][100];


    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols, int monsterLocationRow, int monsterLocationCol)
    {
        // initialize instance variables
        currentScan = new boolean[rows][cols]; // elements will be set to false
        accumulator = new int[rows][cols]; // elements will be set to 0
        
        // sets the location and speed of the monster
        this.monsterLocationRow = monsterLocationRow;
        this.monsterLocationCol = monsterLocationCol;

        
        noiseFraction = 0.01;
        numScans= 0;
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public void scan(int dx, int dy)
    {
        //store last scan
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                pastScan[row][col] = currentScan[row][col];
            }
        }
        
        // zero the current scan grid
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                currentScan[row][col] = false;
            }
        }
        
        // detect the monster, checks to see if range is out of the screen
        if (monsterLocationRow+dy<0)
        {
            monsterLocationRow+=100;
        }
        if (monsterLocationRow+dy>=100)
        {
            monsterLocationRow-=100;
        }
        if (monsterLocationCol+dx<0)
        {
            monsterLocationCol+=100;
        }
        if (monsterLocationCol+dx>=100)
        {
            monsterLocationCol-=100;
        }
        currentScan[monsterLocationRow+dy][monsterLocationCol+dx] = true;
        
        // inject noise into the grid
        injectNoise();
        
        // update the accumulator
        for(int row = 5; row < currentScan.length-5; row++)
        {
            for(int col = 5; col < currentScan[0].length-5; col++)
            {
                if(pastScan[row][col])
                {
                   for (int i = -5; i<=5;i++)
                   {
                       for (int j=-5; j<=5;j++)
                       {
                           if (currentScan[row+i][col+j])
                           {
                               updatedAccumulator[i+5][j+5]++;
                           }
                       }
                   }
                }
            }
        }
        
        // keep track of the total number of scans
        numScans++;
    }

    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation(int row, int col)
    {
        // remember the row and col of the monster's location
        monsterLocationRow = row;
        monsterLocationCol = col;
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[row][col] = true;
    }
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
    public boolean isDetected(int row, int col)
    {
        return currentScan[row][col];
    }
    
    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        return accumulator[row][col];
    }
    
    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
    public int getNumRows()
    {
        return currentScan.length;
    }
    
    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }
    
    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }
    
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true;
                }
            }
        }
    }
    
    /**
     * Returns the velocity
     * 
     * @return the velocity
     */
    public int[] getVelocity()
    {
        int max = 0;
        int[] location = new int[2];
        int dyf=0;
        int dxf=0;
        for(int row = 0; row < 11; row++)
        {
            for(int col = 0; col < 11; col++)
            {
               System.out.println(row+" "+col+" "+updatedAccumulator[row][col]);
                if (updatedAccumulator[row][col] > max)
               {                
                   max = updatedAccumulator[row][col];
                   location[0]=row-5;
                   location[1]=col-5;
               }
            }
        }
        int[] velocity = new int[2];
        velocity[0]=location[1];
        velocity[1]=location[0];
        return velocity; 
    }
}

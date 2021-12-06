import java.io.*;
import java.lang.Math;
import java.io.BufferedReader;

public class Closest
{
    //A 2D array of neighbors for easier acces and usage. We remove entire left and and sector(-1, 0) to rid of redundancy and lower time and usage. 
    private static final int[][] NEIGHBORS = {
                         {-1, +1},
                {0, 0},  {0, +1},
                {+1, 0}, {+1, +1}};
    double minimum = 1;

    /**
    *   intArray method takes an array of string numbers and puts them into an array of doubles.
    *   @param String[] numbers: array of string representation of numbers.
    *   @return double[] ints: array of doubles
    **/
    public double[] intArray(String[] numbers)
    {
        double[] ints = new double[numbers.length];
        for(int i = 0; i < numbers.length; i++)
        {
            ints[i] = Double.parseDouble(numbers[i]);
        }
        return ints;
    }
    
    /**
    *   isOnMap method that helps check to see if given x and y are in bounds of the map.
    *   @param int y: y coor
    *   @param int x: x coor
    *   @param int size: size of given table
    * @return bool
    **/
    public boolean isOnMap(int y, int x, int size)
    {
        return x >= 0 && y >= 0 && x < size && y < size;
    }
    
    /**
    *   checkNeighbors method to check for neighbors of given x and y
    *   @param HashTable ht: given hashtable that holds the points
    *   @param int y: y coor point
    *   @param int x: x coor point
    *   @param double[] firstpoint: single array of point to be compared against
    *   @return double minimum: shortest distant found.
    **/
    public double checkNeighbors(HashTable ht, int y, int x, double[] firstPoint)
    {
        //for loop going through neighbors array
        for(int[] offset: NEIGHBORS)
        {
            //check first if neighbors are on grid
            if (isOnMap(y + offset[0], x + offset[1], ht.getSize()))
            {
                Node cur = ht.getTable(y + offset[0],x + offset[1]);
                if(cur != null)
                {
                    //for loop going through neighbor node list until end
                    for(Node neighbor = ht.getTable(y + offset[0],x + offset[1]); neighbor.next != null; neighbor = neighbor.next)
                    {
                        String[] secondString = neighbor.data.split(",");
                        double[] secondPoint = intArray(secondString);
                        
                        if(secondPoint[0] != firstPoint[0] && secondPoint[1] != firstPoint[1])
                        {
                            //formula sqrt((x2 − x1)^2 + (y2 − y1)^2)
                            double tempNum = Math.sqrt(Math.pow(secondPoint[0] - firstPoint[0], 2) 
                                        + Math.pow(secondPoint[1] - firstPoint[1], 2));
                            
                            //check if minimum tracked is greater then last given result
                            if (minimum > tempNum)
                            {   
                                minimum = tempNum;
                            }
                        }
                    }
                }
              }
        }
        return minimum;
    }
    
    /**
    *   main method that reads in file and hashes points to hashtable, calls methods to calculate shortest point distance.
    *   @param String[] args
    *   throws IOException
    **/
    public static void main(String[] args) throws IOException
    {
        //We use a bufferedReader and cast it as InputStreamReader to reduce read in time.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashTable ht = new HashTable(1000);
        Closest c = new Closest();
        String line = "";
        
        //reads in points from file and hashes
        while ((line=br.readLine()) != null)
        {
            String[] temp = line.split(" ");
            double x = Double.parseDouble(temp[0]);
            double y = Double.parseDouble(temp[1]);
            ht.insert(x, y);
        }
        
        
        String[] firstString = new String[2];
        String[] secondString = new String[2];
        
        //for loop for running the methods to calculate shortest distance.
        for(int i = 0; i < ht.getSize();i++)
        {
            for(int j = 0; j < ht.getSize();j++)
            {
                Node test = ht.getTable(i, j);
                if(test != null)
                {
                for (Node cur = ht.getTable(i, j); cur.next != null; cur = cur = cur.next)
                {
                    firstString = cur.data.split(",");
                    double[] firstPoint = c.intArray(firstString);
                    c.checkNeighbors(ht, i, j, firstPoint);
                }
                }
            }
        }
        System.out.println("The closest pair of points is: " + c.minimum);
    }
}

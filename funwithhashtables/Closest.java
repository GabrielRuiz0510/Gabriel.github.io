import java.io.*;
import java.lang.Math;
import java.io.BufferedReader;

public class Closest
{
    private static final int[][] NEIGHBORS = {
                         {-1, +1},
                {0, 0},  {0, +1},
                {+1, 0}, {+1, +1}};
    double minimum = 1;


    public double[] intArray(String[] numbers)
    {
        double[] ints = new double[numbers.length];
        for(int i = 0; i < numbers.length; i++)
        {
            ints[i] = Double.parseDouble(numbers[i]);
        }
        return ints;
    }
    
    public boolean isOnMap(int y, int x, int size)
    {
        return x >= 0 && y >= 0 && x < size && y < size;
    }
    
    public double checkNeighbors(HashTable ht, int y, int x, double[] firstPoint)
    {
        for(int[] offset: NEIGHBORS)
        {
            if (isOnMap(y + offset[0], x + offset[1], ht.getSize()))
            {
                Node cur = ht.getTable(y + offset[0],x + offset[1]);
                if(cur != null)
                {
                    for(Node neighbor = ht.getTable(y + offset[0],x + offset[1]); neighbor.next != null; neighbor = neighbor.next)
                    {
                        String[] secondString = neighbor.data.split(",");
                        double[] secondPoint = intArray(secondString);
                        if(secondPoint[0] != firstPoint[0] && secondPoint[1] != firstPoint[1])
                        {
                        double tempNum = Math.sqrt(Math.pow(secondPoint[0] - firstPoint[0], 2) 
                                        + Math.pow(secondPoint[1] - firstPoint[1], 2));
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
    
   
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashTable ht = new HashTable(1000);
        Closest c = new Closest();
        String line = "";
        while ((line=br.readLine()) != null)
        {
            String[] temp = line.split(" ");
            double x = Double.parseDouble(temp[0]);
            double y = Double.parseDouble(temp[1]);
            ht.insert(x, y);
        }
        String[] firstString = new String[2];
        String[] secondString = new String[2];

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

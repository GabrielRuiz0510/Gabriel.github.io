import java.util.Scanner;

public class Jugs
{
    private boolean[][] visited;
    private int[] predA;
    private int[] predB;
    private static int jug1Cap;
    private static int jug2Cap;
    private boolean flag = false; 
    private String[][] path;
    private static int trackA = 0;
    private static int trackB = 0;

    private boolean DFS_Visit(int i, int a)
    {

        //checks to see if the values given are between the parameters of visited
        if ((i >= 0 && i <= visited.length) || (a >= 0 && a <= visited[i].length))
        {

           
           // if (i == 0 && a == 0)
                return visited[i][a];
          //  else if (i == 0)
          //      return visited[i][a - 1];
          //  else if (a == 0)
          //      return visited[i - 1][a];
          //  else
          //      return visited[i - 1][a - 1];
        }
        return false;
    }

    public boolean pourWater(int jugA, int jugB, int c)
    {
        int jug1;
        int jug2;
        if ((jugA + jugB) == c)
        {
            visited[jugA][jugB] = true;
            predA[trackA++] = jugA;
            predB[trackB++] = jugB;
            return flag = visited[jugA][jugB];
        }
        else if (DFS_Visit(0,0) == false)
        {
            jug1 = jug1Cap;
            jug2 = jugB;
            visited[0][0] = true;
            return flag = pourWater(jug1, jug2, c);
        }
        else if(DFS_Visit(jugA, jugB) == false )
        {
            jug1 = jugA;
            jug2 = jugB;
            visited[jugA][jugB] = true;

            //if jug1 is empty. pour into jug1
            if (jug1 == 0)
            {
                jug1 = jug1Cap;

                //store previous values into two seperate arrays;
                predA[trackA++] = jugA;
                predB[trackB++] = jugB;

                return flag = pourWater(jug1, jug2, c);
            }

            //if jug2 is full pour out jug2
            else if (jug2 == jug2Cap)
            {
                jug2 = 0;

                predA[trackA++] = jugA;
                predB[trackB++] = jugB;

                return flag = pourWater(jug1, jug2, c);
            }
            
            //check for min between jug1 and the difference between the cap of jug2 minus its content    
            else if  ((jug2Cap - jug2) < jug1)
            {
                int tempHold = jug2Cap - jug2;

                //pour
                jug1 -= tempHold;
                jug2 += tempHold;
                
                predA[trackA++] = jugA;
                predB[trackB++] = jugB;
 
                return flag = pourWater(jug1, jug2, c);
            }
            else
            {
                int tempHold = jug1;
                
                //pour
                jug1 -= tempHold;
                jug2 += tempHold;
                
                predA[trackA++] = jugA;
                predB[trackB++] = jugB;
 
                return flag = pourWater(jug1, jug2, c);
            }

        }
        //backtrack
        else
            return flag = false;
        
      // return flag;  

    }
    
    public static void main(String[] args)
    {
        Jugs j = new Jugs();
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter A: ");
        jug1Cap = kb.nextInt();
        System.out.print("Enter B: ");
        jug2Cap = kb.nextInt();
        System.out.print("Enter C: ");
        int C = kb.nextInt();
        j.visited = new boolean[jug1Cap + 1][jug2Cap + 1];
        j.predA = new int[jug1Cap * 5];
        j.predB = new int[jug2Cap * 5];

        //initialize the pred
        for (int i = 0; i < jug1Cap  * 3; i++)
        {
            j.predA[i] = -1; 
        }
        for (int a = 0; a < jug2Cap * 3; a++)
        {
            j.predB[a] = -1;
        }   
        
        //initialize the path strings
        j.path = new String[jug1Cap + 2][jug2Cap + 2];
        for (int i = 0; i <= jug1Cap; i++)
        {
            for (int a = 0; a <= jug2Cap; a++)
            {
                if (i == jug1Cap)
                {
                    j.path[i][a] = "Fill Jug 1 ";
                }
                else if (a == 0)
                {
                    j.path[i][a] = "Empty Jug 2 ";
                }
                else
                {
                    j.path[i][a] = "Pour Jug 1 -> Jug 2 ";
                }
            }
        }
        //checks to see if their is a path to the target value C
        if (j.pourWater(0, 0, C))
        {
            System.out.println("Yay! Found a solution.");
            j.print();

        }
        else
            System.out.println("Impossible!");
    }

   /**print method to print off predA and predB and use them as index to match path 2d array
    *and print off the right string according to its index
    */
   public void print()
   {
       int i = 0;
        while (i < trackA && i < trackB)
            {  
                System.out.println(path[predA[i]][predB[i]] + 
                    "[a = " + predA[i] + ", b = " + predB[i] + "]");
                i++;
            }
    }
}

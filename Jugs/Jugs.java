import java.util.Scanner;

/**
*   Jugs program to solve the Jugs problem through recursion and depth_first search
**/
public class Jugs
{
    private boolean[][] visited;    //table to store visited states[a][b]
    private int[] predA;            //global variable for pred for state A
    private int[] predB;            //global variable for pred for state B
    private static int jug1Cap;     //global variable capacity limit for jug1(jugA)
    private static int jug2Cap;     //global variable capacity limit for jug2(jugB)
    private boolean flag = false; 
    private String[][] path;        //table that prints out strings according to given states A and B
    private static int trackA = 0;
    private static int trackB = 0;

    /**
    *   DFS_Visit method used to check if table[i][a] is a visited state
    *   @param int i, int a: coordinates for visited table
    *   @return bool
    **/
    private boolean DFS_Visit(int i, int a)
    {

        //checks to see if the values given are between the parameters of visited
        if ((i >= 0 && i <= visited.length) || (a >= 0 && a <= visited[i].length))
        {

           
           // if (i == 0 && a == 0)
                return visited[i][a];
        }
        return false; 
    }

    /**
    *   pourWater recursive method to calculate path for A and B to match C
    *   @param int jugA, JugB: current amount of water in each jug in int
    *   @param int c: target number
    *   @return bool: return true if path is found, false if else
    **/
    public boolean pourWater(int jugA, int jugB, int c)
    {
        int jug1;   //jug1 variable for jugA to preserve JugA
        int jug2;   //jug2 variable for jugB to preserve jugB
        
        //if statement to see if target has been reached else continue
        if ((jugA + jugB) == c)
        {
            visited[jugA][jugB] = true;
            predA[trackA++] = jugA;     //backpointer 
            predB[trackB++] = jugB;     //backpointer
            return flag = visited[jugA][jugB];
        }
        
        //else if to check if this is the first call
        else if (DFS_Visit(0,0) == false)
        {
            jug1 = jug1Cap;
            jug2 = jugB;
            visited[0][0] = true;
            return flag = pourWater(jug1, jug2, c); //recursive call
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

                //store previous values into two seperate arrays(backpointers);
                predA[trackA++] = jugA; 
                predB[trackB++] = jugB;

                return flag = pourWater(jug1, jug2, c); //recursive call
            }

            //if jug2 is full pour out jug2
            else if (jug2 == jug2Cap)
            {
                jug2 = 0;

                predA[trackA++] = jugA; //backpointers
                predB[trackB++] = jugB;

                return flag = pourWater(jug1, jug2, c);
            }
            
            //check for minimum between jug1 and the difference between the cap of jug2 minus its content    
            else if  ((jug2Cap - jug2) < jug1)
            {
                int tempHold = jug2Cap - jug2;

                //pour from jug 1 to jug 2 using the difference from jug2 capacity and current amount
                jug1 -= tempHold;
                jug2 += tempHold;
                
                predA[trackA++] = jugA; //backpointers
                predB[trackB++] = jugB;
 
                return flag = pourWater(jug1, jug2, c); //recursive call
            }
            
            //jug1 had less
            else
            {
                int tempHold = jug1;
                
                //pour from jug1 to jug2
                jug1 -= tempHold;
                jug2 += tempHold;
                
                predA[trackA++] = jugA; //backpointers
                predB[trackB++] = jugB;
 
                return flag = pourWater(jug1, jug2, c); //recursive call
            }

        }
        //backtrack
        else
            return flag = false;

    }
    
    
    /**
    *   main method that reads in users input asking for A, B, and C. calls methods to solve the problem
    *   @param String[] args
    **/
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
                //if jug 1 is filled
                if (i == jug1Cap)
                {
                    j.path[i][a] = "Fill Jug 1 ";
                }
                //if jug 2 is full
                else if (a == 0)
                {
                    j.path[i][a] = "Empty Jug 2 ";
                }
                
                //if their is space for pouring
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

   /**print method to print off strings using predA and predB as the index to match the path 2d array
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

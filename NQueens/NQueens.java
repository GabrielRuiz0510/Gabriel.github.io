import java.util.Scanner;

/**
*   NQueens program solves the NQueens by counting all possible solutions for n
**/
public class NQueens
{
    private static int[][] board;
    private static int placements;
    

    /**
    *   NQueens constructor that initializes board and placements
    **/
    public NQueens(int n)
    {
        board = new int[n][n];
        placements = 0;
    }
    
    /**
    *   function to check if queen is safe to be placed at location
    *   @param int row: row number
    *   @param int col: col number
    *   @return bool
    **/
    private boolean check_safe(int row, int col)
    {
    
            //check current column for other queen
            for (int rowTrack = 0; rowTrack < row; rowTrack++)
            {
                if (board[rowTrack][col] == 1)
                    return false;
                   
            }
                
            //check upper left diagonal for a queen
            for (int rowTrack = row, colTrack = col; rowTrack >= 0 && colTrack >= 0;
                    rowTrack--, colTrack--)
            {
                if (board[rowTrack][colTrack] == 1)
                    return false;

            }

            //check upper right diagonal for a queen
            for (int rowTrack = row, colTrack = col; colTrack < board.length && rowTrack >= 0;
                    rowTrack--, colTrack++)
            {
                if (board[rowTrack][colTrack] == 1)
                    return false;
            }
            return true;
    }
        
    /**
    *   check_row checks each row for valid placements using recursion.
    *   Will backtrack to cut down time
    *   @param int r: row number
    **/
    public void check_row(int r)
    {
        //if int r is equal or exceeds length of board then recursion is done
        //as all possible legal placements have been reached for each recursion path.
        if (r >= board.length)
        {
            placements++;
            return;
        }
        else
        {

            //for loop for as i traverses all rows of the board
            for (int i = 0; i < board.length; i++)
            {

                if (check_safe(r, i) == true)
                {
                    board[r][i] = 1; //able to place queen at [r][i]
        
                    //recursion to place the other queens
                    check_row(r + 1);

                    //we are pruning after failing to place queen
                    board[r][i] = 0;
                }
            }
        }
    }
    
    /**
    *   main method to read in n given by user and call methods needed to caculate all possible placements for n
    *   @param String[] args
    **/
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the number of queens: ");
        int n = kb.nextInt();
        while ( n == 2 || n == 3)
        {
            System.out.println("number must not be 2 or 3 for solution");
            System.out.println("Enter the number of queens: ");
            n = kb.nextInt();
        }
        NQueens newBoard = new NQueens(n);
        newBoard.check_row(0);
        System.out.println("The number of valid arrangements is " + placements);
    }
}



import java.util.*;
import java.util.Scanner;
public class Program {
	//Globally Declared variables;

	static	Integer[][] board = new Integer[9][9];
	static final int blankBlock = 0;
	static	Boolean isTaken = true;
	static	Boolean isCorrect = true;

	
	public static void genBoard(ArrayList<String> inputRows)
	{
		String tmp[] = new String[9];
		for(int i = 0; i < 9; i++)
		{
			//tmp is a temp array that holds each row
			tmp = (inputRows.get(i)).split(" ");
			for(int j = 0; j < 9; j++)
			{
				board[i][j] = Integer.parseInt(tmp[j]);
			}
		}
	}
	
	public static boolean isBoardSafe(int num, int row, int col) 
	{ 
		//checking in row
        for(int i=0;i<9;i++)
        {
            //there is a cell with same value
            if(board[row][i] == num)
                return false;
        }
        //checking column
        for(int i=0;i<9;i++)
        {
            //there is a cell with the value equal to i
            if(board[i][col] == num)
                return false;
        }
        //checking sub matrix
        int row_start = (row/3)*3;
        int col_start = (col/3)*3;
        for(int i=row_start;i<row_start+3;i++)
        {
            for(int j=col_start;j<col_start+3;j++)
            {
                if(board[i][j]==num)
                    return false;
            }
        }
        return true;
	}
	
	private static int[] unassignedNumber(int row, int col)
    {
        int numunassign = 0;
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                //cell is unassigned
                if(board[i][j] == 0)
                {
                    //changing the values of row and col
                    row = i;
                    col = j;
                    //there is one or more unassigned cells
                    numunassign = 1;
                    int[] a = {numunassign, row, col};
                    return a;
                }
            }
        }
        int[] a = {numunassign, -1, -1};
        return a;
    }
	

	
	
	

	

	
	//Sudoku solver
	private static boolean solveSudoku()
    {
        int row=0,col=0;
        
        int[] a = unassignedNumber(row, col);
        //if all cells are assigned then the sudoku is already solved
        //pass by reference because number_unassigned will change the values of row and col
        if(a[0] == 0)
            return true;
        //number between 1 to 9
        row = a[1];
        col = a[2];
        for(int i=1;i<=9;i++)
        {
            //if we can assign i to the cell or not
            //the cell is matrix[row][col]
            if(isBoardSafe(i, row, col))
            {
                board[row][col] = i;
                //backtracking
                if(solveSudoku())
                    return true;
                //if we can't proceed with this solution
                //reassign the cell
                board[row][col]= blankBlock;
            }
        }
        return false;
    }
	
	
	//Helps Print the Soduku board
	public static void printBoard() 
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}
	
	public static void main(String args[])
	{
		//First we take in the input, which in this case is a 9x9 unsolved soduku board
		ArrayList<String> boardRows = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		
		for(int i = 0; i < 9; i++)
		{
			String inputRows = in.nextLine();
			boardRows.add(inputRows);
		}
		
		genBoard(boardRows);
		
		if (solveSudoku())
            printBoard();
        else
            System.out.println("No solution");
		
		
		
		
		
	}
}

//------------------------------------------------------------------//
// Tester2048.java                                                  //
//                                                                  //
// Tester for 2048 PA3                                              //
//                                                                  //
// Author:  Brandon Williams                                        //
// Date:    1/17/15                                                 //
//------------------------------------------------------------------//

import java.util.*;
import java.io.*;

public class PSA4Tester
{
   public static void main(String[] args) throws IOException, InterruptedException
   {
      System.out.println("Begining Testing PSA4");
      testCanMove();
      testIsGameOver();
      testMove();
   }

   private static void testIsGameOver() throws IOException, InterruptedException, SecurityException
   {

      try
      {
         Board studentBoard1,studentBoard2;
         boolean passed1=true,passed2=true;
         boolean result1 = true;
         boolean result2 = false;

         //Creating two student boards 
         studentBoard1 = new Board("gameOver0.in",new Random());

         studentBoard2 = new Board("gameOver1.in",new Random());

         System.out.print("Testing isGameOver() Method..");

         PrintStream out = System.out;
         System.setOut(new PrintStream(new File(".tmp")));

         if(studentBoard1.isGameOver()!=result1)passed1=false;
         if(studentBoard2.isGameOver()!=result2)passed2=false;

         System.setOut(out);

         if(passed1 == true && passed2 == true)
            System.out.println("PASSED!!");
         else
         {
            System.out.println("");
            if(passed1==false)
            {
               System.out.println("Your isGameOver method failed for gameOver0.in file");
               System.out.println("Your method returned : false");
               System.out.println("The correct reutrn value should be: true");
            }
            if(passed2==false)
            {
               System.out.println("Your isGameOver method FAILED for gameOver1.in file");
               System.out.println("Your method returned : true");
               System.out.println("The correct return value should be : false");
            }
         }
      }
      catch(Exception e)
      {
         System.out.println("Your isGameOver method caught an exception, here is the exception detail: " + e);
      }
   }

   //Testing canMove method on canMove1.in and canMove2.in 
   private static void testCanMove() throws IOException, InterruptedException
   {
      try{
         Board studentBoard1,studentBoard2;
         int passed1 = 0;
         int passed2 = 0;
         int failed1 = 0;
         int failed2 = 0;
         int i=0;

         System.out.print("Testing canMove() Method..");

         //Creating 2 new student boards
         studentBoard1 = new Board("canMoveTest1.in", new Random());
         studentBoard2 = new Board("canMoveTest2.in", new Random());   
         //The correct outputs for the two boards
         Boolean[] result1 = {false,true,false,true};
         Boolean[] result2 = {true,true,true,true};
         //Storing the student's results for comparison
         Boolean[] studentResult1 = new Boolean[4];
         Boolean[] studentResult2 = new Boolean[4];
         //Looping for canMove1.in for all 4 directions
         for(Direction dir: Direction.values())
         {
            studentResult1[i] = studentBoard1.canMove(dir);
            // Check to see if the the student board matches the solutions
            if(result1[i] == studentResult1[i])
               passed1++;
            else
               failed1++;
            i++;
         }
         i=0;
         //Looping for canMove2.in for all 4 directions
         for(Direction dir: Direction.values())
         {
            studentResult2[i] = studentBoard2.canMove(dir);
            // Check to see if the the student board matches the solutions
            if(result2[i] == studentResult2[i])
               passed2++;
            else
               failed2++;
            i++;
         }

         //If all cases pass, output PASSED
         if(passed1 == 4 && passed2 == 4)
         {
            System.out.println("PASSED!");
         }
         else 
         {
            System.out.println("");
            if(failed1 > 0)
            {
               System.out.println("Failed for canMove1.in board!");
               System.out.println("Your results for this board are(UP,DOWN,LEFT,RIGHT): "+ Arrays.toString(studentResult1));
               System.out.println("The results for this board should be(UP,DOWN,LEFT,RIGHT): "+Arrays.toString(result1));
            }
            if(failed2 > 0)
            {
               System.out.println("Failed for canMove2.in board!");
               System.out.println("Your results for this board are(UP,DOWN,LEFT,RIGHT): "+ Arrays.toString(studentResult2));
               System.out.println("The results for this board should be(UP,DOWN,LEFT,RIGHT): "+Arrays.toString(result2));
            }

         }
      }
      catch(Exception e)
      {
         System.out.println("Testing canMove() failed , please check your code . Here is the detailed error: " + e);
      }
   }

   private static void testMove() throws IOException
   {
      try
	  {
      Board studentBoard;
	  int size = 7;
	  Boolean passed = true;
      double score = 0.0;
	  int[][] result1={{0,4,0,2048,8,8,4},{0,0,0,8,0,4,4},{0,0,0,0,0,32,16384},{0,0,0,0,0,2048,8},{0,0,0,0,0,32,2},{0,0,0,0,0,0,32},{0,0,0,0,0,0,0}};
	  int[][] result2={{0,0,0,0,0,0,0},{0,0,0,0,0,0,4},{0,0,0,0,0,8,4},{0,0,0,0,0,4,16384},{0,0,0,0,0,32,8},{0,0,0,2048,0,2048,2},{0,4,0,8,8,32,32}};
	  int[][] result3 ={{4,2,0,0,0,0,0},{2,0,0,0,0,0,0},{8,4,0,0,0,0,0},{4,16384,0,0,0,0,0},{32,8,0,0,0,0,0},{2048,4,2048,2,0,0,0},{8,4,64,0,0,0,0}};
	  int[][] result4 ={{0,0,0,0,0,4,2},{0,0,0,0,0,0,2},{0,0,0,0,0,8,4},{0,0,0,0,0,4,16384},{0,0,0,0,0,32,8},{0,0,0,2048,4,2048,2},{0,0,0,0,8,4,64}};
	  
      System.out.print("Testing move() Method..");

      // Directed Tests
      
		String inputBoard = "moveTest.in";
            for(Direction dir: Direction.values())
            {
				
                studentBoard = new Board(inputBoard, new Random());

                // If this is a valid move check to see if
                // the resultant move matches the solutions
				
                    studentBoard.move(dir);
                    int[][] temp=studentBoard.getGrid();
                    for(int i=0;i<size;++i)
					{
                        for(int j=0;j<size;++j)
                        {
							if(dir==Direction.UP)
							{
								if(temp[i][j]!=result1[i][j])
								{
									passed = false;
									System.out.println("Your move method failed for loadBoard.in when moved UP");
									System.out.println("Your output for move method when moved UP is");
									printBoardOnScreen(temp,size);
									System.out.println("The result for move when moved UP should be");
									printBoardOnScreen(result1,size);
								}
							}
							else if(dir==Direction.DOWN)
							{
								if(temp[i][j]!=result2[i][j])
									{
										passed = false;
										System.out.println("Your move method failed for loadBoard.in when moved DOWN");
										System.out.println("Your output for move method when moved DOWN is");
										printBoardOnScreen(temp,size);
										System.out.println("The result for move when moved DOWN should be");
										printBoardOnScreen(result2,size);
									}
								
							}
							else if(dir == Direction.LEFT)
							{
								if(temp[i][j]!=result3[i][j])
									{
										passed = false;
										System.out.println("Your move method failed for loadBoard.in when moved LEFT");
										System.out.println("Your output for move method when moved LEFT is");
										printBoardOnScreen(temp,size);
										System.out.println("The result for move when moved LEFT should be");
										printBoardOnScreen(result3,size);
									}
							}
							else
							{
								if(temp[i][j]!=result4[i][j])
									{
										passed = false;
										System.out.println("Your move method failed for loadBoard.in when moved RIGHT");
										System.out.println("Your output for move method when moved RIGHT is");
										printBoardOnScreen(temp,size);
										System.out.println("The result for move when moved RIGHT should be");
										printBoardOnScreen(result4,size);
									}
							}
							
						}
                    }
			}
			if(passed==true){
				System.out.print("PASSED!");
			}
	  }
		catch(Exception e)
		{
			System.out.print("Your move method failed, here is the detailed error: "+e);
		}
   }    
public static void printBoardOnScreen(int[][] g,int size)
{
	for(int i=0;i<size;++i)
	{
		for(int j=0;j<size;++j)
		{
			System.out.print(g[i][j]+" ");
		}
		System.out.println("");
	}
}   
}

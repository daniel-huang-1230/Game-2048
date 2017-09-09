/*
 *  Name: Daniel Huang 
 *  PID: A12440549
 *  Login:cs8bwacy
 *  Date:2/3/16
 *  File: Board.java
 *  Source of help: PIAZZA
 *  This program would create the board that you would
 *  play the 2048 game on. It contains methods such as adding tiles,moving 
 *  tiles, saving current board, and rotate the board,etc./


/**
 * Sample Board
 * <p/>
 * 0   1   2   3
 * 0   -   -   -   -
 * 1   -   -   -   -
 * 2   -   -   -   -
 * 3   -   -   -   -
 * <p/>
 * The sample board shows the index values for the columns and rows
 * Remember that you access a 2D array by first specifying the row
 * and then the column: grid[row][column]
 */
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;
/* 
 * Name: Board Class
 * Purpose: Creates and  operates the board for the 2048 games
 * Parameter:none
 * Return: void       
 * */


public class Board {
   public final int NUM_START_TILES = 2;
   public final int TWO_PROBABILITY = 90;
   public final int GRID_SIZE;


   private final Random random;
   private int[][] grid;
   private int score;






   /* 
    * Name: Board (the constructor)
    * Purpose:  Constructs a fresh board with random tiles
    * Parameter:int boardSize:set the size of the grid; Random random:a 
    *           random(number) generator
    * Return: void b/c it's a constructor       
    * */ 

   public Board(int boardSize, Random random) {

      this.random =random ; 
      this.GRID_SIZE = boardSize;
      this.grid=new int[boardSize][boardSize];
      this.score=0;
      for(int i=0;i<NUM_START_TILES;i++)
      {this.addRandomTile();}

   }

   /* 
    * Name: Board (the constructor)
    * Purpose:  Construct a board based off of an input file
    * Parameter:String inputBoard:the input file to load a previously 
    * saved game; and Random random:a random(number) generator
    * Return: void b/c it's a constructor       
    * */

   public Board(String inputBoard, Random random) throws IOException {
      this.random = random;
      Scanner boardReader=new Scanner(new File(inputBoard));
      this.GRID_SIZE=boardReader.nextInt();
      this.score=boardReader.nextInt();
      this.grid=new int[GRID_SIZE][GRID_SIZE];
      //read and load the values of each tile in the saved board
      for(int row=0; row<GRID_SIZE; row++){
         for(int col=0;col<GRID_SIZE;col++){
            this.grid[row][col]=boardReader.nextInt();}}

   }
   /* 
    * Name: saveBoard 
    * Purpose:  Saves the current board to a file
    * Parameter:String outputBoard:the output file to save the 
    *  game
    * Return: void        
    * */

   public void saveBoard(String outputBoard) throws IOException {

      java.io.File file=new java.io.File(outputBoard);
      //create a PrintWriter object to write the file
      java.io.PrintWriter output=new java.io.PrintWriter(file);
      output.println(this.GRID_SIZE);
      output.println(this.getScore());

      for(int row=0;row<GRID_SIZE;row++){
         for(int col=0;col<GRID_SIZE;col++){
            output.print(this.grid[row][col]+" ");

         }
         //change to the next row when finishing printing 
         //a row(it also,in effect, append a new line after printing 
         //the last row
         output.println();
      }

      output.close();

   }

   /* 
    * Name: addRandomTile 
    * Purpose:  Adds a random tile (of value 2 or 4) to a
    random empty space on the board
    * Parameter: None 
    * Return: void        
    * */

   public void addRandomTile() {
      //first count the number of available tiles
      int count=0;
      for(int row=0; row<GRID_SIZE;row++){
         for(int col=0;col<GRID_SIZE;col++){
            if(this.grid[row][col]==0)
            {count++;}

         }
      }

      //after checking all the available tiles,if count=0, exit the method
      if(count==0)
      {return;}

      //get a random int called location (0~count-1)
      int location=this.random.nextInt(count);

      //get a random int called value (0~99)
      int value=this.random.nextInt(100);

      //keep track of the number of the empty spaces by numEmp,
      //starting from -1 b/c we number the first empty space as 0
      int numEmp=-1;
      for(int row=0; row<GRID_SIZE;row++){
         for(int col=0;col<GRID_SIZE;col++) {
            if(this.grid[row][col]==0)
            { numEmp++;
            }
            //if the location value equals to the numbering of the empty 
            //space,add a random tile

            if(numEmp==location)
            { //place a 2
               if(value<TWO_PROBABILITY)
               {this.grid[row][col]=2;}
               else
                  //place a 4
               {this.grid[row][col]=4;}
            }
         }

      }


   }

   /* 
    * Name: rotate 
    * Purpose: Rotates the board by 90 degrees clockwise or 90 degrees
    * counter-clockwise.
    If rotateClockwise == true, rotates the board clockwise , else rotates
    the board counter-clockwise
    * Parameter: boolean rotateClockwise:to determine whether the rotation is
    * clockwise or counter-clockwise
    * Return: void        
    * */


   public void rotate(boolean rotateClockwise) {
      int[][] rotGrid=new int[GRID_SIZE][GRID_SIZE];

      //rotate the grid clockwise
      if(rotateClockwise)
      { for(int row=0;row<GRID_SIZE;row++)
         {for(int col=0;col<GRID_SIZE;col++)
            {rotGrid[col][GRID_SIZE-1-row]=this.grid[row][col];}
         }

         this.grid=rotGrid;   
      }

      //rotate the grid counter-clockwise
      else
      { for(int row=0;row<GRID_SIZE;row++)
         {for(int col=0;col<GRID_SIZE;col++)
            {rotGrid[GRID_SIZE-1-col][row]=this.grid[row][col];}
         }

         this.grid=rotGrid;   

      }
   }

   //Complete this method ONLY if you want to attempt at getting the extra 
   //credit
   //Returns true if the file to be read is in the correct format, else return
   //false
   public static boolean isInputFileCorrectFormat(String inputFile) {
      //The try and catch block are used to handle any exceptions
      //Do not worry about the details, just write all your conditions inside
      //the
      //try block
      try {
         //write your code to check for all conditions and return true if it 
         //satisfies
         //all conditions else return false
         return true;
      } catch (Exception e) {
         return false;
      }
   }
   //Name:moveLeft
   //Purpose:helper method for move() method
   //Parameter:none
   //Return:void

   private void moveLeft()
   {  //use count to move the tiles to the left one by one
      for(int row=0;row<GRID_SIZE;row++){
         int count=0;
         for(int col=0;col<GRID_SIZE;col++)

         {
            if(this.grid[row][col]!=0)
            {   this.grid[row][0+count]=this.grid[row][col];
               //Empty the original tile position once tile is moved 
               if(0+count!=col)
               {  this.grid[row][col]=0;}
               count++;}
         }

      }

      for(int row=0;row<GRID_SIZE;row++)
      {for(int col=0;col<GRID_SIZE-1;col++)
         {if(this.grid[row][col]==this.grid[row][col+1])
            //add the two tiles with the same value
            {this.grid[row][col]+=this.grid[row][col+1];
               this.grid[row][col+1]=0;
               //update the score ONCE!
               int sum=this.grid[row][col];
               this.score+=sum;

               break;

            } } }

      //after adding two tiles, repeat the first for loop
      //in this method to move all the tiles to the left side
      for(int row=0;row<GRID_SIZE;row++){
         int count=0;
         for(int col=0;col<GRID_SIZE;col++)

         {
            if(this.grid[row][col]!=0)
            {   this.grid[row][0+count]=this.grid[row][col];
               //Empty the original tile position once tile is moved 
               if(0+count!=col)
               {  this.grid[row][col]=0;}
               count++;}
         }

      }


   }

   //Name:moveRight
   //Purpose:helper method for move() method
   //Parameter:none
   //Return:void

   private void moveRight()
   {  //use count to move the tiles to the right one by one
      for(int row=0;row<GRID_SIZE;row++){
         int count=0;

         for(int col=GRID_SIZE-1;col>=0;col--)

         {
            if(this.grid[row][col]!=0)
            {   this.grid[row][GRID_SIZE-1-count]=this.grid[row][col];
               //empty the original tile position after tile is moved
               if(GRID_SIZE-1-count!=col)
               { this.grid[row][col]=0;}
               count++;}

         }

      }

      for(int row=0;row<GRID_SIZE;row++)
      {for(int col=GRID_SIZE-1;col>0;col--)
         {if(this.grid[row][col]==this.grid[row][col-1])
            //add the two tiles with the same value
            {this.grid[row][col]+=this.grid[row][col-1];
               this.grid[row][col-1]=0;
               //update the score ONCE!
               int sum=this.grid[row][col];
               this.score+=sum;

               break;

            } } }

      //after adding two tiles, repeat the same first for loop
      //in this method to move all the tiles to the right side
      for(int row=0;row<GRID_SIZE;row++){
         int count=0;

         for(int col=GRID_SIZE-1;col>=0;col--)

         {
            if(this.grid[row][col]!=0)
            {   this.grid[row][GRID_SIZE-1-count]=this.grid[row][col];
               if(GRID_SIZE-1-count!=col)
               {this.grid[row][col]=0;}
               count++;}

         }

      }


   }
   //Name:moveUp
   //Purpose:helper method for move() method
   //Parameter:none
   //Return:void


   private void moveUp() 
   {  //use count to move the tiles to the top one by one
      for(int col=0;col<GRID_SIZE;col++){
         int count=0;

         for(int row=0;row<GRID_SIZE;row++)

         {
            if(this.grid[row][col]!=0)
            {   this.grid[0+count][col]=this.grid[row][col];
               //empty the original tile position after tile is moved
               if(0+count!=row)
               { this.grid[row][col]=0;}
               count++;}

         }

      }

      for(int col=0;col<GRID_SIZE;col++)
      {for(int row=0;row<GRID_SIZE-1;row++)
         {if(this.grid[row][col]==this.grid[row+1][col])
            //add the two tiles with the same value
            {this.grid[row][col]+=this.grid[row+1][col];
               this.grid[row+1][col]=0;
               //update the score ONCE!
               int sum=this.grid[row][col];
               this.score+=sum;

               break;

            } } }

      //after adding two tiles, repeat the same first for loop
      //in this method to move all the tiles to the top
      for(int col=0;col<GRID_SIZE;col++){
         int count=0;

         for(int row=0;row<GRID_SIZE;row++)

         {
            if(this.grid[row][col]!=0)
            {   this.grid[0+count][col]=this.grid[row][col];
               //empty the original tile position after tile is moved
               if(0+count!=row)
               { this.grid[row][col]=0;}
               count++;}

         }

      }

   }

   //Name:moveDown
   //Purpose:helper method for move() method
   //Parameter:none
   //Return:void

   private void moveDown()   {  //use count to move the tiles to the bottom
      //one by one
      for(int col=0;col<GRID_SIZE;col++){
         int count=0;

         for(int row=GRID_SIZE-1;row>=0;row--)

         {
            if(this.grid[row][col]!=0)
            {   this.grid[GRID_SIZE-1-count][col]=this.grid[row][col];
               //empty the original tile position after tile is moved
               if(GRID_SIZE-1-count!=row)
               { this.grid[row][col]=0;}
               count++;}

         }

      }

      for(int col=0;col<GRID_SIZE;col++)
      {for(int row=GRID_SIZE-1;row>0;row--)
         {if(this.grid[row][col]==this.grid[row-1][col])
            //add the two tiles with the same value
            {this.grid[row][col]+=this.grid[row-1][col];
               this.grid[row-1][col]=0;
               //update the score ONCE!
               int sum=this.grid[row][col];
               this.score+=sum;

               break;

            } } }

      //after adding two tiles, repeat the same first for loop
      //in this method to move all the tiles to the bottom
      for(int col=0;col<GRID_SIZE;col++){
         int count=0;

         for(int row=GRID_SIZE-1;row>=0;row--)

         {
            if(this.grid[row][col]!=0)
            {   this.grid[GRID_SIZE-1-count][col]=this.grid[row][col];
               //empty the original tile position after tile is moved
               if(GRID_SIZE-1-count!=row)
               { this.grid[row][col]=0;}
               count++;}

         }

      }

   }





   //Name:move
   //Purpose:performs a move operation
   //Parameter:Direction direction:the input direction by the user
   //Return:boolean:return true if the move is successful
   //               otherwise,return false

   public boolean move(Direction direction) {
      //check the input direction enums and perform the move operation
      if(direction.equals(Direction.LEFT))
      {this.moveLeft();
         return true;}
      if(direction.equals(Direction.RIGHT))
      {this.moveRight();
         return true;}
      if(direction.equals(Direction.UP))
      {this.moveUp();
         return true;}
      if(direction.equals(Direction.DOWN))
      {this.moveDown();
         return true;}
      //return false if the move is not successful
      return false;
   }




   //Name:isGameOver
   //Purpose: check to see if we have a game over
   //Parameter:none
   //Return:boolean:true if there is no valid move left,and end the game


   public boolean isGameOver() {
      //check if there is no long any valid move left, then the game
      //is over.
      if(!this.canMoveLeft()&&!this.canMoveRight()&&!this.canMoveUp()
            &&!this.canMoveDown())
      {return true;}

      //return false when the game continues
      return false;
   }



   //Name:canMoveLeft
   //Purpose:helper method for the canMove() method
   //Parameter:none
   //Return:boolean:return true if the the board can move left

   private boolean canMoveLeft() {

      for(int row=0;row<GRID_SIZE;row++)
         //check if there is zero neighboring a nonzero in the grid,if so,
         //the tiles can
         //move to the left; or if they are two tiles with the same value, it
         //can also move to the left
      {for(int col=0;col<GRID_SIZE-1;col++)
         {
            if(this.grid[row][col]==0&&this.grid[row][col+1]!=0)
            {return true;}
            if(this.grid[row][col]!=0&&this.grid[row][col]
                  ==this.grid[row][col+1])
            {return true;}
         }

      }
      return false;

   }
   //Name:canMoveRight
   //Purpose:helper method for the canMove() method
   //Parameter:none
   //Return:boolean:return true if the the board can move right

   private boolean canMoveRight(){
      for(int row=0;row<GRID_SIZE;row++)
         //check if there is zero neighboring a nonzero in the grid,if so,
         //the tiles can
         //move to the right; or if they are two tiles with the same value, it
         //can also move to the right
      {for(int col=GRID_SIZE-1;col>0;col--)
         {
            if(this.grid[row][col]==0&&this.grid[row][col-1]!=0)
            {return true;}
            if(this.grid[row][col]!=0&&this.grid[row][col]
                  ==this.grid[row][col-1])
            {return true;}

         }

      }
      return false; 

   }
   //Name:canMoveUp
   //Purpose:helper method for the canMove() method
   //Parameter:none
   //Return:boolean:return true if the the board can move up


   private boolean canMoveUp(){
      for(int col=0;col<GRID_SIZE;col++)
         //check if there is zero neighboring a nonzero in the grid,if so,
         //the tiles can
         //move up; or if they are two tiles with the same value, it
         //can also move up

      {for(int row=0;row<GRID_SIZE-1;row++)
         {
            if(this.grid[row][col]==0&&this.grid[row+1][col]!=0)
            {return true;}
            if(this.grid[row][col]!=0&&this.grid[row][col]==
                  this.grid[row+1][col])
            {return true;}
         }

      }
      return false; }

   //Name:canMoveDown
   //Purpose:helper method for the canMove() method
   //Parameter:none
   //Return:boolean:return true if the the board can move down


   private boolean canMoveDown(){
      for(int col=0;col<GRID_SIZE;col++)
         //check if there is zero neighboring a nonzero in the grid,if so,
         //the tiles can
         //move down; or if they are two tiles with the same value, it
         //can also move down

      {for(int row=GRID_SIZE-1;row>0;row--)
         {
            if(this.grid[row][col]==0&&this.grid[row-1][col]!=0)
            {return true;}
            if(this.grid[row][col]!=0&&this.grid[row][col]
                  ==this.grid[row-1][col])
            {return true;}
         }

      }
      return false; }




   //Name:canMove
   //Purpose: determine if we can move in a given direction
   //Parameter:Direction direction:the input direction by the user
   //Return:boolean:return true if the the board can move
   //in the input direction


   public boolean canMove(Direction direction) {
      //check if the board can move in the passed in direction
      if(direction.equals(Direction.LEFT)&&this.canMoveLeft())
      {return true;}
      if(direction.equals(Direction.RIGHT)&&this.canMoveRight())
      {return true;}
      if(direction.equals(Direction.UP)&&this.canMoveUp())
      {return true;}
      if(direction.equals(Direction.DOWN)&&this.canMoveDown())
      {return true;}

      //otherwise,return false(the board cannot move)
      return false;

   }


   // Return the reference to the 2048 Grid
   public int[][] getGrid() {
      return grid;
   }

   // Return the score
   public int getScore() {
      return score;
   }

   @Override
      public String toString() {
         StringBuilder outputString = new StringBuilder();
         outputString.append(String.format("Score: %d\n", score));
         for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++)
               outputString.append(grid[row][column] == 0 ? "    -" :
                     String.format("%5d", grid[row][column]));

            outputString.append("\n");
         }
         return outputString.toString();
      }
}

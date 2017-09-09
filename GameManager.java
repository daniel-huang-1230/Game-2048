/* 
 * Name: Daniel Huang
 * PID:A12440549
 * Login: cs8bwacy 
 * Date: 2/3/2016
 * File: GameManager.java 
 * Sources of Help:PIAZZA
 * This program is the game manager of 2048.
 *   
 * */


//------------------------------------------------------------------//
// GameManager.java                                                 //
//                                                                  //
// Game Manager for 2048                                            //
//                                                                  //
// Author:  W16-CSE8B-TA group                                      //
// Date:    1/17/16                                                 //
//------------------------------------------------------------------//

import java.util.*;
import java.io.*;

/* 
 * Name:GameManager Class
 * Purpose: It manages the start,save,loading,and closing of 
 * the game.

 * Parameters: None
 * Return: void
 */


public class GameManager {
   // Instance variables
   private Board board; // The actual 2048 board
   private String outputFileName; // File to save the board to when exiting




   /* 
    * Name: GameManager(the constructor)
    * Purpose: GameManager Constructor
    It generates a new game

    * Parameters: int boardSize:specify the size of the grid
    *             String outputBoard:specify where to save the game
    *             Random random: a random generator
    * Return: void
    *
    */

   public GameManager(int boardSize, String outputBoard, Random random) {
      System.out.println("Generating a New Board");
      if(boardSize>=2){
         this.board=new Board(boardSize,random);}
      //account for the case when the boardsize value is less than 2
      else
      {System.out.println("Use the default board size:4.");
         this.board=new Board(4,random);}

      this.outputFileName=outputBoard;
   }

   /* 
    * Name: GameManager(the constructor)
    * Purpose: GameManager Constructor
    It loads a saved game

    * Parameters: String inputBoard:specify where to load the previously saved
    *             game from
    *             String outputBoard:specify where to save the game
    *             Random random: a random generator
    * Return: void
    *
    */

   public GameManager(String inputBoard, String outputBoard, Random random) 
      throws IOException {
         System.out.println("Loading Board from " + inputBoard);
         this.board=new Board(inputBoard,random);
         this.outputFileName=outputBoard;



      }

   /* 
    * Name: play
    * Purpose: 
   // Main play loop
   // Takes in input from the user to specify moves to execute
   // valid moves are:
   //      k - Move up
   //      j - Move Down
   //      h - Move Left
   //      l - Move Right
   //      q - Quit and Save Board
   //
   //  If an invalid command is received then print the controls
   //  to remind the user of the valid moves.
   //
   //  Once the player decides to quit or the game is over,
   //  save the game board to a file based on the outputFileName
   //  string that was set in the constructor and then return
   //
   //  If the game is over print "Game Over!" to the terminal
    * Parameters: none
    * Return: void
    *
    */


   public void play() throws IOException {

      //print the controls

      this.printControls();
      //print out the current state of the 2048 board to the console 
      System.out.println(board.toString());

      while(board.isGameOver()==false) {


         Scanner userInput=new Scanner(System.in);

         System.out.println("Enter the control key to move the tiles");
         String userIn=userInput.next();

         //check the user input's validility 
         while ((!userIn.equals("k"))&&(!userIn.equals("j"))&&
               (!userIn.equals("h"))&&(!userIn.equals("l"))
               &&(!userIn.equals("q")))
         {  this.printControls();

            System.out.println("Invalid control input,please re-enter");

            userIn=userInput.next();
         } 


         Direction dir=null;
         if(userIn.equals("k"))
         {dir=Direction.UP;}
         if(userIn.equals("j"))
         {dir=Direction.DOWN;}
         if(userIn.equals("h"))
         {dir=Direction.LEFT;}
         if(userIn.equals("l"))
         {dir=Direction.RIGHT;} 

         if(userIn.equals("q"))
         {  //save the board to the outputBoard and quit the game
            this.board.saveBoard(outputFileName);
            //exit the method
            return; 
         }



         //check if the move is valid
         while(this.board.canMove(dir)==false) 
         {  //if the board cannot move,print the controls and 
            //the current board again and prompt the user to re-enter
            System.out.println("Cannot move in this direction");
            this.printControls();
            System.out.println(board.toString());



            userIn=userInput.next();
            while ((!userIn.equals("k"))&&(!userIn.equals("j"))&&
                  (!userIn.equals("h"))&&(!userIn.equals("l"))&&
                  (!userIn.equals("q")))
               //if the input is not valid, print out the countrols again
               //and prompt the user to enter another command
            {  this.printControls();

               System.out.println("Invalid control input,please re-enter");

               userIn=userInput.next();
            } 



            if(userIn.equals("k"))
            {dir=Direction.UP;}
            if(userIn.equals("j"))
            {dir=Direction.DOWN;}
            if(userIn.equals("h"))
            {dir=Direction.LEFT;}
            if(userIn.equals("l"))
            {dir=Direction.RIGHT;} 

            if(userIn.equals("q"))
            {  //save the board to the outputBoard and quit the game
               this.board.saveBoard(outputFileName);
               //exit the method
               return; 
            }


         }
         //finally,if the input is valid,move the tile and add a random 
         //new tile
         this.board.move(dir);
         this.board.addRandomTile();

         //print the updated board
         System.out.println(board.toString()); }


      //outside the while loop==>means the game is over
      System.out.println("Game Over!");
      //save the game and quit
      this.board.saveBoard(outputFileName);
      //exit the method
      return;   

   }


   /* 
    * Name: printControls
    * Purpose: Print the Controls keys for the Game

    * Parameters:none 
    * Return: void
    *
    */


   private void printControls() {
      System.out.println("  Controls:");
      System.out.println("    k - Move Up");
      System.out.println("    j - Move Down");
      System.out.println("    h - Move Left");
      System.out.println("    l - Move Right");
      System.out.println("    q - Quit and Save Board");
      System.out.println();
   }
}

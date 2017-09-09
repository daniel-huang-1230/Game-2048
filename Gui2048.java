/*
 *  Name: Daniel Huang 
 *  PID: A12440549
 *  Login:cs8bwacy
 *  Date:3/3/16
 *  File: Gui2048.java
 *  Source of help: PIAZZA
 *  This program would perform the same as the famous game 2048.
 *  

/** Gui2048.java */
/** PSA8 Release */

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
 * Name: Gui2048 Class
 * Purpose: Starts and perform all the operations in the 2048 game
 * Parameter:none
 * Return: void       
 * */

public class Gui2048 extends Application
{
   private String outputBoard; // The filename for where to save the Board
   private Board board; // The 2048 Game Board

   private static final int TILE_WIDTH = 106;

   private static final int TEXT_SIZE_LOW = 55; // Low value tiles (2,4,8,etc)
   private static final int TEXT_SIZE_MID = 45; // Mid value tiles 
   //(128, 256, 512)
   private static final int TEXT_SIZE_HIGH = 35; // High value tiles 
   //(1024, 2048, Higher)

   // Fill colors for each of the Tile values
   private static final Color COLOR_EMPTY = Color.rgb(204, 192, 179);
   private static final Color COLOR_2 = Color.rgb(238, 228, 218);
   private static final Color COLOR_4 = Color.rgb(237, 224, 200);
   private static final Color COLOR_8 = Color.rgb(242, 177, 121);
   private static final Color COLOR_16 = Color.rgb(245, 149, 99);
   private static final Color COLOR_32 = Color.rgb(246, 124, 95);
   private static final Color COLOR_64 = Color.rgb(246, 94, 59);
   private static final Color COLOR_128 = Color.rgb(237, 207, 114);
   private static final Color COLOR_256 = Color.rgb(237, 204, 97);
   private static final Color COLOR_512 = Color.rgb(237, 200, 80);
   private static final Color COLOR_1024 = Color.rgb(237, 197, 63);
   private static final Color COLOR_2048 = Color.rgb(237, 194, 46);
   private static final Color COLOR_OTHER = Color.BLACK;
   private static final Color COLOR_GAME_OVER = Color.rgb(238, 228, 218, 0.73);

   private static final Color COLOR_VALUE_LIGHT = Color.rgb(249, 246, 242); 
   // For tiles >= 8

   private static final Color COLOR_VALUE_DARK = Color.rgb(119, 110, 101); 
   // For tiles < 8

   /** Add your own Instance Variables here */
   //stackpane as instance variable to achieve the gameover check function

   private GridPane pane;
   private Scene scene;
   private StackPane stack;

   //2-D array for tiles
   private Rectangle[][] tiles;

   //2-D array for text
   private Text[][] texts;



   /* 
    * Name: createTiles
    * Purpose:helper method to intialize the tiles array
    * Parameter:int xTiles, int yTiles
    * Return: Rectangle[][]     
    * */

   public Rectangle[][] createTiles(int xTiles, int yTiles) {
      return new Rectangle[xTiles][yTiles];

   }

   /* 
    * Name: createTexts
    * Purpose:helper method to intialize the texts array
    * Parameter:int xTexts, int yTexts
    * Return: Text[][]     
    * */


   public Text[][] createTexts(int xTexts, int yTexts) {
      return new Text[xTexts][yTexts];
   }


   /* 
    * Name: start
    * Purpose:the vital method to start the game and set up the GUI
    * Parameter:Stage primaryStage
    * Return: void     
    * */


   @Override
      public void start(Stage primaryStage)
      {  
         // Process Arguments and Initialize the Game Board
         processArgs(getParameters().getRaw().toArray(new String[0]));

         // Create the pane that will hold all of the visual objects
         pane = new GridPane();
         pane.setAlignment(Pos.CENTER);

         pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

         pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
         // Set the spacing between the Tiles
         pane.setHgap(15); 
         pane.setVgap(15);

         /** Add your Code for the GUI Here */



         this.tiles=createTiles(board.GRID_SIZE,board.GRID_SIZE+1);
         this.texts=createTexts(board.GRID_SIZE,board.GRID_SIZE+1);
         //add the header "2048" to the pane
         Text header = new Text();
         header.setText("2048");
         header.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                  FontPosture.ITALIC, 50));

         header.setFill(Color.BLACK);


         //add the score 
         Text score=new Text();
         score.setText("Score: "+board.getScore());
         texts[board.GRID_SIZE-1][0]=score;
         texts[board.GRID_SIZE-1][0].setFont(Font.font("Time New Roman",
                  FontWeight.BOLD, 20));
         texts[board.GRID_SIZE-1][0].setFill(Color.BLACK);



         //add the 2048 header and score to the gridpane 

         pane.add(header,0,0);
         GridPane.setHalignment(header, HPos.LEFT);
         pane.add(texts[board.GRID_SIZE-1][0],board.GRID_SIZE-1,0);





         //add the tiles(rectangles) to the gridpane
         for(int x=0; x<board.GRID_SIZE; x++ )
         { 
            for(int y=1;y<board.GRID_SIZE+1;y++) {
               Rectangle aTile = new Rectangle();

               aTile.setWidth(100);
               aTile.setHeight(100);

               //add the newly created rectangle to the 2-D array to
               //store its references for future use
               tiles[x][y]=aTile;

               //set the color of the tiles 
               // if(board.getGrid()[y-1][x]==0){
               tiles[x][y].setFill(COLOR_EMPTY); 

               if(board.getGrid()[y-1][x]==2){
                  tiles[x][y].setFill(COLOR_2); }
               if(board.getGrid()[y-1][x]==4){
                  tiles[x][y].setFill(COLOR_4); }
               if(board.getGrid()[y-1][x]==8){
                  tiles[x][y].setFill(COLOR_8); }
               if(board.getGrid()[y-1][x]==16){
                  tiles[x][y].setFill(COLOR_16); }
               if(board.getGrid()[y-1][x]==32){
                  tiles[x][y].setFill(COLOR_32); }
               if(board.getGrid()[y-1][x]==64){
                  tiles[x][y].setFill(COLOR_64); }
               if(board.getGrid()[y-1][x]==128){
                  tiles[x][y].setFill(COLOR_128); }
               if(board.getGrid()[y-1][x]==256){
                  tiles[x][y].setFill(COLOR_256); }
               if(board.getGrid()[y-1][x]==512){
                  tiles[x][y].setFill(COLOR_512); }
               if(board.getGrid()[y-1][x]==1024){
                  tiles[x][y].setFill(COLOR_1024); }
               if(board.getGrid()[y-1][x]==2048){
                  tiles[x][y].setFill(COLOR_2048); }
               if (board.getGrid()[y-1][x]>2048){
                  //for the tile values other than the above numbers,set it 
                  //to black
                  tiles[x][y].setFill(COLOR_OTHER);}  



               //add the tile to the gridpane
               pane.add(tiles[x][y],x,y);






               //bind the grid with the texts array

               //note the row/coloumn order in grid is different from
               //the one in texts
               Text tileValue=new Text();
               tileValue.setText(Integer.toString((board.getGrid())[y-1][x]));

               //store the tile value references in the 2d texts array
               texts[x][y]=tileValue;


               //set the font size based on the value of tile
               if(board.getGrid()[y-1][x]<128) {
                  texts[x][y].setFont((Font.font("Time New Roman", 
                              FontWeight.BOLD, TEXT_SIZE_LOW ))); }
               if(board.getGrid()[y-1][x]<1024){
                  texts[x][y].setFont((Font.font("Time New Roman", 
                              FontWeight.BOLD,TEXT_SIZE_MID ))); }

               else {
                  texts[x][y].setFont((Font.font("Time New Roman", 
                              FontWeight.BOLD, TEXT_SIZE_HIGH )));}



               //set the color of tile text based on its value  
               if(board.getGrid()[y-1][x]<8){
                  texts[x][y].setFill(COLOR_VALUE_DARK);}
               else {
                  texts[x][y].setFill(COLOR_VALUE_LIGHT);}


               //show the text(tile value) only for the non-empty tiles
               if(board.getGrid()[y-1][x]!=0) {
                  pane.add(texts[x][y],x,y);}

               GridPane.setHalignment(texts[x][y], HPos.CENTER);



            } }




            this.stack=new StackPane();
            stack.getChildren().addAll(pane);


            this.scene = new Scene(stack);
            //register the keyevent(listener)
            scene.setOnKeyPressed(new myKeyHandler());


            primaryStage.setTitle("Gui2048");
            primaryStage.setScene(scene);
            primaryStage.show();


         }

         /** Add your own Instance Methods Here */

         /* 
          * Name: updateGUI
          * Purpose: the helper method to update the GUI once a move is
          *          performed
          * Parameter: none
          * Return: void     
          * */

         private void updateGUI() {
            //update the score 

            texts[board.GRID_SIZE-1][0].setText("Score: "+board.getScore());
            texts[board.GRID_SIZE-1][0].setFont(Font.font("Time New Roman", 
                     FontWeight.BOLD, 20));
            texts[board.GRID_SIZE-1][0].setFill(Color.BLACK);





            for(int x=0;x<board.GRID_SIZE;x++){

               for(int y=1;y<board.GRID_SIZE+1;y++) {

                  Rectangle aTile = new Rectangle();

                  aTile.setWidth(100);
                  aTile.setHeight(100);

                  //add the newly created rectangle to the 2-D array to
                  //store its references for future use
                  tiles[x][y]=aTile;

                  //set the color of the tiles 
                  // if(board.getGrid()[y-1][x]==0){
                  tiles[x][y].setFill(COLOR_EMPTY); 

                  if(board.getGrid()[y-1][x]==2){
                     tiles[x][y].setFill(COLOR_2); }
                  if(board.getGrid()[y-1][x]==4){
                     tiles[x][y].setFill(COLOR_4); }
                  if(board.getGrid()[y-1][x]==8){
                     tiles[x][y].setFill(COLOR_8); }
                  if(board.getGrid()[y-1][x]==16){
                     tiles[x][y].setFill(COLOR_16); }
                  if(board.getGrid()[y-1][x]==32){
                     tiles[x][y].setFill(COLOR_32); }
                  if(board.getGrid()[y-1][x]==64){
                     tiles[x][y].setFill(COLOR_64); }
                  if(board.getGrid()[y-1][x]==128){
                     tiles[x][y].setFill(COLOR_128); }
                  if(board.getGrid()[y-1][x]==256){
                     tiles[x][y].setFill(COLOR_256); }
                  if(board.getGrid()[y-1][x]==512){
                     tiles[x][y].setFill(COLOR_512); }
                  if(board.getGrid()[y-1][x]==1024){
                     tiles[x][y].setFill(COLOR_1024); }
                  if(board.getGrid()[y-1][x]==2048){
                     tiles[x][y].setFill(COLOR_2048); }
                  if (board.getGrid()[y-1][x]>2048){
                     //for the tile values other than the above numbers,set it 
                     //to black
                     tiles[x][y].setFill(COLOR_OTHER);}  

                  pane.add(tiles[x][y],x,y);


                  Text tileValue=new Text();
                  tileValue.setText
                     (Integer.toString((board.getGrid())[y-1][x]));

                  //store the tile value references in the 2d texts array
                  texts[x][y]=tileValue;


                  //set the font size based on the value of tile
                  if(board.getGrid()[y-1][x]<128) {
                     texts[x][y].setFont((Font.font("Time New Roman", 
                                 FontWeight.BOLD,TEXT_SIZE_LOW ))); }

                  if(board.getGrid()[y-1][x]<1024){
                     texts[x][y].setFont((Font.font("Time New Roman",
                                 FontWeight.BOLD, TEXT_SIZE_MID ))); }

                  else {
                     texts[x][y].setFont((Font.font("Time New Roman", 
                                 FontWeight.BOLD,TEXT_SIZE_HIGH )));}



                  //set the color of tile text based on its value  
                  if(board.getGrid()[y-1][x]<8){
                     texts[x][y].setFill(COLOR_VALUE_DARK);}
                  else {
                     texts[x][y].setFill(COLOR_VALUE_LIGHT);}


                  //show the text(tile value) only for the non-empty tiles
                  if(board.getGrid()[y-1][x]!=0) {
                     pane.add(texts[x][y],x,y);}

                  GridPane.setHalignment(texts[x][y], HPos.CENTER);


               }
               }
            }



            /* 
             * Name: gameOverCheck
             * Purpose: the helper method to check if the game is over after 
             *          a valid move; if so, it will stop the game and print 
             *          the "Game Over!" message
             * Parameter: none
             * Return: void     
             * */


            private void gameOverCheck(){
               if(board.isGameOver())
               {



                  Rectangle endCover=new Rectangle(scene.getWidth(),
                        scene.getHeight());
                  endCover.setFill(COLOR_GAME_OVER);

                  stack.getChildren().add(endCover);


                  Text endLine=new Text();
                  endLine.setText("Game Over!");
                  endLine.setFont((Font.font("Time New Roman",
                              FontWeight.BOLD,60 )));

                  endLine.setFill(COLOR_OTHER);
                  stack.getChildren().add(endLine);
               }


            }


            /* 
             * Name: myKeyHandker class
             * Purpose: this inner class implements the eventhandler interface
             *          and link the key input to the game

             * Parameter: none
             * Return: void     
             * */



            private class myKeyHandler implements EventHandler<KeyEvent>
            {   



               /* 
                * Name: handle
                * Purpose: it detects the key input and perform the
                *          corresponding move
                * Parameter: KeyEvent input
                * Return: void     
                * */

               @Override
                  public void handle(KeyEvent input)
                  { 
                     //detect the input key only if the game is NOT over
                     if(!board.isGameOver()) {

                        switch(input.getCode()) {
                           case UP:   if(board.canMove(Direction.UP)) {
                                         System.out.println("Moving <UP>");
                                         board.move(Direction.UP);
                                         board.addRandomTile();

                                         //update the GUI
                                         updateGUI();     

                                         gameOverCheck(); 


                                      }





                                      break;
                           case DOWN: if(board.canMove(Direction.DOWN)) {
                                         System.out.println("Moving <DOWN>");
                                         board.move(Direction.DOWN);
                                         board.addRandomTile();
                                         //update the GUI
                                         updateGUI();     

                                         gameOverCheck(); 




                                      }
                                      break;
                           case LEFT:  if(board.canMove(Direction.LEFT)) {
                                          System.out.println("Moving <LEFT>");
                                          board.move(Direction.LEFT);
                                          board.addRandomTile();

                                          //update the GUI
                                          updateGUI();     

                                          gameOverCheck(); 




                                       }
                                       break;
                           case RIGHT: if(board.canMove(Direction.RIGHT)) {
                                          System.out.println("Moving <RIGHT>");
                                          board.move(Direction.RIGHT);
                                          board.addRandomTile();

                                          //update the GUI
                                          updateGUI();     

                                          gameOverCheck(); 




                                       }
                                       break;
                           case S: 
                                       //the following block takes care of
                                       //the IOException
                                       try {
                                          board.saveBoard(outputBoard);
                                       } catch (IOException e) { 
                                          System.out.println
                                             ("saveBoard threw an Exception");
                                       }

                                       //save the board to the output file
                                       System.out.println
                                          ("Saving board to <2048.board> "); 
                                       break;
                           default: break;




                        }




                     }

                  }

            }






            /** DO NOT EDIT BELOW */

            // The method used to process the command line arguments
            private void processArgs(String[] args)
            {
               String inputBoard = null;   // The filename for where to load
                                           //   the Board
               int boardSize = 0;          // The Size of the Board

               // Arguments must come in pairs
               if((args.length % 2) != 0)
               {
                  printUsage();
                  System.exit(-1);
               }

               // Process all the arguments 
               for(int i = 0; i < args.length; i += 2)
               {
                  if(args[i].equals("-i"))
                  {   // We are processing the argument that specifies
                     // the input file to be used to set the board
                     inputBoard = args[i + 1];
                  }
                  else if(args[i].equals("-o"))
                  {   // We are processing the argument that specifies
                     // the output file to be used to save the board
                     outputBoard = args[i + 1];
                  }
                  else if(args[i].equals("-s"))
                  {   // We are processing the argument that specifies
                     // the size of the Board
                     boardSize = Integer.parseInt(args[i + 1]);
                  }
                  else
                  {   // Incorrect Argument 
                     printUsage();
                     System.exit(-1);
                  }
               }

               // Set the default output file if none specified
               if(outputBoard == null)
                  outputBoard = "2048.board";
               // Set the default Board size if none specified or less than 2
               if(boardSize < 2)
                  boardSize = 4;

               // Initialize the Game Board
               try{
                  if(inputBoard != null)
                     board = new Board(inputBoard, new Random());
                  else
                     board = new Board(boardSize, new Random());
               }
               catch (Exception e)
               {
                  System.out.println(e.getClass().getName() + 
                        " was thrown while creating a " +
                        "Board from file " + inputBoard);
                  System.out.println("Either your Board(String, Random) " +
                        "Constructor is broken or the file isn't " +
                        "formated correctly");
                  System.exit(-1);
               }
            }

            // Print the Usage Message 
            private static void printUsage()
            {
               System.out.println("Gui2048");
               System.out.println("Usage:  Gui2048 [-i|o file ...]");
               System.out.println();
               System.out.println
               ("  Command line arguments come in pairs of the "+ 
                     "form: <command> <argument>");
               System.out.println();
               System.out.println
               ("  -i [file]  -> Specifies a 2048 board that " + 
                     "should be loaded");
               System.out.println();
               System.out.println
               ("  -o [file]  -> Specifies a file that should be " + 
                     "used to save the 2048 board");
               System.out.println
               ("                If none specified then the " + 
                     "default \"2048.board\" file will be used");  
               System.out.println
               ("  -s [size]  -> Specifies the size of the 2048" + 
                     "board if an input file hasn't been"); 
               System.out.println
               ("                specified.  If both -s and -i" + 
                     "are used, then the size of the board"); 
               System.out.println
               ("                will be determined by the input" +
                     " file. The default size is 4.");
            }
         }

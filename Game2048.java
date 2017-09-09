//------------------------------------------------------------------//
// Game2048.java                                                    //
//                                                                  //
// Game Driver for 2048                                             //
//                                                                  //
// Author:  W16-CSE8B-TA group                                      //
// Date:    1/17/16                                                 //
//------------------------------------------------------------------//

import java.util.Random;
import java.io.IOException;

/**
 * DO NOT MODIFY THIS FILE
 */

/** Except for changing the Random Seed */

public class Game2048 {
    private static String inputBoard;
    private static String outputBoard;
    private static int boardSize;

    public static void main(String[] args) throws IOException {
        processArgs(args);

        System.out.println("Welcome to 2048!");

        // You can set a specific Seed by passing an integer value into
        // the Random(int) constructor
        Random random = new Random();
        // Random random = new Random(2015);

        GameManager game;

        if (inputBoard == null) {
            System.out.println("Generating a New Board");
            game = new GameManager(boardSize, outputBoard, random);
        } else {
            System.out.println("Loading Board from " + inputBoard);
            game = new GameManager(inputBoard, outputBoard, random);
        }

        game.play();
    }

    private static void processArgs(String[] args) {
        // Arguments must come in pairs
        if ((args.length % 2) != 0) {
            printUsage();
            System.exit(-1);
        }

        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-i")) { // We are processing the argument that specifies
                // the input file to be used to set the board
                inputBoard = args[i + 1];
            } else if (args[i].equals("-o")) { // We are processing the argument that specifies
                // the output file to be used to save the board
                outputBoard = args[i + 1];
            } else if (args[i].equals("-s")) { // We are processing the argument that specifies
                // the size of the Board
                boardSize = Integer.parseInt(args[i + 1]);
            } else { // Incorrect Argument
                printUsage();
                System.exit(-1);
            }
        }

        // Set the default output file if none specified
        if (outputBoard == null)
            outputBoard = "2048.board";
        // Set the default Board size if none specified
        if (boardSize < 2)
            boardSize = 4;
    }

    // Print the Usage Message
    private static void printUsage() {
        System.out.println("2048");
        System.out.println("Usage:  Game2048 [-i|o file ...]");
        System.out.println();
        System.out.println("  Command line arguments come in pairs of the form: <command> <argument>");
        System.out.println();
        System.out.println("  -i [file]  -> Specifies a 2048 board that should be loaded");
        System.out.println();
        System.out.println("  -o [file]  -> Specifies a file that should be used to save the 2048 board");
        System.out.println("                If none specified then the default \"2048.board\" file will be used");
        System.out.println("  -s [size]  -> Specifies the size of the 2048 board if an input file hasn't been");
        System.out.println("                specified.  If both -s and -i are used, then the size of the board");
        System.out.println("                will be determined by the input file. The default size is 4.");

    }
}

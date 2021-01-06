package se.hig.aod.labyrintProjekt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Huvudklasen för programmet. Denna klass skapar labyrinter mha
 * {@link DepthFirstSearch} samt {@link AldousBorderAlgorithm} algoritmer. Sedan
 * får användaren välja vilken taktik hen vill lösa labyrinten med. De lösnings
 * algoritmer som är implementerade är en så kallad Mouse Algorithm samt en ren
 * BruteForce algoritm som tittar efter den kortaste vägen.
 * @author Andreas Roghe
 * @author Sofia Ågren
 * @version 2021-01-06
 */

public final class MazeRunner {

    /**
     * Deklaration utav {@link AldousBorderAlgorithm}.
     */
    private static Maze aldousBorder;
    /**
     * Deklaration utav {@link DepthFirstSearch}.
     */
    private static Maze df;
    /**
     * Scannern för användar input.
     */
    private static Scanner keyBoard;
    /**
     * Den storlek som väljs som default om inte användaren säger annat.
     */
    private static final int DEFAULT_SIZE = 10;

    private MazeRunner() {
    };

    /**
     * Main metoden. Tar inga argument för tillfället.
     * @param args
     */
    public static void main(final String[] args) {
        keyBoard = new Scanner(System.in);
        keyBoard.useDelimiter(System.lineSeparator());

        String fileName = null;

        if (args.length <= 0) {
            fileName = chooseMaze();
        } else {
            System.out.println("Arguments is not yet implemented!");
        }

        chooseSolver(converter(fileName));
        keyBoard.close();
    }

    private static String chooseMaze() {
        System.out.println("Choose maze generator algorithm: ");
        System.out.println("1. DepthFirst");
        System.out.println("2. Aldous-Border");
        int choice = keyBoard.nextInt();
        System.out.println("Choose size of maze:");
        int size;
        if (keyBoard.hasNextInt()) {
            size = keyBoard.nextInt();
        } else {
            System.out.println("No size selected, generating with default size 10x10.");
            size = DEFAULT_SIZE;
        }

        System.out.println("Name your maze:");
        String name = keyBoard.next();

        switch (choice) {
        case 1:
            df = new DepthFirstSearch(size);
            printToFile(df.getMaze(), name);
            break;

        case 2:
            aldousBorder = new AldousBorderAlgorithm(size);
            printToFile(aldousBorder.getMaze(), name);
            break;
        default:
            System.out
                    .println("Nothing selected. Generating maze with depth-first recursion.");
            df = new DepthFirstSearch(size);
            printToFile(df.getMaze(), name);
            break;
        }

        return name;
    }

    private static void chooseSolver(final String[][] maze) {
        System.out.println("Choose solver algorithm:");
        System.out.println("1. BruteForce algorithm.");
        System.out.println("2. Mouse algorithm.");

        String choice = keyBoard.next();
        switch (choice) {
        case "1":
            Solver ms = new MazeSolver();
            ms.solveMaze(maze);
            break;
        case "2":
            Solver mouse = new MouseAlgorithm();
            mouse.solveMaze(maze);
            break;
        default:
            System.out.println("Nothing selected. Solving with BruteForce");
            ms = new MazeSolver();
            ms.solveMaze(maze);
            break;
        }
    }

    private static String[][] converter(final String fileName) {
        String[][] maze;
        File file = new File("Labyrint/" + fileName);
        Scanner inSize = null;
        BufferedReader inChar = null;

        try {
            inChar = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            inSize = new Scanner(file);

            System.out.println("Complete");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int counter = 0;

        while (inSize.hasNextLine()) {
            inSize.nextLine();
            counter++;
        }

        maze = new String[counter][counter];

        int i = 0;

        while (i < maze.length) {
            for (int j = 0; j < maze.length; j++) {
                try {
                    char a = (char) inChar.read();
                    if (a == '\r') {
                        a = (char) inChar.read();
                        if (a == '\n') {
                            a = (char) inChar.read();
                        }
                    }
                    char b = (char) inChar.read();
                    maze[i][j] = a + "" + b;

                } catch (IOException e) {

                    e.printStackTrace();
                }

            }
            i++;
        }
        return maze;
    }

    private static void printToFile(final String[][] maze, final String name) {
        try {

            PrintWriter labyWriter = new PrintWriter("Labyrint/" + name);

            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze.length; j++) {
                    labyWriter.print(maze[i][j]);

                }
                labyWriter.println();
            }
            labyWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

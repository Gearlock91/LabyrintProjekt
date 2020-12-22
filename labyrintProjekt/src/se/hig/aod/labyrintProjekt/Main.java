package se.hig.aod.labyrintProjekt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	
    static Maze am;
    static Maze df;
    
    public static void main(String[] args) {
      
        String fileName = null;
        
        if(args.length <= 0) {
            fileName = chooseMaze();
        }
        
        Solver ms = new MazeSolver();
        ms.solveMaze(converter(fileName));
        
        
//        if (args.length > 0) {
//            switch (args[0]) {
//            case "-w":
//                int size = 0;
//                if (args.length > 1) {
//                    fileName = args[1];
//                    if(args.length > 2) {
//                        size =  Integer.valueOf(args[2]);
//                    }else {
//                        System.out.println("Choose size of labyrint");
//                        size = keyBoard.nextInt();
//                    }
//                } else {
//                    System.out.println("Choose file name to write:");
//                    fileName = keyBoard.nextLine();
//                    System.out.println("Choose size of labyrint");
//                    size = keyBoard.nextInt();
//                }
//                am = new AldousBorderAlgorithm(size);
//                printToFile(am.getMaze(), fileName);
//                break;
//            case "-r":
//                if (args[1].length() > 0) {
//                    fileName = args[1];
//                } else {
//                    System.out.println("Choose file to read:");
//                    fileName = keyBoard.nextLine();
//                }
//
//                break;
//            }
//            keyBoard.close();
//        } else {
//            System.out.println("Choose size of labyrint:");
//            am = new AldousBorderAlgorithm(keyBoard.nextInt());
//            fileName = "MazeGenerated.txt";
//            printToFile(am.getMaze(), fileName);
//        }

        // converter(labyrint);
        

        // ms.solveMaze(am.getMaze());

    }

    private static String chooseMaze() {
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("Choose algorithm: ");
        System.out.println("1. DepthFirst");
        System.out.println("2. Aldous-Border");
        int choice = keyBoard.nextInt();
        System.out.println("Choose size of maze:");
        int size  = keyBoard.nextInt();
        System.out.println("Name your maze:");
        String name = keyBoard.nextLine();
        switch(choice) {
        case 1:
            df = new DepthFirstSearch(size);
            printToFile(df.getMaze(), name);
            break;
            
        case 2:
            am = new AldousBorderAlgorithm(size);
            printToFile(am.getMaze(), name);
            break;
        }
        keyBoard.close();
		return name;
    }
    
    private static void printToFile(String[][] maze, String name) {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static String[][] converter(String fileName) {
        String[][] maze;
        File file = new File("Labyrint/" + fileName);
        Scanner inSize = null;
        BufferedReader inChar = null;
        try {
            inChar = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            inSize = new Scanner(file);

            System.out.println("Complete");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
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
}

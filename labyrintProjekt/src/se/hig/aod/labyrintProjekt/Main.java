package se.hig.aod.labyrintProjekt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
    	String labyrint ="Labyrint/testLabyrint";
    	//converter(labyrint);
        MazeSolver ms = new MazeSolver();
        ms.solveMaze(converter(labyrint));
        AldousBorderMazeGenerator am = new AldousBorderMazeGenerator(10);
        ms.solveMaze(am.getMaze());
        
    }
    private static String[][] converter(String labyrint){
    	String[][] maze;
    	File file = new File(labyrint);
    	Scanner inSize = null;
    	BufferedReader inChar = null;
		try {
			inChar = new BufferedReader(new FileReader( file));
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
    	while(inSize.hasNextLine()) {
    		inSize.nextLine();
    		counter++;
	
    	}
    	maze = new String[counter][counter];
    	System.out.println("Maze size: " + counter*counter);
    	int i = 0;
    
		while (i < maze.length) {
    		for(int j = 0; j < maze.length; j++) {
    			try {
    				char a = (char)inChar.read();
    				if(a == '\r') {
    					a = (char)inChar.read();
    					if(a == '\n') {
    						a = (char)inChar.read();
    					}
    				}
    				char b = (char)inChar.read();
    				maze[i][j] = a + "" + b;
    				//System.out.print(a + "" + b);
    			} catch (IOException e) {
			
    				e.printStackTrace();
    			}
    			//a.concat(inChar.next());
    			//maze[i][j] = a;
    		}
    		i++;	
    		//System.out.println();
    	}   	 
		return maze; 	
    }
}

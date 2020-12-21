package se.hig.aod.labyrintProjekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
    	String labyrint ="Labyrint/testLabyrint.txt";
    	File file = new File(labyrint);
    	try {
			Scanner in = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        AldousBorderMazeGenerator generator = new AldousBorderMazeGenerator(20);
        
        generator.printMaze();
        
    }

}

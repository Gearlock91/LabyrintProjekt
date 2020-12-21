package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;
import java.util.Random;

public class MazeGenerator {

    private int height = 29;
    private int width = 29;
    private final ArrayList<String> DIRECTIONS = new ArrayList<>(
            Arrays.asList("NORTH", "SOUTH", "WEST", "EAST"));
   // private Map<String, Integer> steps = new HashMap<>();
    
    private List<Integer> steps = new ArrayList<Integer>();
    
   // private String keyLetter = "";
   // private int keyIntX = 0;
   // private int keyIntY = 0;
    private String[][] maze;

    public MazeGenerator(/* int width, int height */) {
        /*
         * if (width > 2 && height > 2) { this.height = height; this.width = width;
         * }else { //throw new InvalidSizeException }
         */

        maze = new String[width][height];
        createMaze();
    }

    private void createMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                maze[i][j] = " X";
            }
        }

        int startX = findRandomPoint();
        int startY = findRandomPoint();
        
      
        steps.add(startX);
        steps.add(startY);
        
        dig(startX, startY);

        maze[startX][startY] = " S";
        maze[steps.get(steps.size() - 2)][steps.get(steps.size() - 1)] = " E";
    }

    private void dig(int x, int y) {
        Collections.shuffle(DIRECTIONS);
        for (int i = 0; i < DIRECTIONS.size(); i++) {

            switch (DIRECTIONS.get(i)) {
            case "WEST":
                // Whether 2 cells west is out or not
                if (x - 2 <= 0)
                    continue;
                // Wheter 2 cells west is another room  or not
                if (maze[x - 2][y] != "  ") {
                    maze[x - 2][y] = "  ";
                    maze[x - 1][y] = "  ";
                    //keyIntX++;
                    //steps.put("x" + keyIntX, x - 2);
                    steps.add(x - 2);
                    steps.add(y);
                    steps.add(x - 1);
                    steps.add(y);       
                    dig(x - 2, y);
                }
                break;
            case "SOUTH":
                if (y + 2 >= width - 1)
                    continue;
                if (maze[x][y + 2] != "  ") {
                    maze[x][y + 2] = "  ";
                    maze[x][y + 1] = "  ";
//                    keyIntY++;
//                    steps.put("y" + keyIntY, y + 2);
                    steps.add(x);
                    steps.add(y + 2);
                    steps.add(x);
                    steps.add(y + 1);  
                    dig(x, y + 2);
                }
                break;
            case "EAST": 
                if (x + 2 >= height - 1)
                    continue;
                if (maze[x + 2][y] != "  ") {
                    maze[x + 2][y] = "  ";
                    maze[x + 1][y] = "  ";
//                    keyIntX++;
//                    steps.put("x" + keyIntX, x + 2);
                    steps.add(x + 2);
                    steps.add(y);
                    steps.add(x + 1);
                    steps.add(y);  
                    dig(x + 2, y);
                }
                break;
            case "NORTH": 
                if (y - 2 <= 0)
                    continue;
                if (maze[x][y - 2] != "  ") {
                    maze[x][y - 2] = "  ";
                    maze[x][y - 1] = "  ";
//                    keyIntY++;
//                    steps.put("y" + keyIntY, y - 2);
                    steps.add(x);
                    steps.add(y - 2);
                    steps.add(x);
                    steps.add(y - 1);  
                    dig(x, y - 2);
                }
                break;
            }
        }

    }

    private int findRandomPoint() {
        boolean oddNumber = false;
        int hold = 0;
        while (!oddNumber) {
            Random random = new Random();
            hold = random.nextInt(width);
            if (hold % 2 != 0) {
                oddNumber = true;
            }
        }
        return hold;
    }

    public void printTestMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[j][i]);
            }
            System.out.println();
        }

    }
    
    
    public String[][] getMaze(){
        return maze;
    }
    
//    public List<Integer> getCords() {
//        return steps;
//    }
}

package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractGenerator {
    
    protected final String[] DIRECTIONS = {"NORTH", "SOUTH","WEST", "EAST"};
    protected List<Cell> unvisitedCells = new ArrayList<Cell>();
    protected Cell[][] maze;
    protected int width;
    protected int height;
    
    
    protected class Cell {
        protected boolean visited = true;
        protected String format = " X";
        protected int x;
        protected int y;

        public Cell(int x, int y, int wallDefiner) {
            if (wallDefiner != 0) {
                visited = false;
                this.x = x;
                this.y = y;
            }
        }
    }
    
    public AbstractGenerator(int size) {
        if(size % 2 != 0) {
            //throw new IllegalArgument
            System.err.println("IllegalArgument!");
        }else {
            this.width = size - 1;
            this.height = size - 1;
            createMaze(width,height);
        }
    }
    
    private void createMaze(int width, int height) {
        maze = new Cell[width][height];

        createBoard();
        createPerimeter();
        algorithm(maze);
    }
    
    

    private void createPerimeter() {
        for (int i = 0; i < maze.length; i++) {
            maze[0][i] = new Cell(0, i, 0);
        }
        for (int i = 0; i < maze.length; i++) {
            maze[maze.length - 1][i] = new Cell(maze.length - 1, i, 0);
            ;
        }
        for (int i = 0; i < maze.length; i++) {
            maze[i][0] = new Cell(i, 0, 0);
            ;
        }
        for (int i = 0; i < maze.length; i++) {
            maze[i][maze.length - 1] = new Cell(i, maze.length - 1, 0);
        }
    }
    abstract void createBoard();
    abstract String[][] getMaze();
    abstract void algorithm(Cell[][] maze);

}

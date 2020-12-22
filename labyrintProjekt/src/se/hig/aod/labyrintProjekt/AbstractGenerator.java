package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se.hig.aod.labyrintProjekt.AbstractGenerator.Cell;

public abstract class AbstractGenerator implements Maze {

    protected final String[] DIRECTIONS = {"NORTH", "SOUTH", "WEST", "EAST"};
    protected List<Cell> unvisitedCells = new ArrayList<Cell>();
    protected Cell[][] maze;
    protected int width;
    protected int height;

    protected class Cell {
        protected List<Cell> adjecent;
        protected Cell previous;
        protected boolean visited = true;
        protected String format = " X";
        protected int x;
        protected int y;

        public Cell(int x, int y, int wallDefiner) {
            if (wallDefiner != 0) {
                visited = false;
                this.x = x;
                this.y = y;
                adjecent = new ArrayList<Cell>();
            }
        }
    }

    public AbstractGenerator(int size) {
        if (size % 2 != 0) {
            // throw new IllegalArgument
            System.err.println("IllegalArgument!");
        } else {
            this.width = size - 1;
            this.height = size - 1;
            createMaze(width, height);
        }
    }

    public void createMaze(int width, int height) {
        maze = new Cell[width][height];
        createBoard();
        createPerimeter();
        algorithm(maze);
    }

    private void createBoard() {
        for (int i = 1; i < maze.length; i++) {
            for (int j = 1; j < maze.length; j++) {
                int uneven = 0;

                if (j % 2 != 0 && i % 2 != 0) {
                    uneven = 1;
                }

                maze[i][j] = new Cell(i, j, uneven);

                if (!(maze[i][j].visited)) {
                    unvisitedCells.add(maze[i][j]);
                }
            }
        }
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

    public String[][] getMaze() {
        String[][] convertMaze = new String[width][height];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                convertMaze[i][j] = maze[i][j].format;
            }
        }

        return convertMaze;
    }


    abstract void algorithm(Cell[][] maze);

}

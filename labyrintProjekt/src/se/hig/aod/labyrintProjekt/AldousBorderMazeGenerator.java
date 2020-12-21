package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AldousBorderMazeGenerator {

    private List<Cell> unvisitedCells = new ArrayList<Cell>();
    private final String[] DIRECTION = {"NORTH", "SOUTH", "WEST", "EAST"};
    private Cell[][] maze;
    private int width;
    private int height;

    private class Cell {
        private boolean visited = true;
        private String format = " X";
        private int x;
        private int y;

        public Cell(int x, int y, int wallDefiner) {
            if (wallDefiner != 0) {
                visited = false;
                this.x = x;
                this.y = y;
            }
        }
    }

    public AldousBorderMazeGenerator(int size) {
        if (size % 2 != 0) {
            // throw new IllegalArgument
            System.err.println("IllegalArgument!");
        } else {
            this.width = size - 1;
            this.height = size - 1;
            createMaze(width, height);
        }
    }

    private void createMaze(int width, int height) {
        maze = new Cell[width][height];

        createBoard();
        createPerimeter();
        aldousBorderAlgorithm(maze);
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

    private void aldousBorderAlgorithm(Cell[][] maze) {

        Collections.shuffle(unvisitedCells);
        Cell current = unvisitedCells.remove(0);
        current.visited = true;
        current.format = " S";

        while (unvisitedCells.size() > 0) {
            Random random = new Random();
            int randomDirection = random.nextInt(4);
            Cell potentialWall;
            switch (DIRECTION[randomDirection]) {
            case "NORTH":
                if (current.y - 2 > 0) {
                    String endPoint = "  ";
                    if (unvisitedCells.size() == 1) {
                        endPoint = " E";
                    }
                    int x = current.x;
                    int y = current.y - 2;
                    current = maze[x][y];
                    y = y + 1;
                    potentialWall = maze[x][y];
                    if (current.visited == false) {
                        current.format = endPoint;
                        potentialWall.format = "  ";
                        current.visited = true;
                        unvisitedCells.remove(current);

                    }
                }
                break;
            case "SOUTH":
                if (current.y + 2 < height) {
                    String endPoint = "  ";
                    if (unvisitedCells.size() == 1) {
                        endPoint = " E";
                    }
                    int x = current.x;
                    int y = current.y + 2;
                    current = maze[x][y];
                    y = current.y - 1;
                    potentialWall = maze[x][y];
                    if (current.visited == false) {
                        current.format = endPoint;
                        potentialWall.format = "  ";
                        current.visited = true;
                        unvisitedCells.remove(current);
                    }
                }
                break;
            case "WEST":
                if (current.x - 2 > 0) {
                    String endPoint = "  ";
                    if (unvisitedCells.size() == 1) {
                        endPoint = " E";
                    }
                    int x = current.x - 2;
                    int y = current.y;
                    current = maze[x][y];
                    x = x + 1;
                    potentialWall = maze[x][y];
                    if (current.visited == false) {
                        current.format = endPoint;
                        potentialWall.format = "  ";
                        current.visited = true;
                        unvisitedCells.remove(current);
                    }
                }
                break;
            case "EAST":
                if (current.x + 2 < width) {
                    String endPoint = "  ";
                    if (unvisitedCells.size() == 1) {
                        endPoint = " E";
                    }
                    int x = current.x + 2;
                    int y = current.y;
                    current = maze[x][y];
                    x = x - 1;
                    potentialWall = maze[x][y];
                    if (current.visited == false) {
                        current.format = endPoint;
                        potentialWall.format = "  ";
                        current.visited = true;
                        unvisitedCells.remove(current);

                    }
                }
                break;
            }
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

    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j].format);
            }
            System.out.println();
        }
    }

}

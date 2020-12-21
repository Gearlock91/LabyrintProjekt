package se.hig.aod.labyrintProjekt;

import java.util.Collections;
import java.util.Random;

import se.hig.aod.labyrintProjekt.AbstractGenerator.Cell;


public class AldousBorderAlgorithm extends AbstractGenerator {

    public AldousBorderAlgorithm(int size) {
        super(size);
    }

    @Override
    void algorithm(Cell[][] maze) {
        Collections.shuffle(unvisitedCells);
        Cell current = unvisitedCells.remove(0);
        current.visited = true;
        current.format = " S";

        while (unvisitedCells.size() > 0) {
            Random random = new Random();
            int randomDirection = random.nextInt(4);
            Cell potentialWall;
            switch (DIRECTIONS[randomDirection]) {
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

    @Override
    String[][] getMaze() {
        String[][] convertMaze = new String[width][height];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                convertMaze[i][j] = maze[i][j].format;
            }
        }

        return convertMaze;
    }

    @Override
    void createBoard() {
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

}

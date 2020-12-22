package se.hig.aod.labyrintProjekt;

import java.util.Collections;
import java.util.Random;

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
}

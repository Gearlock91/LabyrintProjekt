package se.hig.aod.labyrintProjekt;

import java.util.Collections;
import java.util.Random;

/**
 * Implementationen utav AldousBorder algoritmen.
 * @author Andreas Roghe
 * @author Sofia Ågren
 * @version 2020-01-06
 */

public class AldousBorderAlgorithm extends AbstractGenerator {

    /**
     * Antal möjliga riktningar.
     */
    private static final int POSSIBLE_ROADS = 4;

    /**
     * Konstruktorn för att bygga upp grunden utav labyrinten. Samtliga celler fylls
     * upp med X och varannan cell markeras som en besöksbar cell.
     * @param size
     */
    public AldousBorderAlgorithm(final int size) {
        super(size);
    }

    @Override
    void algorithm(final Cell[][] maze) {
        Collections.shuffle(unvisitedCells);
        Cell current = unvisitedCells.remove(0);
        current.visited = true;
        current.format = " S";

        while (unvisitedCells.size() > 0) {
            Random random = new Random();
            int randomDirection = random.nextInt(POSSIBLE_ROADS);
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
                    if (!current.visited) {
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
                    if (!current.visited) {
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
                    if (!current.visited) {
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
                    if (!current.visited) {
                        current.format = endPoint;
                        potentialWall.format = "  ";
                        current.visited = true;
                        unvisitedCells.remove(current);

                    }
                }
                break;
            default:
                break;
            }
        }

    }
}

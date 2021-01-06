package se.hig.aod.labyrintProjekt;

import java.util.Collections;
import java.util.Random;

/**
 * Implementationen utav DepthFirstSearch algoritmen mha vår abstrakta generator
 * av labyrinter.
 * @author Andreas Roghe, Sofia Ågren
 * @version 2020-01-06
 */

public class DepthFirstSearch extends AbstractGenerator {

    /**
     * Antal möjliga riktningar.
     */
    private static final int POSSIBLE_ROADS = 4;

    /**
     * Konstruktorn som tar en storlek av labyrinten och skickar vidare till vår
     * abstrakta generator som sätter upp labyrinten.
     * @param size
     */
    public DepthFirstSearch(final int size) {
        super(size);
    }

    @Override
    void algorithm(final Cell[][] maze) {
        Collections.shuffle(unvisitedCells);
        Cell current = unvisitedCells.get(0);
        Cell randomEnd = unvisitedCells.get(1);
        depthAlgorithm(current);
        current.format = " S";
        randomEnd.format = " E";
    }

    private void depthAlgorithm(final Cell node) {
        node.visited = true;
        Random random = new Random();
        int left;
        int right;
        int up;
        int down;
        left = node.x - 2;
        right = node.x + 2;
        up = node.y - 2;
        down = node.y + 2;
        for (int i = 0; i < DIRECTIONS.length; i++) {
            switch (DIRECTIONS[random.nextInt(POSSIBLE_ROADS)]) {
            case "NORTH":
                if ((up > 0)) {
                    Cell upwardsCell = maze[node.x][up];
                    if (!(upwardsCell.format.equals("  "))) {
                        upwardsCell.format = "  ";
                        maze[node.x][node.y - 1].format = "  ";
                        depthAlgorithm(upwardsCell);
                    }
                }
                break;
            case "SOUTH":
                if ((down < height)) {
                    Cell southCell = maze[node.x][down];
                    if (!(southCell.format.equals("  "))) {
                        southCell.format = "  ";
                        maze[node.x][node.y + 1].format = "  ";
                        depthAlgorithm(southCell);
                    }
                }
                break;
            case "WEST":
                if ((left > 0)) {
                    Cell westCell = maze[left][node.y];
                    if (!(westCell.format.equals("  "))) {
                        westCell.format = "  ";
                        maze[node.x - 1][node.y].format = "  ";
                        depthAlgorithm(westCell);
                    }
                }
                break;
            case "EAST":

                if ((right < width)) {
                    Cell eastCell = maze[right][node.y];
                    if (!(eastCell.format.equals("  "))) {
                        eastCell.format = "  ";
                        maze[node.x + 1][node.y].format = "  ";
                        depthAlgorithm(eastCell);
                    }
                }
                break;
            default:
                break;
            }
        }

    }

}

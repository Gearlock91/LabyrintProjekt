package se.hig.aod.labyrintProjekt;

import java.util.Collections;
import java.util.Random;


public class DepthFirstSearch extends AbstractGenerator {

    public DepthFirstSearch(int size) {
        super(size);
        // TODO Auto-generated constructor stub
    }

    @Override
    void algorithm(Cell[][] maze) {
        Collections.shuffle(unvisitedCells);
        Cell current = unvisitedCells.get(0);
        Cell randomEnd = unvisitedCells.get(1);
        depthAlgorithm(current);
        current.format = " S";
        randomEnd.format = " E";
    }

    private void depthAlgorithm(Cell node) {
        node.visited = true;
        Random random = new Random();
        int left, right, up, down;
        left = node.x - 2;
        right = node.x + 2;
        up = node.y - 2;
        down = node.y + 2;
        for (int i = 0; i < DIRECTIONS.length; i++) {
            switch (DIRECTIONS[random.nextInt(4)]) {
            case "NORTH":
                if ((up > 0)) {
                    Cell upwardsCell = maze[node.x][up];
                    if (!(upwardsCell.format.equals("  "))) {
                        upwardsCell.format = "  ";
                        maze[node.x][node.y - 1].format = "  ";
                        depthAlgorithm(upwardsCell);
                    }
                    // node.adjecent.add(maze[node.x][up]);
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
                    // node.adjecent.add(maze[node.x][down]);
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
                    // node.adjecent.add(maze[left][node.y]);
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
                    // node.adjecent.add(maze[right][node.y]);
                }
                break;
            }
        }
        
    }

}

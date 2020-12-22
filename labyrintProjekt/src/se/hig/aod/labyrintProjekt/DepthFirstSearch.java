package se.hig.aod.labyrintProjekt;

import java.util.Collections;

public class DepthFirstSearch extends AbstractGenerator {

    public DepthFirstSearch(int size) {
        super(size);
        // TODO Auto-generated constructor stub
    }

    @Override
    void algorithm(Cell[][] maze) {
        Collections.shuffle(unvisitedCells);
        Cell current = unvisitedCells.get(0);
        current.format = " S";
        current.previous = current;
        depthAlgorithm(current);
    }

    private void depthAlgorithm(Cell node) {
        node.visited = true;
        int left, right, up, down;
        left = node.x - 2;
        right = node.x + 2;
        up = node.y - 2;
        down = node.y + 2;
        if ((left > 0) && !(maze[left][node.x].format == "  ")) {
            node.adjecent.add(maze[left][node.y]);
            maze[left][node.y].format = "  ";
            maze[node.x - 1][node.y].format = "  ";
        }
        if ((right < width) && maze[right][node.x].visited == false
                && right != node.previous.x) {
            node.adjecent.add(maze[right][node.y]);
            maze[right][node.y].format = "  ";
            maze[node.x + 1][node.y].format = "  ";
        }
        if ((up > 0) && maze[node.x][up].visited == false && up != node.previous.y) {
            node.adjecent.add(maze[node.x][up]);
            maze[node.x][up].format = "  ";
            maze[node.x][node.y - 1].format = "  ";
           
        }
        if ((down < height) && maze[node.x][down].visited == false
                && down != node.previous.y) {
            node.adjecent.add(maze[node.x][down]);
            maze[node.x][down].format = "  ";
            maze[node.x][node.y + 1].format = "  ";
        }

        for (Cell cell : node.adjecent) {
            cell.previous = node;
            depthAlgorithm(cell);
        }
  
    }

}

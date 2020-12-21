package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.List;

public class MazeSolver {

    private List<MazeNode> path = new ArrayList<MazeNode>();
    private final int INFINITY = Integer.MAX_VALUE;
    private MazeNode endNode;

    /**
     * Klassen som håller reda på den aktuella informationen om
     * en specifik väg som är en väg i labyrinten.
     * @author Andreas Roghe
     * @version 2020-12-19
     *
     */
    private class MazeNode {

        private List<MazeNode> adj;
        private MazeNode prev;
        private int x;
        private int y;
        private int distance = 0;

        public MazeNode(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            adj = new ArrayList<MazeNode>();
        }
    }

    public void solveMaze(String[][] maze) {
        MazeNode startNode = readMaze(maze);
        bruteForce(maze, startNode);
        printMaze(maze);
    }

    private MazeNode readMaze(String[][] maze) {
        // find Start

        MazeNode start = null;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == " S") {
                    start = new MazeNode(i, j, 0);
                }
            }
        }
        start.prev = start;
        return start;
    }

    private void bruteForce(String[][] maze, MazeNode start) {
        start.distance = 0;
        checkAdj(maze, start);

        int min = INFINITY;
        for (MazeNode endPoint : path) {
            if (endPoint.distance < min) {
                min = endPoint.distance;
                endNode = endPoint;
            }
        }
        System.out.println("Steps from start to endpoint: " + min);
    }

    private void checkAdj(String[][] maze, MazeNode node) {
        // Avståndet från startpunkten till den aktuella noden.
        node.distance += node.prev.distance;
        // Om vi hittar slutpunkten lägg till den i path och fortsätt
        // sedan att kontrollera övriga noder.
        if (maze[node.x][node.y] == " E") {
            path.add(node);
            return;
        }
        // Vi tittar om det finns en väg åt vänster,höger,uppåt samt nedåt.
        int left, right, up, down;
        left = node.x - 1;
        right = node.x + 1;
        up = node.y - 1;
        down = node.y + 1;
        if (((maze[left][node.y] == "  " || (maze[left][node.y] == " E")))
                && (left != node.prev.x)) {
            node.adj.add(new MazeNode(left, node.y, 1));
        }
        if (((maze[right][node.y] == "  " || (maze[right][node.y] == " E")))
                && (right != node.prev.x)) {
            node.adj.add(new MazeNode(right, node.y, 1));
        }
        if (((maze[node.x][up] == "  " || (maze[node.x][up] == " E")))
                && (up != node.prev.y)) {
            node.adj.add(new MazeNode(node.x, up, 1));
        }
        if (((maze[node.x][down] == "  " || (maze[node.x][down] == " E")))
                && (down != node.prev.y)) {
            node.adj.add(new MazeNode(node.x, down, 1));
        }

        for (MazeNode n : node.adj) {
            n.prev = node;
            checkAdj(maze, n);
        }

    }

    private void printPath(String[][] maze, MazeNode node) {
        if (maze[node.x][node.y] == " S") {
            return;
        } else {
            if (!(maze[node.x][node.y] == " E")) {
                maze[node.x][node.y] = " *";
            }
            printPath(maze, node.prev);
        }
    }

    public void printMaze(String[][] maze) {

        printPath(maze, endNode);

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[j][i]);
            }
            System.out.println();
        }
    }

}

package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.List;

public class MazeSolver {

    private List<MazeNode> path = new ArrayList<MazeNode>();
    private List<MazeNode> unvisitedNodes = new ArrayList<MazeNode>();
    private final int INFINITY = Integer.MAX_VALUE;
    private MazeNode endNode;

    private class MazeNode {

        private List<MazeNode> adj;
        private MazeNode prev;
        private int x;
        private int y;
        private boolean visited;
        private int distance = 0;

        public MazeNode(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            adj = new ArrayList<MazeNode>();
        }
    }

    public void solveMaze(String[][] maze) {
        readMaze(maze);
    }

    private void readMaze(String[][] maze) {
        // find Start

        MazeNode start = null;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == " S") {
                    start = new MazeNode(i, j, 0);
                }
                if (maze[i][j] == "  ") {
                    unvisitedNodes.add(new MazeNode(i, j, INFINITY));
                }
            }
        }
        start.prev = start;
        dijkstras(maze, start);

        printMaze(maze);
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

    private void dijkstras(String[][] maze, MazeNode start) {

        start.visited = true;
        start.distance = 0;
        checkAdj(maze, start);
        
        int min = INFINITY;
        for(MazeNode endPoint : path) {
            if (endPoint.distance < min) {
                min = endPoint.distance;
                endNode = endPoint;    
            }
            System.out.println(min);
        }
    }

    public void checkAdj(String[][] maze, MazeNode node) {

        node.distance += node.prev.distance;

        if (maze[node.x][node.y] == " E") {
            path.add(node);
            return;
        }
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
}

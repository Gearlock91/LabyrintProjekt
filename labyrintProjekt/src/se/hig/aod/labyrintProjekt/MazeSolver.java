package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MazeSolver {
    
    private List<MazeNode> path = new ArrayList<MazeNode>();
    
   
    
    private class MazeNode {
        
        private List<MazeNode> adj;
        private MazeNode prev;
        private int x;
        private int y;
        private boolean visited = false;
        
        public MazeNode(int x, int y) {
            this.x = x;
            this.y = y;
            adj = new ArrayList<MazeNode>();
        }
    }
    
    public void solveMaze(String[][] maze) {
        readMaze(maze);
    }
    
    private void readMaze(String[][] maze) {
        //find Start
        MazeNode start = null;
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze.length; j++) {
                if(maze[i][j] == " S") {
                    start = new MazeNode(i,j);
                }
            }
        }
        start.prev = start;
        checkAdj(maze, start, 0);
        printMaze(maze);
    }
    
    public void printMaze(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[j][i]);
            }
            System.out.println();
        }
    }
    
    public void checkAdj(String[][] maze,MazeNode node, int WalkCounter) {
        int walkCounter = WalkCounter;
        if(maze[node.x][node.y] == " E") {
            System.out.println("FOUND!");
            System.err.println(walkCounter);
            
            for(MazeNode n1 : path) {
                maze[n1.x][n1.y] = " *";
            }
            return;
        }
      
        path.add(node);
        int left, right, up ,down;
        left = node.x - 1;
        right = node.x + 1;
        up = node.y - 1;
        down = node.y + 1;
        if(((maze[left][node.y] == "  "|| (maze[left][node.y] == " E"))) && (left != node.prev.x)) {
            node.adj.add(new MazeNode(left, node.y));
        }
        if(((maze[right][node.y] == "  " || (maze[right][node.y] == " E"))) && (right != node.prev.x)) {
            node.adj.add(new MazeNode(right, node.y));
        }
        if(((maze[node.x][up] == "  " || (maze[node.x][up] == " E"))) && (up != node.prev.y)) {
            node.adj.add(new MazeNode(node.x, up));
        }
        if(((maze[node.x][down] == "  " || (maze[node.x][down] == " E"))) && (down != node.prev.y)) {
            node.adj.add(new MazeNode(node.x, down));
        }
        
        for(MazeNode n : node.adj) {
            if(maze[node.x][node.y] == " S") {
                path.clear();
                walkCounter = 0;
            }
            n.prev = node;
            checkAdj(maze, n, walkCounter += 1);  
        }
    
    }
}

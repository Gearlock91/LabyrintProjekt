package se.hig.aod.labyrintProjekt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MazeSolver {

    private String[][] maze;
    private Map<String, MazeNode> mazeMap = new HashMap<String, MazeNode>();
    
    
    private class Edge {
        private MazeNode dest;
        private int cost;

        public Edge(MazeNode destination, int cost) {
            dest = destination;
            this.cost = cost;
        }
    }

    private class MazeNode {

        private String name;
        private List<Edge> adj;
        private int dist;
        private MazeNode prev;

        public MazeNode(String name) {
            this.name = name;
            adj = new LinkedList<Edge>();
            reset();
        }

        public void reset() {
            dist = Integer.MAX_VALUE;
            prev = null;
        }

    }
    
    private class Path implements Comparable<Path>{
        
        private MazeNode dest;
        private int      cost;
        
        public Path(MazeNode destination, int cost) {
            this.dest = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path rhs) {
           int otherCost = rhs.cost;
           return cost < otherCost ?  -1 : cost > otherCost ? 1 : 0;
        }
    }

    public void SolveMaze(String[][] maze) {
        this.maze = maze;
        readMaze(maze);
        addEdge();
        printPath();
        dijkstra();
        getVertex();
        printPath();
        clearAll();
    }

    private MazeNode getMazeNode(String mazeNodeName) {
        MazeNode v = mazeMap.get(mazeNodeName);
        if(v == null) {
            v = new MazeNode(mazeNodeName);
            mazeMap.put(mazeNodeName, v);
        }
        return v;
    }
    
    private void addEdge(String sourceName, String destName, int cost) {
        MazeNode v = getMazeNode(sourceName);
        MazeNode w = getMazeNode(destName);
        v.adj.add(new Edge(w, cost));
    }
    
    private void printPath(MazeNode destination) {
        if(destination.prev != null) {
            printPath(destination.prev);
            System.out.print(" to ");
        }
        System.out.print(destination.name);
    }
    
    
    private void printPath(String destination) {
        MazeNode w = mazeMap.get(destination);
        if(w == null) {
            //throw new NoSuchElementException();
        }else if(w.dist == Integer.MAX_VALUE) {
            System.out.println(destination + "is unreachable");
        }else {
            System.out.print("(Cost is: " + w.dist + ")");
        }
    }
    
    private void clearAll() {
        for(MazeNode v : mazeMap.values()) {
            v.reset();
        }
    }
    
    private void dijkstra(String startName) {
        PriorityQueue<Path> pq = new PriorityQueue<Path>();
        MazeNode start = mazeMap.get(startName);
        if(start == null) {
            //throw new NoSuchElementException
        }
        
        clearAll();
        pq.add(new Path(start, 0));
        start.dist = 0;
        
        int nodeSeen = 0;
        while(!pq.isEmpty() && nodeSeen < mazeMap.size()) {
            Path mrec = pq.remove();
            MazeNode v = mrec.dest;
            
//            if(v.scratch != 0) {
//                continue;
//            }
//            v.scratch = 1;
            
            nodeSeen++;
            for(Edge e: v.adj) {
                MazeNode w = e.dest;
                int cvw = e.cost;
                if(cvw < 0) {
                    //throw new GraphExceptio("Graph has negative edges");
                }
                if( w.dist > v.dist + cvw) {
                    w.dist = v.dist + cvw;
                    w.prev = v;
                    pq.add(new Path(w,w.dist));
                }
            }
        }
    }
    
    private void readMaze(String[][] maze) {
       for(int i = 0; i < maze.length; i++) {
           
       }
    }
}

package se.hig.aod.labyrintProjekt;

import java.util.List;
import java.util.Map;



public class MazeSolver {
   
        private List<RoadNode> unvisitedNodes;

        private class RoadNode<T>{
            private int cost = Integer.MAX_VALUE;
            private T x;
            private T y;
            private boolean visited;
            
            public RoadNode(T x, T y, boolean visited ) {
                this.x = x;
                this.y = y;
                this.visited = visited;
            }
        }
        
    public void SolveMaze(/*int[][] road,*/ Map steps, int[] keys) {
       
          int x = (int) steps.get("x" + 0);
          int y = (int) steps.get("y" + 0);
          RoadNode<Integer> start = new RoadNode<Integer>(x,y, true);
   
          System.out.println(start.x + " " + start.y);
          
        //  steps.
          
//          System.err.println((steps.get("x" + keys[0]) + " "+ (steps.get("y" + keys[1]))));
          
          for(int i = 1; i < keys[0]; i++) {
              x =  (int) steps.get("x" + i);
              RoadNode<Integer> current = new RoadNode<Integer>(x, null, false);
              unvisitedNodes.add(current);
          }
          
          
          System.err.println(unvisitedNodes.size());
          for(RoadNode node : unvisitedNodes) {
              System.err.println(node);
          }
    }
}

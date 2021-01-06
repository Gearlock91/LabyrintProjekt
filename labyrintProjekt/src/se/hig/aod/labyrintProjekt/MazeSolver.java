package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation utav en BruteForce algoritm som tittar efter den kortaste
 * vägen från start till slutpunkten utav alla möjliga vägar.
 * @author Andreas Roghe
 * @author Sofia Ågren
 * @version 2021-01-06
 */

public class MazeSolver implements Solver {
    /**
     * Denna lista håller reda på den möjliga vägen fram till slutnoden E.
     */
    private List<MazeNode> path = new ArrayList<MazeNode>();
    /**
     * Antalet steg fram till slutnoden sätts till oändlighet inledningsvis.
     */
    private static final int INFINITY = Integer.MAX_VALUE;
    /**
     * Hållare för slutnoden.
     */
    private MazeNode endNode;

    /**
     * Klassen som håller reda på den aktuella informationen om en specifik väg som
     * är en väg i labyrinten.
     * @author Andreas Roghe
     * @author Sofia Ågren
     * @version 2021-12-19
     */
    private class MazeNode {
        /**
         * En lista för att hålla reda på närliggande celler till den aktuella cellen vi
         * står i.
         */
        private List<MazeNode> adj;
        /**
         * En pekare till föregående nod vi stod i.
         */
        private MazeNode prev;
        /**
         * Koordinaten x i matrisen för cellen.
         */
        private int x;
        /**
         * Koordinaten y i matrisen för cellen.
         */
        private int y;
        /**
         * Avståndet från start till den cell vi står i.
         */
        private int distance = 0;

        public MazeNode(final int x, final int y, final int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            adj = new ArrayList<MazeNode>();
        }
    }

    /**
     * Den publika metoden för att hämta in en labyrint att lösa med algoritmen.
     */
    public void solveMaze(final String[][] maze) {
        MazeNode startNode = readMaze(maze);
        long start = System.currentTimeMillis();
        bruteForce(maze, startNode);
        System.out.printf("Time to solve maze: %dms\n",System.currentTimeMillis() - start);
        printMaze(maze);
    }

    private MazeNode readMaze(final String[][] maze) {
        // find Start

        MazeNode start = null;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j].equals(" S")) {
                    start = new MazeNode(i, j, 0);
                }
            }
        }
        start.prev = start;
        return start;
    }

    private void bruteForce(final String[][] maze, final MazeNode start) {
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

    private void checkAdj(final String[][] maze, final MazeNode node) {
        // Avståndet från startpunkten till den aktuella noden.
        node.distance += node.prev.distance;
        // Om vi hittar slutpunkten lägg till den i path och fortsätt
        // sedan att kontrollera övriga noder.
        if (maze[node.x][node.y].equals(" E")) {
            path.add(node);
            return;
        }
        // Vi tittar om det finns en väg åt vänster,höger,uppåt samt nedåt.
        int left;
        int right;
        int up;
        int down;
        left = node.x - 1;
        right = node.x + 1;
        up = node.y - 1;
        down = node.y + 1;
        if (((maze[left][node.y].equals("  ") || (maze[left][node.y].equals(" E"))))
                && (left != node.prev.x)) {
            node.adj.add(new MazeNode(left, node.y, 1));
        }
        if (((maze[right][node.y].equals("  ") || (maze[right][node.y].equals(" E"))))
                && (right != node.prev.x)) {
            node.adj.add(new MazeNode(right, node.y, 1));
        }
        if (((maze[node.x][up].equals("  ") || (maze[node.x][up].equals(" E"))))
                && (up != node.prev.y)) {
            node.adj.add(new MazeNode(node.x, up, 1));
        }
        if (((maze[node.x][down].equals("  ") || (maze[node.x][down].equals(" E"))))
                && (down != node.prev.y)) {
            node.adj.add(new MazeNode(node.x, down, 1));
        }

        for (MazeNode n : node.adj) {
            n.prev = node;
            checkAdj(maze, n);
        }

    }

    private void printPath(final String[][] maze, final MazeNode node) {
        if (maze[node.x][node.y].equals(" S")) {
            return;
        } else {
            if (!(maze[node.x][node.y].equals(" E"))) {
                maze[node.x][node.y] = " *";
            }
            printPath(maze, node.prev);
        }
    }

    private void printMaze(final String[][] maze) {

        printPath(maze, endNode);

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

}

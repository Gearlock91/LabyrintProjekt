package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Vår implementation utav en enkel Mouse-algoritm där vi helt random bestämmer
 * vägval när vi kommer fram till en korsning.
 * @author Andreas Roghe
 * @author Sofia Ågren
 * @version 2021-01-06
 */

public class MouseAlgorithm implements Solver {
    /**
     * Antalet vägar vi ska kolla om vi kan gå.
     */
    private static final int POSSIBLE_ROAD = 4;
    /**
     * Riktningarna vi kan gå i form utav väderstreck.
     */
    private static final String[] DIRECTIONS = {"NORTH", "SOUTH", "EAST", "WEST"};
    /**
     * Vägen vi har gått.
     */
    private List<MazeNode> path;
    /**
     * Koordinaten x för startpunkten.
     */
    private int x;
    /**
     * Koordinaten y för startpunkten.
     */
    private int y;

    private class MazeNode {

        /**
         * Koordinaten x för cellen.
         */
        private int x;
        /**
         * Koordinaten y för cellen.
         */
        private int y;
        /**
         * Närliggande celler från den vi står i.
         */
        private List<String> adj;
        /**
         * Formatet på cellen, om de är en vägg eller väg.
         */
        private String format;

        public MazeNode(final int x, final int y, final String format) {
            this.x = x;
            this.y = y;
            this.format = format;
            adj = new ArrayList<String>();
        }

    }

    @Override
    public void solveMaze(final String[][] maze) {
        path = new ArrayList<MazeNode>();
        getStart(maze);
        randomMouze(maze);
        printMaze(maze);
    }

    private void printMaze(final String[][] maze) {

        for (MazeNode node : path) {
            if (!(node.format.equals(" S") || node.format.equals(" E"))) {
                maze[node.x][node.y] = " *";
            }

        }

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    private void randomMouze(final String[][] maze) {
        boolean cheezeFound = false;
        int left;
        int right;
        int up;
        int down;

        String direction = randomDirection();
        while (!cheezeFound) {
            left = x - 1;
            right = x + 1;
            up = y - 1;
            down = y + 1;

            switch (direction) {
            case "NORTH":
                if (maze[x][up].equals(" X")) {
                    direction = randomDirection();
                    break;
                }
                y--;
                MazeNode currentPos = new MazeNode(x, y, maze[x][y]);
                path.add(currentPos);
                if (currentPos.format.equals(" E")) {
                    cheezeFound = true;
                    break;
                }
                if (!(maze[x][currentPos.y - 1].equals("  X"))) {
                    currentPos.adj.add("NORTH");
                }
                if (!(maze[currentPos.x + 1][currentPos.y].equals(" X"))) {
                    currentPos.adj.add("EAST");
                }
                if (!(maze[currentPos.x - 1][currentPos.y].equals(" X"))) {
                    currentPos.adj.add("WEST");
                }
                if (currentPos.adj.isEmpty()) {
                    currentPos.adj.add("SOUTH");
                }
                Collections.shuffle(currentPos.adj);
                direction = currentPos.adj.get(0);
                currentPos.adj.clear();
                break;
            case "SOUTH":
                if (maze[x][down].equals(" X")) {
                    direction = randomDirection();
                    break;
                }
                y++;
                currentPos = new MazeNode(x, y, maze[x][y]);
                path.add(currentPos);
                if (currentPos.format.equals(" E")) {
                    cheezeFound = true;
                    break;
                }
                if (!(maze[x][currentPos.y + 1].equals(" X"))) {
                    currentPos.adj.add("SOUTH");
                }
                if (!(maze[currentPos.x - 1][currentPos.y].equals(" X"))) {
                    currentPos.adj.add("WEST");
                }
                if (!(maze[currentPos.x + 1][currentPos.y].equals(" X"))) {
                    currentPos.adj.add("EAST");
                }
                if (currentPos.adj.isEmpty()) {
                    currentPos.adj.add("NORTH");
                }
                Collections.shuffle(currentPos.adj);
                direction = currentPos.adj.get(0);
                currentPos.adj.clear();
                break;
            case "EAST":
                if (maze[right][y].equals(" X")) {
                    direction = randomDirection();
                    break;
                }
                x++;
                currentPos = new MazeNode(x, y, maze[x][y]);
                path.add(currentPos);
                if (currentPos.format.equals(" E")) {
                    cheezeFound = true;
                    break;
                }
                if (!(maze[currentPos.x + 1][y].equals(" X"))) {
                    currentPos.adj.add("EAST");
                }
                if (!(maze[x][currentPos.y - 1].equals(" X"))) {
                    currentPos.adj.add("NORTH");
                }
                if (!(maze[x][currentPos.y + 1].equals(" X"))) {
                    currentPos.adj.add("SOUTH");
                }
                if (currentPos.adj.isEmpty()) {
                    currentPos.adj.add("WEST");
                }
                Collections.shuffle(currentPos.adj);
                direction = currentPos.adj.get(0);
                currentPos.adj.clear();
                break;
            case "WEST":
                if (maze[left][y].equals(" X")) {
                    direction = randomDirection();
                    break;
                }
                x--;
                currentPos = new MazeNode(x, y, maze[x][y]);
                path.add(currentPos);
                if (currentPos.format.equals(" E")) {
                    cheezeFound = true;
                    break;
                }
                if (!(maze[currentPos.x - 1][y].equals(" X"))) {
                    currentPos.adj.add("WEST");
                }
                if (!(maze[x][currentPos.y - 1].equals(" X"))) {
                    currentPos.adj.add("NORTH");
                }
                if (!(maze[x][currentPos.y + 1].equals(" X"))) {
                    currentPos.adj.add("SOUTH");
                }
                if (currentPos.adj.isEmpty()) {
                    currentPos.adj.add("EAST");
                }
                Collections.shuffle(currentPos.adj);
                direction = currentPos.adj.get(0);
                currentPos.adj.clear();
                break;
            default:
                break;
            }

        }

    }

    private String randomDirection() {
        Random random = new Random();
        return DIRECTIONS[random.nextInt(POSSIBLE_ROAD)];
    }

    private void getStart(final String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j].equals(" S")) {
                    x = i;
                    y = j;
                }
            }
        }
    }

}

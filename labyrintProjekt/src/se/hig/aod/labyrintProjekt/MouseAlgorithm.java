package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MouseAlgorithm implements Solver {

    private final String[] DIRECTIONS = {"NORTH", "SOUTH", "EAST", "WEST"};
    private List<MazeNode> path;
    private int x;
    private int y;

    private class MazeNode {

        private int x;
        private int y;

        private String format;

        public MazeNode(int x, int y, String format) {
            this.x = x;
            this.y = y;
            this.format = format;
        }

    }

    @Override
    public void solveMaze(String[][] maze) {
        path = new ArrayList<MazeNode>();
        getStart(maze);
        randomMouze(maze);
        printMaze(maze);
    }

    private void printMaze(String[][] maze) {

        for (MazeNode node : path) {
            if (node.format.equals(" S") || node.format.equals(" E")) {

            } else {
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

    private void randomMouze(String[][] maze) {
        boolean cheezeFound = false;
        int left, right, down, up;

        String direction = randomDirection();
        while (!cheezeFound) {

            left = x - 1;
            right = x + 1;
            up = y - 1;
            down = y + 1;

            switch (direction) {
            case "NORTH":
                MazeNode nextPos = new MazeNode(x, up, maze[x][up]);
                if (nextPos.format.equals(" X")) {
                    String[] directions = {"SOUTH","WEST","EAST"};
                    Random newDirection = new Random();
                    direction = directions[newDirection.nextInt(3)];
                    break;
                }
                path.add(nextPos);
                if (nextPos.format.equals(" E")) {
                    cheezeFound = true;
                    break;
                } else {
                    y = up;
                }
                break;
            case "SOUTH":
                nextPos = new MazeNode(x, down, maze[x][down]);
                if (nextPos.format.equals(" X")) {
                    String[] directions = {"NORTH","WEST","EAST"};
                    Random newDirection = new Random();
                    direction = directions[newDirection.nextInt(3)];
                    break;
                }
                path.add(nextPos);
                if (nextPos.format.equals(" E")) {
                    cheezeFound = true;
                    break;
                } else {
                    y = down;
                }
                break;
            case "EAST":
                nextPos = new MazeNode(right, y, maze[right][y]);
                if (nextPos.format.equals(" X")) {
                    String[] directions = {"SOUTH","WEST","NORTH"};
                    Random newDirection = new Random();
                    direction = directions[newDirection.nextInt(3)];
                    break;
                }
                path.add(nextPos);
                if (nextPos.format.equals(" E")) {
                    cheezeFound = true;
                    break;
                } else {
                    x = right;
                }
                break;
            case "WEST":
                nextPos = new MazeNode(left, y, maze[left][y]);
                if (nextPos.format.equals(" X")) {
                    String[] directions = {"SOUTH","NORTH","EAST"};
                    Random newDirection = new Random();
                    direction = directions[newDirection.nextInt(3)];
                    break;
                }
                path.add(nextPos);
                if (nextPos.format.equals(" E")) {
                    cheezeFound = true;
                    break;
                } else {
                    x = left;
                }
                break;
            }
        }

    }

    private String randomDirection() {
        Random random = new Random();
        return DIRECTIONS[random.nextInt(4)];
    }

    private void getStart(String[][] maze) {
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

package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.Collections;
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
        private List<String> adj;
        private String format;

        public MazeNode(int x, int y, String format) {
            this.x = x;
            this.y = y;
            this.format = format;
            adj = new ArrayList<String>();
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
                if(maze[x][up].equals(" X")) {
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
                if (maze[x][currentPos.y - 1].equals("  ") || maze[x][currentPos.y - 1].equals(" E") || maze[x][currentPos.y - 1].equals(" S") ) {
                    currentPos.adj.add("NORTH");
                }
                if (maze[currentPos.x + 1][currentPos.y].equals("  ") || maze[currentPos.x + 1][currentPos.y].equals(" E") || maze[currentPos.x + 1][currentPos.y].equals(" S")) {
                    currentPos.adj.add("EAST");
                }
                if (maze[currentPos.x - 1][currentPos.y].equals("  ") || maze[currentPos.x - 1][currentPos.y].equals(" E") || maze[currentPos.x - 1][currentPos.y].equals(" S")) {
                    currentPos.adj.add("WEST");
                }
                if(currentPos.adj.isEmpty()) {
                    currentPos.adj.add("SOUTH");
                }
                Collections.shuffle(currentPos.adj);
                direction = currentPos.adj.get(0);
                currentPos.adj.clear();
                break;
            case "SOUTH":
                if(maze[x][down].equals(" X")) {
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
                if (maze[x][currentPos.y + 1].equals("  ") ||  maze[x][currentPos.y + 1].equals(" E") || maze[x][currentPos.y + 1].equals(" S")) {
                    currentPos.adj.add("SOUTH");
                }
                if (maze[currentPos.x - 1][currentPos.y].equals("  ") ||  maze[currentPos.x - 1][currentPos.y].equals(" E") || maze[currentPos.x - 1][currentPos.y].equals(" S")) {
                    currentPos.adj.add("WEST");
                }
                if (maze[currentPos.x + 1][currentPos.y].equals("  ") ||  maze[currentPos.x + 1][currentPos.y].equals(" E") || maze[currentPos.x + 1][currentPos.y].equals(" S")) {
                    currentPos.adj.add("EAST");
                }
                if(currentPos.adj.isEmpty()) {
                    currentPos.adj.add("NORTH");
                }
                Collections.shuffle(currentPos.adj);
                direction = currentPos.adj.get(0);
                currentPos.adj.clear();
                break;
            case "EAST":
                if(maze[right][y].equals(" X")) {
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
                if (maze[currentPos.x + 1][y].equals("  ") || maze[currentPos.x + 1][y].equals(" E") || maze[currentPos.x + 1][y].equals(" S")) {
                    currentPos.adj.add("EAST");
                }
                if (maze[x][currentPos.y - 1].equals("  ") || maze[x][currentPos.y - 1].equals(" E") || maze[x][currentPos.y - 1].equals(" S")) {
                    currentPos.adj.add("NORTH");
                }
                if (maze[x][currentPos.y + 1].equals("  ") || maze[x][currentPos.y + 1].equals(" E") ||  maze[x][currentPos.y + 1].equals(" S")) {
                    currentPos.adj.add("SOUTH");
                }
                if(currentPos.adj.isEmpty()) {
                    currentPos.adj.add("WEST");
                }
                Collections.shuffle(currentPos.adj);
                direction = currentPos.adj.get(0);
                currentPos.adj.clear();
                break;
            case "WEST":
                if(maze[left][y].equals(" X")) {
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
                if (maze[currentPos.x - 1][y].equals("  ") || maze[currentPos.x - 1][y].equals(" E") || maze[currentPos.x - 1][y].equals(" S")) {
                    currentPos.adj.add("WEST");
                }
                if (maze[x][currentPos.y - 1].equals("  ") || maze[x][currentPos.y - 1].equals(" E") || maze[x][currentPos.y - 1].equals(" S")) {
                    currentPos.adj.add("NORTH");
                }
                if (maze[x][currentPos.y + 1].equals("  ") || maze[x][currentPos.y + 1].equals(" E") || maze[x][currentPos.y + 1].equals(" S")) {
                    currentPos.adj.add("SOUTH");
                }
                if(currentPos.adj.isEmpty()) {
                    currentPos.adj.add("EAST");
                }
                Collections.shuffle(currentPos.adj);
                direction = currentPos.adj.get(0);
                currentPos.adj.clear();
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

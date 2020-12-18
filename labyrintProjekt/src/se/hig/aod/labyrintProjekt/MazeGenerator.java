package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {

    private int height = 19;
    private int width = 19;
    private final ArrayList<String> DIRECTIONS = new ArrayList<>(
            Arrays.asList("NORTH", "SOUTH", "WEST", "EAST"));
    private HashMap<String, Integer> steps = new HashMap<>();
    private String keyLetter = "";
    private int keyIntX = 0;
    private int keyIntY = 0;
    private String[][] walls;

    public MazeGenerator(/* int width, int height */) {
        /*
         * if (width > 2 && height > 2) { this.height = height; this.width = width;
         * }else { //throw new InvalidSizeException }
         */

        walls = new String[height][width];
        createMaze();
    }

    private void createMaze() {
        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls.length; j++) {
                walls[i][j] = " X";
            }
        }

        int startX = findRandomPoint();
        int startY = findRandomPoint();

        dig(startX, startY);

        walls[startX][startY] = " S";

        walls[steps.get("x" + keyIntX)][steps.get("y" + keyIntY)] = " E";
    }

    private void dig(int x, int y) {
        Collections.shuffle(DIRECTIONS);
        for (int i = 0; i < DIRECTIONS.size(); i++) {

            switch (DIRECTIONS.get(i)) {
            case "NORTH": // Up
                // Whether 2 cells up is out or not
                if (x - 2 <= 0)
                    continue;
                if (walls[x - 2][y] != "  ") {
                    walls[x - 2][y] = "  ";
                    walls[x - 1][y] = "  ";
                    keyIntX++;
                    steps.put("x" + keyIntX, x - 2);
                    dig(x - 2, y);
                }
                break;
            case "EAST": // Right
                // Whether 2 cells to the right is out or not
                if (y + 2 >= width - 1)
                    continue;
                if (walls[x][y + 2] != "  ") {
                    walls[x][y + 2] = "  ";
                    walls[x][y + 1] = "  ";
                    keyIntY++;
                    steps.put("y" + keyIntY, y + 2);
                    dig(x, y + 2);
                }
                break;
            case "SOUTH": // Down
                // Whether 2 cells down is out or not
                if (x + 2 >= height - 1)
                    continue;
                if (walls[x + 2][y] != "  ") {
                    walls[x + 2][y] = "  ";
                    walls[x + 1][y] = "  ";
                    keyIntX++;
                    steps.put("x" + keyIntX, x + 2);
                    dig(x + 2, y);
                }
                break;
            case "WEST": // Left
                // Whether 2 cells to the left is out or not
                if (y - 2 <= 0)
                    continue;
                if (walls[x][y - 2] != "  ") {
                    walls[x][y - 2] = "  ";
                    walls[x][y - 1] = "  ";
                    keyIntY++;
                    steps.put("y" + keyIntY, y - 2);
                    dig(x, y - 2);
                }
                break;
            }
        }

    }

    private int findRandomPoint() {
        boolean oddNumber = false;
        int hold = 0;
        while (!oddNumber) {
            Random random = new Random();
            hold = random.nextInt(width);
            if (hold % 2 != 0) {
                oddNumber = true;
            }
        }
        return hold;
    }

    public void printTestMaze() {
        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls.length; j++) {
                System.out.print(walls[i][j]);
            }
            System.out.println();
        }

    }
}

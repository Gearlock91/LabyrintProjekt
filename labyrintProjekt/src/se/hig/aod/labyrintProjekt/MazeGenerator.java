package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MazeGenerator {

    private int height = 99;
    private int width = 99;
    private final ArrayList<String> DIRECTIONS = new ArrayList<>(
            Arrays.asList("NORTH", "SOUTH", "WEST", "EAST"));

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
                walls[i][j] = " X ";
            }
        }

        int startX = findStartPoint();
        int startY = findStartPoint();

        walls[startX][startY] = " S ";

        dig(startX, startY);

    }

    private void dig(int x, int y) {
        Collections.shuffle(DIRECTIONS);
        for (int i = 0; i < DIRECTIONS.size(); i++) {

            switch (DIRECTIONS.get(i)) {
            case "NORTH": // Up
                // Whether 2 cells up is out or not
                if (x - 2 <= 0)
                    continue;
                if (walls[x - 2][y] != "   ") {
                    walls[x - 2][y] = "   ";
                    walls[x - 1][y] = "   ";
                    dig(x - 2, y);
                }
                break;
            case "EAST": // Right
                // Whether 2 cells to the right is out or not
                if (y + 2 >= width - 1)
                    continue;
                if (walls[x][y + 2] != "   ") {
                    walls[x][y + 2] = "   ";
                    walls[x][y + 1] = "   ";
                    dig(x, y + 2);
                }
                break;
            case "SOUTH": // Down
                // Whether 2 cells down is out or not
                if (x + 2 >= height - 1)
                    continue;
                if (walls[x + 2][y] != "   ") {
                    walls[x + 2][y] = "   ";
                    walls[x + 1][y] = "   ";
                    dig(x + 2, y);
                }
                break;
            case "WEST": // Left
                // Whether 2 cells to the left is out or not
                if (y - 2 <= 0)
                    continue;
                if (walls[x][y - 2] != "   ") {
                    walls[x][y - 2] = "   ";
                    walls[x][y - 1] = "   ";
                    dig(x, y - 2);
                }
                break;
            }
        }

    }

    private int findStartPoint() {
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

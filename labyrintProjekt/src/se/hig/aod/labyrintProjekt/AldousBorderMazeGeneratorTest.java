package se.hig.aod.labyrintProjekt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AldousBorderMazeGeneratorTest {
    /**
     * Storleken på labyrinten vi vill skapa.
     */
    private static final int SIZE = 100;
    /**
     * Skapar en labyrint om 100x100.
     */
    private Maze generator = new AldousBorderAlgorithm(SIZE);

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
        String[][] maze = generator.getMaze();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

}

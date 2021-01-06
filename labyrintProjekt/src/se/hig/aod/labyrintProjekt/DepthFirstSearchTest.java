package se.hig.aod.labyrintProjekt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepthFirstSearchTest {
    /**
     * Storleken av vår tänkta labyrint.
     */
    private static final int LABYRINTH_SIZE = 100;
    /**
     * Vår pekare för den abstrakta generatorn för DepthFirst algoritmen.
     */
    private Maze generator;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
        generator = new DepthFirstSearch(LABYRINTH_SIZE);
        String[][] maze = generator.getMaze();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

}

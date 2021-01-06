package se.hig.aod.labyrintProjekt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MazeSolverTest {
    /**
     * Storleken på labyrinten vi vill ha.
     */
    private static final int MAZE_SIZE = 20;
    /**
     * Vår generator med Aldous-Border Algoritmen.
     */
    private Maze aldousGenerator = new AldousBorderAlgorithm(MAZE_SIZE);

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
        MazeSolver solver = new MazeSolver();
        solver.solveMaze(aldousGenerator.getMaze());

    }

}

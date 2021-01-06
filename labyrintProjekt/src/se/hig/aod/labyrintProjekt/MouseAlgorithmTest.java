package se.hig.aod.labyrintProjekt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MouseAlgorithmTest {
    /**
     * Storleken på den tänkta labyrinten.
     */
    private static final int MAZE_SIZE = 20;
    /**
     * Våran lösnings-algoritm pekare.
     */
    private Solver mouseAlgorithm;
    /**
     * Vår generator pekare.
     */
    private Maze ab;

    @BeforeEach
    void setUp() throws Exception {
        mouseAlgorithm = new MouseAlgorithm();
        ab = new AldousBorderAlgorithm(MAZE_SIZE);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
        mouseAlgorithm.solveMaze(ab.getMaze());
    }

}

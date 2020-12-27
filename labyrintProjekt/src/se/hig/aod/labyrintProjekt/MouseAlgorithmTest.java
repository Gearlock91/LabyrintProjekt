package se.hig.aod.labyrintProjekt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MouseAlgorithmTest {
    
    Solver wf;
    Maze ab;
    
    @BeforeEach
    void setUp() throws Exception {
        wf = new MouseAlgorithm();
        ab = new AldousBorderAlgorithm(20);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
        wf.solveMaze(ab.getMaze());
    }

}

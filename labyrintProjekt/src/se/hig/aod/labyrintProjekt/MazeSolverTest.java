package se.hig.aod.labyrintProjekt;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MazeSolverTest {
    
    AldousBorderMazeGeneratorOLD aldousGenerator = new AldousBorderMazeGeneratorOLD(20);

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

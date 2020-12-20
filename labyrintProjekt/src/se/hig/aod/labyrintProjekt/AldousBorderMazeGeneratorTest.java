package se.hig.aod.labyrintProjekt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AldousBorderMazeGeneratorTest {
    
    AldousBorderMazeGenerator generator = new AldousBorderMazeGenerator(20);

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
        generator.printMaze();
    }

}

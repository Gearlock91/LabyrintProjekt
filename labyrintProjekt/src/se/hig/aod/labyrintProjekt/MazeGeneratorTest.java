package se.hig.aod.labyrintProjekt;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MazeGeneratorTest {

    MazeGenerator generator;
    
    @BeforeEach
    void setUp() throws Exception {
        generator = new MazeGenerator();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
       generator.printTestMaze();
    }

}


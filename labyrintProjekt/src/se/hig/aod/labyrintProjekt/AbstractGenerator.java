package se.hig.aod.labyrintProjekt;

import java.util.ArrayList;
import java.util.List;

/**
 * Den abstrakta implementationen utav hur en generator för en Maze skall se ut.
 * @author Andreas Roghe
 * @author Sofia Ågren
 * @version 2020-01-06
 */

public abstract class AbstractGenerator implements Maze {

    /**
     * Deklaration utav hur generatorerna skall orientera sig mha väderstreck.
     */
    protected static final String[] DIRECTIONS = {"NORTH", "SOUTH", "WEST", "EAST"};
    /**
     * De flesta algoritmer behövde en lista över all noder som inte har besökts än.
     */
    protected List<Cell> unvisitedCells = new ArrayList<Cell>();
    /**
     * Hållaren för labyrinten.
     */
    protected Cell[][] maze;
    /**
     * Bredden på labyrinten. Inkluderad yttreväggarna.
     */
    protected int width;
    /**
     * Höjden på labyrinten. Inkluderad yttreväggarna.
     */
    protected int height;

    /**
     * Klassen som representerar en cell eller en vägg.
     * @author Andreas Roghe, Sofia Ågren
     * @version 2020-01-06
     */
    protected class Cell {
        /**
         * Listan som håller koll på en specifik cells närliggande celler.
         */
        protected List<Cell> adjecent;
        /**
         * En pekare till föregående cell.
         */
        protected Cell previous;
        /**
         * En kontroll för att veta om vi redan har varit i en cell.
         */
        protected boolean visited = true;
        /**
         * Väggarna representeras med ett enkelt X.
         */
        protected String format = " X";
        /**
         * Koordinaten x för en cell.
         */
        protected int x;
        /**
         * Koordinaten y för en cell.
         */
        protected int y;

        public Cell(final int x, final int y, final int wallDefiner) {
            if (wallDefiner != 0) {
                visited = false;
                this.x = x;
                this.y = y;
                adjecent = new ArrayList<Cell>();
            }
        }
    }

    /**
     * Konstruktorn för en maze. Kontroll om det är ett jämnt tal som matades in av
     * användaren för att få en kvadratisk labyrint.
     * @param size
     */
    public AbstractGenerator(final int size) {
        if (size % 2 != 0) {
            // throw new IllegalArgument
            System.err.println("IllegalArgument!");
        } else {
            this.width = size - 1;
            this.height = size - 1;
            createMaze(width, height);
        }
    }

    private void createMaze(final int w, final int h) {
        maze = new Cell[w][h];
        createBoard();
        createPerimeter();
        algorithm(maze);
    }

    private void createBoard() {
        for (int i = 1; i < maze.length; i++) {
            for (int j = 1; j < maze.length; j++) {
                int uneven = 0;

                if (j % 2 != 0 && i % 2 != 0) {
                    uneven = 1;
                }

                maze[i][j] = new Cell(i, j, uneven);

                if (!(maze[i][j].visited)) {
                    unvisitedCells.add(maze[i][j]);
                }
            }
        }
    }

    private void createPerimeter() {
        for (int i = 0; i < maze.length; i++) {
            maze[0][i] = new Cell(0, i, 0);
        }
        for (int i = 0; i < maze.length; i++) {
            maze[maze.length - 1][i] = new Cell(maze.length - 1, i, 0);
        }
        for (int i = 0; i < maze.length; i++) {
            maze[i][0] = new Cell(i, 0, 0);
        }
        for (int i = 0; i < maze.length; i++) {
            maze[i][maze.length - 1] = new Cell(i, maze.length - 1, 0);
        }
    }

    /**
     * Metod för att hämta ut den skapta labyrinten. Används för att skicka vidare
     * labyrinten till en solver.
     * @return String[][] - Den färdigbyggda labyrinten.
     */
    public String[][] getMaze() {
        String[][] convertMaze = new String[width][height];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                convertMaze[i][j] = maze[i][j].format;
            }
        }

        return convertMaze;
    }

    abstract void algorithm(Cell[][] maze);

}

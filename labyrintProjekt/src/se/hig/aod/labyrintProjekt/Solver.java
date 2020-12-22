package se.hig.aod.labyrintProjekt;
/**
 * 
 * @author Sofia Ågren
 * @author Andreas Roghe
 *
 */
public interface Solver {
	/**
	 * Löser labyrinten.
	 * @param maze
	 */
	void solvMaze(String[][] maze);
	/**
	 * Skriver ut labyrinten.
	 * @param maze
	 */
	void printMaze(String[][] maze);
	
	

}

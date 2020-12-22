package se.hig.aod.labyrintProjekt;
/**
 * 
 * @author Sofia Ågren
 * @author Andreas Roghe
 *
 */
public interface Maze {
	/**
	 * Skapar yttre väggar och sätter de som fasta.
	 */
	void createPerimeter();
	/**
	 * Skapar en labyrint.
	 */
	void createBoard();
	/**
	 * Skapar vägarna i labyrinten.
	 * @param width.
	 * @param height.
	 */	
	void createMaze(int width, int height);
	/**
	 * Hämtar labyrinten och konverterar den.
	 * @return convertMaze.
	 */
	String[][] getMaze();
}

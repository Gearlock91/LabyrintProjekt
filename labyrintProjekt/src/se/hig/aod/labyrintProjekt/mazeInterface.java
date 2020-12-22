package se.hig.aod.labyrintProjekt;

public interface mazeInterface {
	
	void solvMaze(String[][] maze);
	void printMaze(String[][] maze);
	String[][] getMaze();
	void createPerimeter();
	void createBoard();
	void createMaze(int width, int height);

}

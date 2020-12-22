package se.hig.aod.labyrintProjekt;

public interface AldusMazeInterface {

	void createPerimeter();
	void createBoard();
	void createMaze(int width, int height);
	String[][] getMaze();
}

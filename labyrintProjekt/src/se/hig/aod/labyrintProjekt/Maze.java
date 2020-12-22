package se.hig.aod.labyrintProjekt;

public interface Maze {

	void createPerimeter();
	void createBoard();
	void createMaze(int width, int height);
	String[][] getMaze();
}

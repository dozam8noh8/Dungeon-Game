package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Player;
import unsw.dungeon.objectives.*;

class CompleteableLevels {

	@Test
	void no_objective() throws FileNotFoundException {
		MazeController maze = new MazeController("maze3.json");
		Dungeon dungeon = maze.load();
		assertTrue(dungeon==null);
	}
	
	@Test
	void finish_objectives() throws FileNotFoundException {
		MazeController maze = new MazeController("maze.json");
		Dungeon dungeon = maze.load();
		dungeon.completeExitObjective(dungeon.getObjective());
		assertTrue(dungeon.getComplete());
	}
	
	@Test
	void items_on_top() throws FileNotFoundException {
		MazeController maze = new MazeController("maze4.json");
		Dungeon dungeon = maze.load();
		ArrayList<Entity> entitesOnFirstSquare = dungeon.getEntOnSq(0,0);
		assertTrue(entitesOnFirstSquare.size()==1);
	}
	
	@Test
	void check_and_objectives() throws FileNotFoundException {
		MazeController maze = new MazeController("maze22.json");
		Dungeon dungeon = maze.load();
		dungeon.completeExitObjective(dungeon.getObjective());
		dungeon.completeEnemyObjective(dungeon.getObjective());
		assertTrue(dungeon.getComplete());
	}

	@Test
	void check_and_subobjectives() throws FileNotFoundException {
		MazeController maze = new MazeController("maze24.json");
		Dungeon dungeon = maze.load();
		dungeon.completeExitObjective(dungeon.getObjective());
		dungeon.completeEnemyObjective(dungeon.getObjective());
		dungeon.completeTreasureObjective(dungeon.getObjective());
		assertTrue(dungeon.getComplete());
	}
	
	@Test
	void check_or_subobjectives() throws FileNotFoundException {
		MazeController maze = new MazeController("maze25.json");
		Dungeon dungeon = maze.load();
		dungeon.completeExitObjective(dungeon.getObjective());
		dungeon.completeTreasureObjective(dungeon.getObjective());
		assertTrue(dungeon.getComplete());
	}
	@Test
	void check_and_objectives2() throws FileNotFoundException{
		MazeController maze = new MazeController("enemiesAndExit.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		assertFalse(dungeon.getComplete()); //no objectives done yet
		dungeon.completeExitObjective(dungeon.getObjective()); //shouldnt do anything because other obj not done
		assertFalse(dungeon.getComplete());
		dungeon.completeEnemyObjective(dungeon.getObjective());
		player.moveDown(); //reset exit objective upon move.
		assertFalse(dungeon.getComplete()); //shouldnt be complete because non exit
		dungeon.completeExitObjective(dungeon.getObjective()); //now we have copmleted other, should finish
		assertTrue(dungeon.getComplete());
	}
	@Test
	void check_single_exit_objective() throws FileNotFoundException{
		MazeController maze = new MazeController("singleExitObj21.json");
		Dungeon dungeon = maze.load();
		dungeon.completeExitObjective(dungeon.getObjective());
		assertTrue(dungeon.getComplete());
	}
	
	@Test
	void check_or_objectives() throws FileNotFoundException{
		MazeController maze = new MazeController("enemyOrExit23.json");
		Dungeon dungeon = maze.load();
		dungeon.completeExitObjective(dungeon.getObjective());
		assertTrue(dungeon.getComplete());
	}
	

}

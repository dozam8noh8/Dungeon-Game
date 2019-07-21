package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Door;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Player;

class DoorTests {

	@Test
	void unlock_door() throws FileNotFoundException {
		MazeController maze = new MazeController("maze6.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		player.moveDown();
		assertTrue(player.getX()==1 && player.getY()==3);
	}
	
	@Test
	void players_can_go_back() throws FileNotFoundException {
		MazeController maze = new MazeController("maze6.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		player.moveDown();
		player.moveUp();
		assertTrue(player.getX()==1 && player.getY()==2);
	}
	
	@Test
	void cannot_unlock_with_no_key() throws FileNotFoundException {
		MazeController maze = new MazeController("maze7.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		player.moveDown();
		assertTrue(player.getX()==1 && player.getY()==1);
	}
	
	@Test
	void unlock_with_wrong_key() throws FileNotFoundException {
		MazeController maze = new MazeController("maze9.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		player.moveDown();
		System.out.println(player.getX()+"------"+player.getY());
		assertTrue(player.getX()==1 && player.getY()==2);
	}
	
	@Test
	void pushing_boulder_into_door() throws FileNotFoundException {
		MazeController maze = new MazeController("maze21.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		List<Entity> entities = dungeon.getEntOnSq(1,3);
		boolean moved = false;
		for (Entity e: entities) {
			if (e instanceof Door) {
				moved = true;
			}
		}
		assertTrue(player.getY()==1 && moved);
	}
	
	@Test
	void pushing_boulder_into_unlocked_door() throws FileNotFoundException {
		MazeController maze = new MazeController("maze26.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		List<Entity> entities = dungeon.getEntOnSq(1,4);
		for (Entity e: entities) {
			if (e instanceof Door) {
				((Door) e).openDoor(player);
			}
		}
		player.moveDown();
		assertTrue(player.getY()==3);
	}

}

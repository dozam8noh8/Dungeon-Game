package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Player;

class LifeTests {

	@Test
	void life_collected_when_walked_on() throws FileNotFoundException {
		MazeController maze = new MazeController("lifeTests.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		assertEquals(player.getLives(), 1, "1 life to start");
		player.moveDown();
		assertEquals(player.getLives(), 2, "2 lives now");
	}
	@Test
	void secondLife_with_enemy() throws FileNotFoundException {
		MazeController maze = new MazeController("lifeTests.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		assertEquals(player.getLives(), 1, "1 live to start");
		player.moveDown();
		assertEquals(player.getLives(), 2, "2 lives now");
		player.moveDown();
		assertEquals(player.getLives(), 1, "1 lives now, hit enemy");
		
	}

}

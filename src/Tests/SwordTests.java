package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Player;

class SwordTests {

	@Test
	void player_can_carry_only_one_sword() throws FileNotFoundException {
		MazeController maze = new MazeController("maze18.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		Object o = player.getWeapon();
		assertTrue(!(o instanceof List));
	}
	
	@Test
	void player_can_kill_enemy() throws FileNotFoundException {
		MazeController maze = new MazeController("maze18.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		player.attack();
		List<Entity> entities = dungeon.getEntOnSq(1, 3);
		assertTrue(entities.size()==0);
	}
	
	@Test
	void sword_has_5_hits() throws FileNotFoundException {
		MazeController maze = new MazeController("maze18.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		player.attack();
		player.attack();
		player.attack();
		player.attack();
		player.attack();
		assertTrue(player.getWeapon()==null);
	}

}

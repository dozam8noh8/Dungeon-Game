package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Observer;
import unsw.dungeon.entities.Enemy;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Player;

class BombTests {

	@Test
	void player_picks_bomb() throws FileNotFoundException {
		MazeController maze = new MazeController("maze8.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		assertTrue(player.getBombs().size()==1);
	}
	
	@Test
	void player_picks_multiple_bomb() throws FileNotFoundException {
		MazeController maze = new MazeController("maze8.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		player.moveDown();
		assertTrue(player.getBombs().size()==2);
	}
	
	@Test
	void player_kill_enemy_after_3_secs() throws FileNotFoundException, InterruptedException {
		MazeController maze = new MazeController("advanced2.json");
		Dungeon dungeon = maze.load();
		Player player = dungeon.getPlayer();
		player.moveDown();
		player.useBomb();
		TimeUnit.SECONDS.sleep(1);
		List<Entity> entities = dungeon.getEntities();
		int x = 0;
		for (Entity e : entities) {
			if (e instanceof Enemy) {
				x=x+1;
			}
		}
		assertTrue(x==1);
	}

}

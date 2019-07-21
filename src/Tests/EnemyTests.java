package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.*;

class EnemyTests {

	@Test
	void enemy_move_up_down_left_right() throws FileNotFoundException {
		MazeController maze = new MazeController("maze19.json");
		Dungeon dungeon = maze.load();
		Enemy enemy = new Enemy(dungeon, 3, 3);
		enemy.move(1, 0);
		assertEquals(enemy.getX(), 4);
		assertEquals(enemy.getY(), 3);
		enemy.move(-1, 0);
		assertEquals(enemy.getX(), 3);
		assertEquals(enemy.getY(), 3);
		enemy.move(0, -1);
		assertEquals(enemy.getX(), 3);
		assertEquals(enemy.getY(), 2);
		enemy.move(0, 1);
		assertEquals(enemy.getX(), 3);
		assertEquals(enemy.getY(), 3);
		
	}
	@Test
	void enemy_cant_move_into_wall() throws FileNotFoundException{
		MazeController maze = new MazeController("enemyTestsWall.json");
		Dungeon dungeon = maze.load();
		Enemy enemy = new Enemy(dungeon, 3, 2);
		assertTrue(enemy.getX() == 3);
		assertTrue(enemy.getY() == 2);
		enemy.move(0,1); //blocked by wall
		System.out.println(enemy.getX());
		System.out.println(enemy.getY());
		assertTrue(enemy.getX() == 3);
		assertTrue(enemy.getY() == 2);
		
	}
	@Test
	void enemy_cant_move_into_boulder() throws FileNotFoundException{
		MazeController maze = new MazeController("enemyTestsBoulder.json");
		Dungeon dungeon = maze.load();
		Enemy enemy = new Enemy(dungeon, 3, 2);
		assertTrue(enemy.getX() == 3);
		assertTrue(enemy.getY() == 2);
		enemy.move(0,1); //blocked by boulder
		System.out.println(enemy.getX());
		System.out.println(enemy.getY());
		assertTrue(enemy.getX() == 3);
		assertTrue(enemy.getY() == 2);
		
	}


		
}
	
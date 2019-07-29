package testClasses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Player;
import unsw.dungeon.entities.Sword;

class testSword {
	Sword sword = new Sword(5,5);
	Dungeon d = new Dungeon(30,30);
	Player player = new Player(d, 8,7);

	
	@Test
	void testShouldBreakAfterFiveHits() {
		/*Sword sword = new Sword(5,5);
		Dungeon d = new Dungeon(30,30);
		Player player = new Player(d, 8,7);*/
		player.setWeapon(sword);
		player.attack();
		player.attack();
		player.attack();
		player.attack();
		System.out.println(player.getWeapon());
		assertEquals(sword, player.getWeapon(), "Weapon should be sword");
		player.attack();
		assertEquals(null, player.getWeapon(), "Weapon should be null");
		System.out.println(player.getWeapon());
	}

}

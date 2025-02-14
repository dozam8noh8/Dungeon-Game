package unsw.dungeon.entities;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Weapon;

/**
 * The Sword class is a type of weapon that can be picked up by players.
 * It can be used 5 times before breaking and being unable to be used.
 * @author Owen Silver and Waqif Alam
 *
 */
public class Sword extends Entity implements Weapon{
	private int attacks = 5; //The number of attacks the weapon has
	private Player p; //The player using the sword.
	/**
	 * 
	 * @param x - the x coordinate on which the sword lies
	 * @param y - the y coordinate on which the sword lies
	 */
	public Sword(int x, int y) {
		super(x, y);
	}

	/**
	 * Method override of Entity squareBehaviour, called when a player walks on this
	 * object. The player picks up this weapon and it is removed from the floor of the dungeon.
	 */
	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("STEPPED ON A Sword");
		Dungeon dungeon = p.getDungeon();
		dungeon.removeEntity(this);
		p.setWeapon(this);
		setAlive(false);
		p.setWeaponName(attacks+" Hits Left");
	}

	/**
	 * Overrode method from Weapon interface. The specifics of swords attacks.
	 * Breaks sword after 5 hits, setting players weapon to null.
	 * Sword attacks in the squares immediately above, below, left and right
	 * as if spinning in a 2d block circle.
	 */
	@Override
	public void attack(Player p) {

				System.out.println("USING SWORD");
				int x = p.getX();
				int y = p.getY();
				Dungeon dungeon = p.getDungeon();
				attackOnSquare(x+1,y, p, dungeon); //attack right
				attackOnSquare(x-1,y, p, dungeon); //attack left
				attackOnSquare(x,y+1, p, dungeon); //attack down
				attackOnSquare(x,y-1, p, dungeon); //attack up
				attacks --;
				p.setWeaponName(attacks+" Hits Left");
				if (attacks < 1) {
					p.setWeapon(null);
					p.setWeaponName("None");
				}

	}
	/**
	 * Helper function for attack method, will remove and kill all killable entities 
	 * on a particular square. Checks if square is a valid dungeon square.
	 * @param x - the x coordinate of the square that will be checked.
	 * @param y - the y coordinate of the square that will be checked.
	 */
	private void attackOnSquare(int x, int y, Player p, Dungeon dungeon) {
		if ((y >= 0) && (y < dungeon.getHeight()-1) //check if valid attack location
		&& (x >= 0) && (x < dungeon.getWidth()-1)){
			ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x, y);
			for (Entity e : entOnSq) {
				if (e instanceof Enemy) {
					dungeon.removeEntity(e);
					((Enemy) e).killEnemy();
					System.out.println("Killed enemy");
				}
			
			}
		}
	}
	
	/**
	 * Get how many attacks the sword has
	 * @return int of how many attacks
	 */
	public int getAttacks() {
		return this.attacks;
	}
		
}
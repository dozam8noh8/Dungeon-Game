package unsw.dungeon;

import javafx.beans.property.StringProperty;
import unsw.dungeon.entities.Player;

public interface Weapon {
	public void attack(Player p);
	public StringProperty getName();
	
}

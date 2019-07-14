package unsw.dungeon;

public class TestThread extends Thread {
	private Player p;
	
	public TestThread(Player p) {
		this.p = p;
	}
	
	public void run() {
		try { Thread.sleep(2000); } catch (Exception e) {}
		p.useSword();
		try { Thread.sleep(2000); } catch (Exception e) {}
		p.useSword();
		try { Thread.sleep(2000); } catch (Exception e) {}
		p.useSword();
	}
}

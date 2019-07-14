package unsw.dungeon;

public class TestSwordThread extends Thread {
	private Player p;
	
	public TestSwordThread(Player p) {
		this.p = p;
	}
	
	public void run() {
		try { Thread.sleep(2000); } catch (Exception e) {}
		//p.useBomb();
		try { Thread.sleep(2000); } catch (Exception e) {}
		//p.useBomb();
		try { Thread.sleep(2000); } catch (Exception e) {}
		//p.useBomb();
		try { Thread.sleep(2000); } catch (Exception e) {}
		//p.useBomb();
		try { Thread.sleep(2000); } catch (Exception e) {}
		//p.useBomb();
		try { Thread.sleep(2000); } catch (Exception e) {}
		//p.useBomb();
		try { Thread.sleep(2000); } catch (Exception e) {}
		//p.useBomb();
		try { Thread.sleep(2000); } catch (Exception e) {}
		p.useBomb();
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONLevelBuilder {

	private JSONObject json;
	private String filename;
	private PrintWriter writer;
	public JSONLevelBuilder (String filename) throws JSONException, FileNotFoundException, UnsupportedEncodingException {
		writer = new PrintWriter("dungeons/"+filename , "UTF-8");
		//writer.println("Hello");
		//json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));

	}

	public static void main(String[] args) throws JSONException, FileNotFoundException, UnsupportedEncodingException {
		JSONLevelBuilder jb = new JSONLevelBuilder("owen.json");
		jb.makeDungeonDimensions(16,16);
		jb.startEntityList();
		jb.makePlayer(1, 1);
		for (int i = 0; i < 10; i ++) {
			for (int j = 0; j < 10; j++) {
				if (i == 1 && j == 1) continue;
				jb.makePotion(i, j);
			}
		}
		jb.endEntityList();
		jb.startObjective();
		/*jb.startAndObjective();
		jb.makeExitObjective();
		jb.startOrObjective();
		jb.makeTreasureObjective();
		jb.makeEnemyObjective();
		jb.endObjective();
		jb.endObjective();*/
		jb.makeTreasureObjective();
		//jb.endObjective();
		jb.endDungeon();
		jb.closeWriter();
	}
	public void makeDungeonDimensions(int i, int j) {
		writer.write("{\n" + 
				"  \"width\": 18,\n" + 
				"  \"height\": 16,");
	}
	public void startEntityList() {
		writer.write("\"entities\": [");
	}
	public void endEntityList() {
		writer.write("],");
	}
	public void endDungeon() {
		writer.write("}");
	}

	public void closeWriter() {
		writer.close();
	}
	public void makePlayer(int x, int y) {
		writer.write("    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"player\"\n" + 
				"    },");
	}
	public void makeEnemy(int x, int y) {
		writer.write("    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"enemy\"\n" + 
				"    },");
	}
	public void makePotion(int x, int y) {
		writer.write("    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"invincibility\"\n" + 
				"    },");
	}
	public void makeWall(int x, int y) {
		writer.write("    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"wall\"\n" + 
				"    },");
	}
	public void makeBoulder(int x, int y) {
		writer.write("    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"boulder\"\n" + 
				"    },");
	}
	public void makeSwitch(int x, int y) {
		writer.write("    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"switch\"\n" + 
				"    },");
	}
	public void makeSword(int x, int y) {
		writer.write("    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"sword\"\n" + 
				"    },");
	}
	public void startObjective() {
		writer.write("\"goal-condition\":");
	}
	public void startAndObjective() {
		writer.write(" { \"goal\": \"AND\", \"subgoals\":\n" + 
				"  [ ");
	}
	public void startOrObjective() {
		writer.write(" { \"goal\": \"OR\", \"subgoals\":\n" + 
				"  [ ");
	}
	public void endObjective() {
		writer.write("]\n}");
	}
	public void makeEnemyObjective() {
		writer.write("{\"goal\": \"enemies\"},");
	}
	public void makeExitObjective() {
		writer.write("{\"goal\": \"exit\"},");
	}
	public void makeTreasureObjective() {
		writer.write("{\"goal\": \"treasure\"},");
	}
	public void makeBoulderObjective() {
		writer.write("{\"goal\": \"boulder\"},");
	}
	
}

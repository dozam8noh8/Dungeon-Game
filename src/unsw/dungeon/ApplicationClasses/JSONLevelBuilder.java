package unsw.dungeon.ApplicationClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONLevelBuilder {

	private JSONObject json;
	private String filename;
	private PrintWriter writer;
	private int Dwidth;
	private int Dheight;
	private String objString = "";
	public JSONLevelBuilder (String filename) throws JSONException, FileNotFoundException, UnsupportedEncodingException {
		this.filename = filename;
		makeWriter();


	}
	public void makeWriter() throws FileNotFoundException, UnsupportedEncodingException {
		writer = new PrintWriter("dungeons/"+ this.filename , "UTF-8");
	}
	public int getDungeonWidth() {
		return this.Dwidth;
	}
	public int getDungeonHeight() {
		return this.Dheight;
	}
	public void makeDefaultLevel() throws FileNotFoundException, UnsupportedEncodingException {
		closeWriter();
		makeWriter();
		makeDungeonDimensions(10,10);
		startEntityList();
		makePlayer(1, 1);
		endEntityList();
		startObjective();
		makeTreasureObjective();
		endDungeon();
		closeWriter();
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
		jb.deleteEntityOnSquare(3, 6);
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
	public void makeDungeonDimensions(int height, int width) {
		this.Dwidth = width;
		this.Dheight = height;
		
		writer.write("{\n" + 
				"  \"width\": "+ width + ",\n" + 
				"  \"height\": " + height + ",");
	}
	public void deleteEntityOnSquare(int x, int y) throws FileNotFoundException {
		writer.close();
		int lineNum = 0;
		File file = new File("dungeons/" + filename);
		//System.out.println(file.getAbsolutePath());
		Scanner scanner = new Scanner(file);
		System.out.println(filename);
		int endLineToDelete = 0;
		int startLineToDelete = 0;
		while (scanner.hasNextLine()) {
			//System.out.println("Scanning!");
			String line1 = scanner.nextLine();
			//System.out.println(line1);
			lineNum++;
			if (line1.contains("{")) {
				startLineToDelete = lineNum;
			}
			if (line1.contains("\"x\": "+x)) {
				System.out.println("Found x");
				if (scanner.hasNextLine()){
					String line2 = scanner.nextLine();
					lineNum++;
					if (line2.contains("\"y\": "+y)) {
						System.out.println("Found y");
						endLineToDelete = lineNum;
						while (scanner.hasNextLine()){
							String line3 = scanner.nextLine();
							endLineToDelete++;
							if (line3.contains("}")) {
								break;
							}
							
									
						}
						System.out.println("start = " +startLineToDelete);
						System.out.println("end = " +endLineToDelete);
						rewriteFileWithoutEntity(startLineToDelete, endLineToDelete);
					}
				}
			}
			
		}
		scanner.close();

		//writer = new PrintWriter("dungeons/" + filename);
	}
	public void rewriteFileWithoutEntity(int start, int end) throws FileNotFoundException {
		File file = new File ("dungeons/" + filename);
		Scanner scanner = new Scanner (file);
		try {
			PrintWriter p = new PrintWriter ("dungeons/" + filename + "tmp");
			int lineNum = 0;
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				System.out.println(line);
				lineNum++;
				if ((lineNum < start) || (lineNum > end)){
					p.println(line);
				}
			}
			p.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
		renameFile();
	}
	public void renameFile() {
		File file = new File ("dungeons/" + filename);
		File file2 = new File("dungeons/" + filename + "tmp");
		if (!file.delete()) {
			System.out.println("Couldnt delete file");
		}
		file2.renameTo(file);
	}
	public void startEntityList() {
		writer.write("\"entities\": [");
	}
	public void endEntityList() {
		writer.write("],\n");
	}
	public void endDungeon() {
		writer.write(objString);
		emptyObjString();
		writer.write("}");
	}
	public void emptyObjString() {
		this.objString = "";
	}
	public void closeWriter() {
		writer.close();
	}
	public void makePlayer(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"player\"\n" + 
				"    },");
	}
	public void makeExit(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"exit\"\n" + 
				"    },");
	}
	public void makeTreasure(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"treasure\"\n" + 
				"    },");
	}
	public void makeEnemy(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"enemy\"\n" + 
				"    },");
	}
	public void makePotion(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"invincibility\"\n" + 
				"    },");
	}
	public void makeWall(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"wall\"\n" + 
				"    },");
	}
	public void makeBoulder(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"boulder\"\n" + 
				"    },");
	}
	public void makeSwitch(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"switch\"\n" + 
				"    },");
	}
	public void makeSword(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"sword\"\n" + 
				"    },");
	}
	public void makeBomb(int x, int y) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x +",\n" + 
				"      \"y\": "+y +",\n" + 
				"      \"type\": \"bomb\"\n" + 
				"    },");		
	}
	public void makeDoor(int x, int y, int id) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x+ ",\n" + 
				"      \"y\": "+y+",\n" + 
				"      \"type\": \"door\",\n" + 
				"      \"id\" : "+id+"\n" + 
				"    },");
	}
	public void makeKey(int x, int y, int id) {
		writer.write("\n    {\n" + 
				"      \"x\": "+x+ ",\n" + 
				"      \"y\": "+y+",\n" + 
				"      \"type\": \"key\",\n" + 
				"      \"id\" : "+id+"\n" + 
				"    },");
	}
	public void startObjective() {
		//writer.write("\"goal-condition\":");
		objString += "\"goal-condition\":";
	}
	public void startAndObjective() {
		/*writer.write(" { \"goal\": \"AND\", \"subgoals\":\n" + 
				"  [ ");*/
		objString += " { \"goal\": \"AND\", \"subgoals\":\n" + 
				"  [ ";
	}
	public void startOrObjective() {
		/*writer.write(" { \"goal\": \"OR\", \"subgoals\":\n" + 
				"  [ ");*/
		objString += " { \"goal\": \"OR\", \"subgoals\":\n" + 
				"  [ ";
	}
	public void endObjective() {
		//writer.write("]\n}");
		objString += "]\n}";
	}
	public void makeEnemyObjective() {
		//writer.write("{\"goal\": \"enemies\"},");
		objString += "{\"goal\": \"enemies\"},";
	}
	public void makeExitObjective() {
		//writer.write("{\"goal\": \"exit\"},");
		objString += "{\"goal\": \"exit\"},";
	}
	public void makeTreasureObjective() {
		//writer.write("{\"goal\": \"treasure\"},");
		objString += "{\"goal\": \"treasure\"},";
	}
	public void makeBoulderObjective() {
		//writer.write("{\"goal\": \"boulders\"},");
		objString += "{\"goal\": \"boulders\"},";
	}

	
}

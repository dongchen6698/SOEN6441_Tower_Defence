package GameData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
/**
 * This class is contain the information of a map
 * @author peilin
 *
 */
public class MapData {
	
	private int gridRow ;
	private int gridCol ;

	public MapData(String map_path) throws FileNotFoundException{
		String  thisLine = null;
		int countrow = 0;
		int countcol = 0;
		try{
			BufferedReader br = new BufferedReader(new FileReader(map_path));
			while ((thisLine = br.readLine()) != null) {
			countrow++;
			countcol = thisLine.replace(" ", "").length();
			}
		gridRow = countrow;
		gridCol = countcol;
		}
		catch(Exception e){
		e.printStackTrace();
		}
	}
	
	public int getGridRow() {
		return gridRow;
	}
	public void setGridRow(int gridRow) {
		this.gridRow = gridRow;
	}
	public int getGridCol() {
		return gridCol;
	}
	public void setGridCol(int gridCol) {
		this.gridCol = gridCol;
	}

}
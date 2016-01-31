package GameData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MapData {
	
	private int gridrow ;
	private int gridcol ;
	/**
	 * 
	 * @param map_path
	 * @throws FileNotFoundException
	 */
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
		gridrow = countrow;
		gridcol = countcol;
		}
		catch(Exception e){
		e.printStackTrace();
		}
	}
	
	public int getGridRow() {
		return gridrow;
	}
	public void setGridRow(int gridRow) {
		this.gridrow = gridRow;
	}
	public int getGridCol() {
		return gridcol;
	}
	public void setGridCol(int gridCol) {
		this.gridcol = gridCol;
	}

}
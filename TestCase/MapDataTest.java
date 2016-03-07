package Testcase;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import GameData.MapData;;

public class MapDataTest {
	private String path;
	private MapData mapData;
	@Before
	public void beforetest(){
		path = "Maps/maps_1";
		try {
			mapData = new MapData(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMapData_testRow() {
		assertSame(7,mapData.getGridRow());
	}
	
	@Test
	public void testMapData_testCol() {
		assertSame(12,mapData.getGridCol());
	}

}

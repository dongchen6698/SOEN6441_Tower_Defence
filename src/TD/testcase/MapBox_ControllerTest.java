package TD.testcase;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import TD.controller.MapBox_Controller;
import TD.model.MapBox_Model;


public class MapBox_ControllerTest {

	MapBox_Controller mbcontroller;
	MapBox_Model mbModel;
	
	@Before                        
	public void setUp() {	
		mbcontroller = new MapBox_Controller();
		mbModel = new MapBox_Model();
		mbModel.setXBlockCount(5);
		mbModel.setYBlockCount(5);
		mbModel.setmapGirdArrayElement(1, 0, 7);
		mbModel.setmapGirdArrayElement(0, 4, 8);
		mbModel.setmapGirdArrayElement(1, 1, 1);
		mbModel.setmapGirdArrayElement(0, 1, 1);
		mbModel.setmapGirdArrayElement(0, 2, 1);
		mbModel.setmapGirdArrayElement(0, 3, 1);
	}
	
	@Test
	public void testsaveMap() {
		mbcontroller.saveMap("testsavemap");
		assertTrue(testfileishere());
	}
	
	public boolean testfileishere(){
		File file = new File("MapFiles/testsavemap.dat");
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}

}

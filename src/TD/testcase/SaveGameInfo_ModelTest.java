package TD.testcase;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import TD.model.SaveGameInfo_Model;
import TD.model.Serialization_model;

public class SaveGameInfo_ModelTest {
	static Serialization_model smodel = new Serialization_model();
	static SaveGameInfo_Model sgimodel = new SaveGameInfo_Model();
	
	@BeforeClass
	public static void beforclass(){
		smodel.Serialization_model_test(500, 0, 0, 6, 1, 1);
		sgimodel.SaveGameInfo_test(smodel);
	}
	
	@Test
	public void testSavedmoney() {
		assertTrue(sgimodel.smodel.money == 500);
	}
	
	@Test
	public void testSavedkilled() {
		assertTrue(sgimodel.smodel.killed == 0);
	}
	
	@Test
	public void testSavedtotal_killed() {
		assertTrue(sgimodel.smodel.total_killed == 0);
	}
	
	@Test
	public void testSavedhealth() {
		assertTrue(sgimodel.smodel.health == 6);
	}
	
	@Test
	public void testSavedlevel() {
		assertTrue(sgimodel.smodel.level == 1);
	}
	
	@Test
	public void testSavedwavelap() {
		assertTrue(sgimodel.smodel.wavelap == 1);
	}
	

}

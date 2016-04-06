package TD.testcase;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import TD.model.LoadGameInfo_Model;
import TD.model.Serialization_model;

public class LoadGameInfo_ModelTest {

	static LoadGameInfo_Model lgimodel = new LoadGameInfo_Model();
	static Serialization_model smodel = new Serialization_model();
	@BeforeClass
	public static void beforclass(){
		smodel = lgimodel.LoadGameInfoTest();
	}
	
	@Test
	public void testloadmoney() {
		assertEquals(500, smodel.money);
	}
	
	@Test
	public void testloadkilled() {
		assertEquals(0, smodel.killed);
	}
	
	@Test
	public void testloadtotalkilled() {
		assertEquals(0, smodel.total_killed);
	}
	
	@Test
	public void testloadhealth() {
		assertEquals(6, smodel.health);
	}
	
	@Test
	public void testloadwavelap() {
		assertEquals(1, smodel.wavelap);
	}
	
	@Test
	public void testloadlevel() {
		assertEquals(1, smodel.level);
	}



}

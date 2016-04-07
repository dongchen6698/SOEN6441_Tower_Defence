package TD.testcase;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import TD.controller.CellContainer_Controller;
import TD.model.CellContainer_Model;
import TD.model.Creature_Model;
import TD.model.GridCell_Model;
import TD.view.CellContainer_View;

public class GridCell_ModelTest {
	
	GridCell_Model gcmtest = new GridCell_Model();
	CellContainer_Model ccmtest = new CellContainer_Model(10, 10);
	CellContainer_Model ccmtest2 = new CellContainer_Model(10, 10);
	CellContainer_View ccvtest = new CellContainer_View();
	CellContainer_Controller ccctest = new CellContainer_Controller(ccvtest, ccmtest);
	Creature_Model cmtest = new Creature_Model(ccmtest, ccctest);
	Creature_Model[] cModel;
	@Test
	public void testfire() throws ParseException{
		gcmtest.physic(cModel);
		boolean fire = true;
		
		assertEquals(fire, gcmtest.isFire());
	}
	
	@Test
	public void testfreezen() throws ParseException{
		gcmtest.physic(cModel);
		boolean freezen = true;
		
		assertEquals(freezen, gcmtest.isFreeze());
	}
}

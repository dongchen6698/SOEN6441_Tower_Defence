package TD.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CellContainer_ModelTest.class, Creature_ModelTest.class, Creature_ViewTest.class, MapCreation_ModelTest.class, MapBox_ViewTest.class, MapCreation_ViewTest.class, PlayScreen_ViewTest.class, Shop_ControllerTest.class, MapValidationTest.class,SaveGameInfo_ModelTest.class,LoadGameInfo_ModelTest.class})
public class TDSuite {
  //nothing
}
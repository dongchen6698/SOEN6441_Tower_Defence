package TD.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import TD.config.ConfigModel;
import TD.view.GridCell_View;
import TD.view.PlayScreen_View;
import TowerDefenceGame.GamePlay;

public class LoadGameInfo_Model {
//	private File file;
//	private PlayScreen_Model psModel;
//	private GridCell_Model[][] gcModel;
//	private CellContainer_Model ccModel;
//	private Shop_Model sModel;
//	private ConfigModel cModel;

	
	public LoadGameInfo_Model(){
		//PlayScreen_Model psModel = null;
		//GridCell_View gcView = null;
		PlayScreen_View psView = null;
		
		System.out.println("start loadgame");
		try
	      {
	         FileInputStream fileIn = new FileInputStream("savegameinfo/savegameinfo.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         
	         psView = (PlayScreen_View) in.readObject();
	         
	         
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
		System.out.println("success reload");

	}
	
}

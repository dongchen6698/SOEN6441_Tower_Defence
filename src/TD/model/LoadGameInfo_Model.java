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
	
	public LoadGameInfo_Model(){

		Serialization_model slzModel = null;
		
		System.out.println("start loadgame");
		try
	      {
	         FileInputStream fileIn = new FileInputStream("savegameinfo/savegameinfo.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         
	         slzModel = (Serialization_model) in.readObject();
	         System.out.println(slzModel.money);
	         
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

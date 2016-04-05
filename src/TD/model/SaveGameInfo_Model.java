package TD.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import TD.config.ConfigModel;
import TD.view.CellContainer_View;
import TD.view.GridCell_View;
import TD.view.PlayScreen_View;
import TD.view.Shop_View;
import TowerDefenceGame.GamePlay;

public class SaveGameInfo_Model {
	
	public SaveGameInfo_Model(Serialization_model slzModel){

		try{
			 FileOutputStream fileOut = new FileOutputStream("savegameinfo/savegameinfo.ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         
			         out.writeObject(slzModel);
			        
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in savegameinfo/savegameinfo.ser");
			 }
			 catch(IOException i){
			         i.printStackTrace();
			 }
	}

}

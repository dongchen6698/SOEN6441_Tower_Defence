package TD.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import TD.config.ConfigModel;
import TD.view.CellContainer_View;
import TD.view.GridCell_View;
import TD.view.PlayScreen_View;
import TD.view.Shop_View;
import TowerDefenceGame.GamePlay;

/**
 * This is a model for save game information.
 * @author DanQiao
 *
 */
public class SaveGameInfo_Model extends JFrame{
	
	public Serialization_model smodel;
	
	/**
	 * this is a default constructor.
	 * 
	 */
	public SaveGameInfo_Model(){
		
	}
	
	/**
	 * This is constructor for save game information.
	 * @param slzModel
	 */	
	public SaveGameInfo_Model(Serialization_model slzModel){
		this.smodel = slzModel;

		try{
			 String str = JOptionPane.showInputDialog(this, "Enter File Name");
			 FileOutputStream fileOut = new FileOutputStream("savegameinfo/"+str+ ".ser");
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
	
	/**
	 * this is a method for test of save game information.
	 * @param slzModel
	 */
	public void SaveGameInfo_test(Serialization_model slzModel){
		this.smodel = slzModel;

		try{
			 //String str = JOptionPane.showInputDialog(this, "Enter File Name");
			 FileOutputStream fileOut = new FileOutputStream("savegameinfo/testsaveinfo.ser");
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

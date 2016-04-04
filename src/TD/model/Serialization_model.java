package TD.model;

import java.io.File;
import java.io.Serializable;

public class Serialization_model implements Serializable{
	public File file;
	public int money;
	
	public Serialization_model(File new_file,int new_money){
		this.file = new_file;
		this.money = new_money;
		new SaveGameInfo_Model(this);
	}
}

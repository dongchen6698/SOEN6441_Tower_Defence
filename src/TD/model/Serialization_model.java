package TD.model;

import java.io.File;
import java.io.Serializable;

import TD.view.GridCell_View;

/**
 * this class is serializing data.
 * @author peilin
 *
 */
public class Serialization_model implements Serializable{
	public File file;
	public int money;
	public int killed;
	public int total_killed;
	public int health;
	public int wavelap;
	public int level;
	public GridCell_Model[][] gcModel;
	public CellContainer_Model ccModel;
	public GridCell_View gcView;
	public PlayScreen_Model psModel;
	
	/**
	 * this is a default function of Serialization_model;
	 */
	public Serialization_model(){
		
	}
	
	/**
	 * this is constructor of the class
	 * @param new_file
	 * @param new_money
	 * @param killed
	 * @param total_killed
	 * @param health
	 * @param gcModel
	 * @param ccModel
	 */
	public Serialization_model(File new_file,int new_money, int killed, int total_killed, int health, int wavelap, int level, GridCell_Model[][] gcModel){
		this.file = new_file;
		this.money = new_money;
		this.killed = killed;
		this.total_killed = total_killed;
		this.health = health;
		this.wavelap = wavelap;
		this.level = level;
		this.gcModel = gcModel;
		new SaveGameInfo_Model(this);
	}
	
	/**
	 * this is a test of Serialization_model
	 * @param new_money
	 * @param killed
	 * @param total_killed
	 * @param health
	 * @param wavelap
	 * @param level
	 */
	public void Serialization_model_test(int new_money, int killed, int total_killed, int health, int wavelap, int level){
		this.money = new_money;
		this.killed = killed;
		this.total_killed = total_killed;
		this.health = health;
		this.wavelap = wavelap;
		this.level = level;
	}
}
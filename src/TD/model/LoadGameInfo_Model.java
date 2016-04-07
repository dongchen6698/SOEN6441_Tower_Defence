package TD.model;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import TD.config.ConfigModel;
import TD.controller.PlayScreen_Controller;
import TD.mapValidation.MapValidation;
import TD.view.CellContainer_View;
import TD.view.GridCell_View;
import TD.view.PlayScreen_View;
import TD.view.Shop_View;
import TowerDefenceGame.GamePlay;

/**
 * This is a Model for loading game information.
 * @author DanQiao
 *
 */

public class LoadGameInfo_Model extends GamePlay{	
	private File file;
	private ConfigModel cModel;
	private PlayScreen_Controller psCont;
    private Serialization_model slzModel;
    private PlayScreen_View psView;
    private PlayScreen_Model psModel;
    private GridCell_View gcView;
    private GridCell_Model[][] gcModel;
    private CellContainer_View ccView;
    private CellContainer_Model ccModel;
    private Shop_View sView;
    private Shop_Model sModel;
    
    /**
     * this is a default constructor 
     */
    public LoadGameInfo_Model(){
    	
    }
    
    /**
     * This method for loading game
     * @param loadgame
     */   
	public LoadGameInfo_Model(String loadgame){
		Serialization_model slzModel = null;
		
		System.out.println("start loadgame");
		try
	      {
	         FileInputStream fileIn = new FileInputStream("savegameinfo/" + loadgame);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         slzModel = (Serialization_model) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("class not found");
	         c.printStackTrace();
	         return;
	      }
		System.out.println("success reload");
		this.file = slzModel.file;
		this.cModel.money = slzModel.money;
		this.cModel.killed = slzModel.killed;
		this.cModel.total_killed = slzModel.total_killed;
		this.cModel.health = slzModel.health;
		this.cModel.waveLap = slzModel.wavelap;
		this.cModel.level = slzModel.level;
		this.gcModel = slzModel.gcModel;
		gamePlay(20,15);
	}
	/**
	 * This is a method for game play
	 * @param w width
	 * @param h height
	 */
	public void gamePlay(int w, int h)
    {
        int width = w*40 + 350;
        int height = h*40 + 100;
        this.setTitle("Tower Defence Game");
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.darkGray);
        init_elements();
        this.addWindowListener(this);
    }
	public void init_elements()
    {
        this.setLayout(new GridLayout(1, 1, 0, 0));
        PlayScreen_Model psModel = new PlayScreen_Model();
        this.psModel = psModel;
                        boolean temp = psModel.LoadMap(file);
                        if(temp){
                            MapValidation mv = new MapValidation(psModel.getGridCellArray());
                            if(mv.isValid()){
                                //System.out.println("Map is Valid");
                                ConfigModel cModel = new ConfigModel(); 
                                this.cModel = cModel;
                                
                                psModel.initCellContainerModel();
                                psModel.setGridCellVal();
                                
                                Shop_View sView = new Shop_View();
                                Shop_Model sModel = new Shop_Model(psModel.getStartX(),psModel.getStartY());
                                this.sView = sView;
                                this.sModel = sModel;
                                
                                CellContainer_View ccView = new CellContainer_View();
                                ccModel = psModel.getCellContainer_Model();
                                ccModel.setGcModel(gcModel);
                                this.ccView = ccView;
                                GridCell_View gcView = new GridCell_View();
                                this.gcView = gcView;
                                PlayScreen_View psView = new PlayScreen_View(this);
                                this.psView = psView;
                                this.add(psView);
                                psCont = new PlayScreen_Controller(psView, psModel, gcView, gcModel, ccView, ccModel, sView, sModel);
                                psView.setController(getPsCont());
                                psView.startGame();
                                this.setVisible(true);
                            }else{
                                //System.out.println("Map Is Invalid");
                                JOptionPane.showMessageDialog(this,"Map is Invalid", null, WIDTH);
                            }  
                        }else{
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "Invalid Map File", null, WIDTH);
                        }
    }
	
	/**
     * This method is closing game and save game
     */
    @Override
    public void windowClosing(WindowEvent e) {
    	String message = "Do you want to save game information?";
        String title = "Save Game?";
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION){
        	System.exit(0);
        }else if(reply == JOptionPane.YES_OPTION){        	
        	slzModel = new Serialization_model(this.file,ConfigModel.money, ConfigModel.killed, ConfigModel.total_killed, ConfigModel.health, ConfigModel.waveLap, ConfigModel.level, gcModel);
        	this.dispose();
        	System.exit(0);
        	
        }
    }

    /**
     * @return the psCont
     */
    public PlayScreen_Controller getPsCont() {
        return psCont;
    }
    
    
    /**
     * Load game info test function
     */
	public Serialization_model LoadGameInfoTest(){
		Serialization_model slzModel = null;
		System.out.println("start loadgame");
		try
	      {
	         FileInputStream fileIn = new FileInputStream("savegameinfo/testsaveinfo.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         slzModel = (Serialization_model) in.readObject();	         
	         in.close();
	         fileIn.close();	         
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("class not found");
	         c.printStackTrace();
	      }
		return slzModel;
	}
}
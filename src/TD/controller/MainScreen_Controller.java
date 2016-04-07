package TD.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import TD.config.ConfigModel;
import TD.model.ShowLog_Model;
import TD.model.LoadGameInfo_Model;
import TD.model.MainScreen_Model;
import TD.model.FileChooser_Model;
import TD.model.MapCreation_Model;
import TD.view.ShowLog_View;
import TD.view.MainScreen_View;
import TD.view.FileChooser_View;
import TD.view.MapCreation_View;
import TowerDefenceGame.LogGenerator;

/**
 * This Class will bind and initialize Model-View of Main Screen Module.
 * @author peilin
 */
public class MainScreen_Controller {
	MainScreen_View theView;
	MainScreen_Model theModel;
		
	/**
     * This method initialize view and model object.
     * @param view MainScreen_View object
     * @param model MainScreen_Model object 
     */
	public MainScreen_Controller(MainScreen_View view, MainScreen_Model model){
		this.theView = view;
		this.theModel = model;
		this.theView.addButtonClickListener(new ButtonActionDetector());
	}
	
	/**
     * This method will call setTopEnabled() from view class.
     */
    public void setTopEnabled(){
        theView.setTopEnabled();
    }
    
    /**
     * this is method for initializing map creation controller
     */
    public void initMapCreationg_Controller(){
    	MapCreation_View mcView = new MapCreation_View();
        MapCreation_Model mcModel = new MapCreation_Model();
        mcView.setVisible(true);
        theView.setAlwaysOnTop(false);
        mcView.setAlwaysOnTop(true);
        mcView.setFocusable(true);
        MapCreation_Controller theMapCreation = new MapCreation_Controller(mcView, mcModel, this);
        theMapCreation.startMapCreation();
    }
	
	/**
     * This class will perform action based on button pressed on Main Screen.
     */
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
            	if(tempBtnStr.equals("Creat Maps")){
                	initMapCreationg_Controller();
                }
            	
                if(tempBtnStr.equals("Start Game")){
                	LogGenerator.creatLogFile("game");
                	LogGenerator.addLogInfo("Global", "Global", "User clicked the start game");
                    FileChooser_Model mcModel = new FileChooser_Model("MapFiles/");
                    FileChooser_View mcView = new FileChooser_View(theView, mcModel.getMapFileList(),"Select Game Map.", "Start Game");
                    FileChooser_Controller mp = new FileChooser_Controller(mcView,mcModel);
                }
                
                if(tempBtnStr.equals("Load Game")){
                	FileChooser_Model mcModel = new FileChooser_Model("savegameinfo/");
                    FileChooser_View mcView = new FileChooser_View(theView, mcModel.getMapFileList(),"Load Game", "Load File");
                    FileChooser_Controller mp = new FileChooser_Controller(mcView,mcModel);
                }

                if(tempBtnStr.equals("EXIT GAME")){
                    theView.dispose();
                    System.exit(0);
                }
                
                if(tempBtnStr.equals("Show Log")){
                	FileChooser_Model mcModel = new FileChooser_Model("logfile/");
                    FileChooser_View mcView = new FileChooser_View(theView, mcModel.getMapFileList(),"Choose Log File", "See Log");
                    FileChooser_Controller mp = new FileChooser_Controller(mcView,mcModel);
                }
            }
        }
    }

}

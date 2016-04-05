package TD.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import TD.model.LoadGameInfo_Model;
import TD.model.LoadMapChooser_Model;
import TD.view.LoadMapChooser_View;
import TowerDefenceGame.GamePlay;

/**
 * This Class will bind and initialize Model-View of load map Module.
 * @author peilin
 */
public class LoadGame_Controller {
	LoadMapChooser_View theView = new LoadMapChooser_View();
	LoadMapChooser_Model theModel = new LoadMapChooser_Model();
    
    /**
     * This is constructor of Map Chooser.
     * @param mcView the MapChooserView
     * @param mcModel the MapChooserModel
     */
    LoadGame_Controller(LoadMapChooser_View lmcView, LoadMapChooser_Model lmcModel) {
        this.theView = lmcView;
        this.theModel = lmcModel;
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }
    
    /**
     * This method will call getMapFileList() from model.
     * @return the String array of MapFiles.
     */
    public String[] getMapFileList(){
        return theModel.getMapFileList();
    }
    
    /**
     * This class will perform action based on buttons pressed in Load map Chooser.
     */
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Start game")){
                    if(theView.getSelectedFile().equals("NONE")){
                        theView.displayMessage("Please Select At least one file.");
                    }else{
                    	String str = theView.getSelectedFile();                    
                    	LoadGameInfo_Model lgiModel = new LoadGameInfo_Model(str);
                    	theView.setMSTOp(false);
                    	theView.dispose();     
                        
                    }
                }
            }
        }
    }

}
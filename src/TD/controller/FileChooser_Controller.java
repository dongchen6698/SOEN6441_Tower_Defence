package TD.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

import TD.config.ConfigModel;
import TD.model.FileChooser_Model;
import TD.model.ShowLog_Model;
import TD.model.LoadGameInfo_Model;
import TD.model.PlayScreen_Model;
import TD.view.FileChooser_View;
import TD.view.ShowLog_View;
import TowerDefenceGame.GamePlay;
import TowerDefenceGame.LogGenerator;

/**
 * This Class will bind and initialize Model-View of Map Chooser Module.
 * @author peilin
 */
public class FileChooser_Controller {
    FileChooser_View theView;
    FileChooser_Model theModel;
    
    /**
     * This is default constructor of Map Chooser.
     * @param mcView the MapChooserView
     * @param mcModel the MapChooserModel
     */
    public FileChooser_Controller(FileChooser_View mcView, FileChooser_Model mcModel) {
        this.theView = mcView;
        this.theModel = mcModel;
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
     * This class will perform action based on buttons pressed in Map Chooser.
     */
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Start Game")){
                    if(theView.getSelectedFile().equals("NONE")){
                        theView.displayMessage("Please Select At least one file.");
                    }else{
                        PlayScreen_Model psModel = new PlayScreen_Model();
                        boolean temp = psModel.LoadMap(new File("MapFiles/"+theView.getSelectedFile()));
                        String[] mapname = theView.getSelectedFile().split("\\.");
                        File loadmap = new File("logfile/"+mapname[0]+".log");
                        String content = "is played";
                        LogGenerator.addMapLog(loadmap, mapname[0], content, ConfigModel.money);
                        LogGenerator.addLogInfo("Global", "Global", "User choose the Map file of "+ theView.getSelectedFile());
                        if(temp){
                            GamePlay gp = new GamePlay(new File("MapFiles/"+theView.getSelectedFile()), psModel.getxC(), psModel.getyC());
                            theView.setMSTOp(false);
                            theView.dispose();
                        }
                        else{
                            theView.displayMessage("Incorrect Map File");
                        }  
                    }
                }
                
                if(tempBtnStr.equals("Load File")){
                	 if(theView.getSelectedFile().equals("NONE")){
                         theView.displayMessage("Please Select At least one file.");
                     }else{
                     	String str = theView.getSelectedFile();                    
                     	LoadGameInfo_Model lgiModel = new LoadGameInfo_Model(str);
                     	theView.setMSTOp(false);
                     	theView.dispose();  
                     }
                }
                
                if(tempBtnStr.equals("See Log")){
               	 if(theView.getSelectedFile().equals("NONE")){
                        theView.displayMessage("Please Select At least one file.");
                    }else{
                    	String str = theView.getSelectedFile();   
                    	System.out.println(str);
                    	ShowLog_Model jsModel = new ShowLog_Model("logfile/"+str);
                    	ShowLog_View jsView = new ShowLog_View();
                		jsModel.addObserver(jsView);
                		ShowLog_Controller jsc = new ShowLog_Controller(jsView, jsModel,"All");
                    	theView.setMSTOp(false);
                    	theView.dispose();   
                    }
               } 
            }
        }
    }
}
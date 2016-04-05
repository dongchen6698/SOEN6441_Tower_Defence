package TowerDefenceGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import TD.config.ConfigModel;
import TD.controller.PlayScreen_Controller;
import TD.mapValidation.MapValidation;
import TD.model.CellContainer_Model;
import TD.model.Creature_Model;
import TD.model.GridCell_Model;
import TD.model.PlayScreen_Model;
import TD.model.SaveGameInfo_Model;
import TD.model.Serialization_model;
import TD.model.Shop_Model;
import TD.view.CellContainer_View;
import TD.view.Creature_View;
import TD.view.GridCell_View;
import TD.view.PlayScreen_View;
import TD.view.Shop_View;

/**
 * This class initialize the game play screen.
 * @author peilin
 */
public class GamePlay extends JFrame implements WindowListener{
    
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
    private ConfigModel cModel;
    private File file;
    
    /**
     * This method will initialize JFrame and set some basic properties(Title, Size, Background Color, Location).
     * @param f Map file which is selected by user from list box.
     * @param w Width of Play screen based on map size
     * @param h Height of Play screen based on map size
     */
    public GamePlay(){}
    public GamePlay(File file, int w, int h)
    {
        int width = w*40 + 350;
        int height = h*40 + 100;
        this.setTitle("Tower Defence Game");
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        //this.setAlwaysOnTop(true);
        this.file = file;
        this.setBackground(Color.darkGray);
        init_elements(file);
        this.addWindowListener(this);
    }
    
    /**
     * This method will check the Map file and based on the type of map, it will bind the model to the controller and create the play view using the View.
     * @param f Map file which is selected by user from list box.
     */
    public void init_elements(File file)
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
                                this.ccView = ccView;
                             //   this.ccModel = ccModel;

                                gcView = new GridCell_View();
                                gcModel = ccModel.getGcModel();
                              //  this.gcView = gcView;
                             //  this.gcModel = gcModel;

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
     * @return the psCont
     */
    public PlayScreen_Controller getPsCont() {
        return psCont;
    }

    @Override
    public void windowOpened(WindowEvent e) {
       
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
        	System.out.println("save game");
        	slzModel = new Serialization_model(this.file,ConfigModel.money, ConfigModel.killed, ConfigModel.total_killed, ConfigModel.health, ConfigModel.waveLap, ConfigModel.level, gcModel);
        	System.exit(0);
        	
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {
   
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
}
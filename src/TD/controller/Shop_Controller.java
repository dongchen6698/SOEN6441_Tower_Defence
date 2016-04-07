package TD.controller;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import TD.config.ConfigModel;
import TD.model.GridCell_Model;
import TD.model.Shop_Model;
import TD.view.Shop_View;
import TowerDefenceGame.LogGenerator;

/**
 * This Class will bind and initialize Model-View of Shop(Tower) Module.
 * @author DanQiao
 *
 */
public class Shop_Controller implements Observer{
    static CellContainer_Controller ccCont;
    static Shop_Model sModel;
    Shop_View sView;
    
    /**
     * This method is constructor for Shop(Tower)
     * @param sModel the shopModel
     * @param sView the shopView
     */
    Shop_Controller(Shop_Model sModel, Shop_View sView) {
        this.sModel = sModel;
        this.sView = sView;
    }

    /**
     * This is a public shop_Controller constructor.
     */
    public Shop_Controller() {
        
    }
    
    /**
     * This method will set CellContainerController object.
     * @param ccCont the CellContainerController
     */
    public void setccCont(CellContainer_Controller ccCont) {
        this.ccCont = ccCont;
    }
    
    /**
     * THis method will call draw method from draw().
     * @param sModel the showModel
     * @param g the Graphics
     */
    public void getshopDraw(Shop_Model sModel, Graphics g){
        this.sView.draw(sModel, g);
    }
    
    /**
     * This method will execute by user pressed button. It also used to place tower and delete tower.
     * @param button the mouse button id.
     */
    public static void click(int button) {
        
    }
    
    /**
     * Place tower function
     * @param y y Co-ordinate
     * @param x x Co-ordinate
     * @param priceID price of towerID
     * @return successFlag
     */
    public boolean placeTower(int y, int x, int priceID){
        if(ccCont.getgcModelObj(y, x).getgID() != 11 && ccCont.getgcModelObj(y, x).getgID() != ConfigModel.groundRoad && ccCont.getgcModelObj(y, x).getAirID() == ConfigModel.airAir){
            ccCont.getgcModelObj(y, x).setAirID(sModel.getHeldID());
            ConfigModel.money = ConfigModel.money - sModel.getButtonPrice(priceID);
            LogGenerator.addLogInfo("WAVE_"+Integer.toString(ConfigModel.waveLap), "Tower_"+Integer.toString(ccCont.getgcModelObj(y, x).getAirID()-2), " was placed ");
            ccCont.getgcModelObj(y, x).setTowerRange(sModel.getTowerID(), new Rectangle(ccCont.getgcModelObj(y, x).x - ((ConfigModel.airTowerRanger[sModel.getTowerID()])/2), ccCont.getgcModelObj(y, x).y - ((ConfigModel.airTowerRanger[sModel.getTowerID()])/2), ccCont.getgcModelObj(y, x).width + ConfigModel.airTowerRanger[sModel.getTowerID()], ccCont.getgcModelObj(y, x).height + ConfigModel.airTowerRanger[sModel.getTowerID()]));
            sModel.setHoldsItem(false);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Remove tower function
     * @param y y Co-ordinate
     * @param x x Co-ordinate
     * @return successFlag
     */
    public boolean removeTower(int y, int x){
        for(int i=0;i<ConfigModel.airTowerLaser.length;i++){
            if(ccCont.getgcModelObj(y, x).getAirID() == ConfigModel.airTowerLaser[i]){
                    ccCont.getgcModelObj(y, x).setFiring(false);
                    ccCont.getgcModelObj(y, x).setAirID(-1);
                    ccCont.getgcModelObj(y, x).setgID(0);
                    double refund_amount = sModel.getButtonPrice(i) * 0.8;
                    sModel.setMoney(ConfigModel.money + (int)refund_amount );
                    ConfigModel.money = ConfigModel.money + (int)refund_amount;
                    LogGenerator.addLogInfo("WAVE_"+Integer.toString(ConfigModel.waveLap), "Tower_"+Integer.toString(ccCont.getgcModelObj(y, x).getAirID()+2), " was sold ");
                    return true;
                }
        }
        return false;
    }
    
    /**
     * Is tower here function
     * @param y y Co-ordinate
     * @param x x Co-ordinate
     * @return successFlag
     */
    public boolean isTowerHere(int y, int x){
        for(int i=0;i<ConfigModel.airTowerLaser.length;i++){
            //System.out.println("Tower Id: "+ccCont.getgcModelObj(y, x).getAirID()+"-"+ConfigModel.airTowerLaser[i]);
            if(ccCont.getgcModelObj(y, x).getAirID() == ConfigModel.airTowerLaser[i]){
                //System.out.println("Tower Is Here");
                sModel.setTowerID(i);
                sModel.setTowerInfo(true);
                return true;
            } else {
                sModel.setTowerInfo(false);
            }
        }
        return false;
    }
    
    /**
     * Observer Update Method
     * @param o observer Object
     * @param arg arguments
     */
    public void update(Observable o, Object arg) {
         if (arg instanceof String) {
            int button = Integer.parseInt((String)arg);
            if(button == 1)
            {
                for(int i=0;i<sModel.getbuttonLength();i++){
                    if(sModel.getButtonObj(i).contains(ConfigModel.mse)){
                        if(sModel.getButtonId(i) != ConfigModel.airAir)
                        {
                            if(ConfigModel.money >= sModel.getButtonPrice(i)){
                                sModel.setHeldID(sModel.getButtonId(i));
                                sModel.setRealID(i);
                                sModel.setHoldsItem(true);
                            }
                        }
                    }
                }

                if(sModel.isHoldsItem()){
                    if(ConfigModel.money >= sModel.getButtonPrice(sModel.getRealID())){
                        for(int y=0;y<ccCont.getyC();y++){
                            for(int x=0;x<ccCont.getxC();x++){
                                if(ccCont.getgcModelObj(y, x).contains(ConfigModel.mse)){
                                    placeTower(y, x, sModel.getRealID());
                                }
                            }
                        }
                    }
                }
                        for(int y=0;y<ccCont.getyC();y++){
                            for(int x=0;x<ccCont.getxC();x++){
                                if(ccCont.getgcModelObj(y, x).contains(ConfigModel.mse)){
                                    isTowerHere(y, x);
                                }
                            }
                        }
            
                        
                        if(sModel.strategy1.contains(ConfigModel.mse)){
                            if(sModel.isTowerInfo()){
                            	GridCell_Model.strategyno=1;    
                            }                
                        }            
                        
                        if(sModel.strategy2.contains(ConfigModel.mse)){
                            if(sModel.isTowerInfo()){
                            	GridCell_Model.strategyno=2;  
                            }                
                        }       
                        if(sModel.strategy3.contains(ConfigModel.mse)){
                            if(sModel.isTowerInfo()){
                            	GridCell_Model.strategyno=3;   
                            }                
                        }       
                        if(sModel.strategy4.contains(ConfigModel.mse)){
                            if(sModel.isTowerInfo()){
                            	GridCell_Model.strategyno=4;
                            }                
                        }  
                        
                        if(sModel.showTowerLog.contains(ConfigModel.mse)){
                        	if(sModel.isTowerInfo()){
                        		System.out.println(sModel.getTowerID());
                        		switch(sModel.getTowerID()){
                        		case 0:
                        			
                        		case 1:
                        			
                        		case 2:
                        			
                        		case 3:
                        			
                        		
               		
                        		
                        		}
                        	}
                        }
                         
            if(sModel.towerUpgrade.contains(ConfigModel.mse)){
                if(sModel.isTowerInfo()){
                    //System.out.println("Tower Info");
                    if(ConfigModel.TowerLevel[sModel.getTowerID()] < 5)
                    {
                        if(ConfigModel.money >= ConfigModel.TowerPrice[sModel.getTowerID()])
                        {
                            //System.out.println("Tower Upgrade: "+sModel.getTowerID());
                            ConfigModel.TowerFiringRate[sModel.getTowerID()] += 1;
                            ConfigModel.airTowerRanger[sModel.getTowerID()] += 20;
                            ConfigModel.TowerLevel[sModel.getTowerID()]++;
                            ConfigModel.money -= ConfigModel.TowerPrice[sModel.getTowerID()];
                            
                            for(int y=0;y<ccCont.getyC();y++)
                            {
                                for(int x=0;x<ccCont.getxC();x++)
                                {
                                    if(ccCont.getgcModelObj(y, x).getAirID() == (sModel.getTowerID()+3))
                                    {
                                        ccCont.getgcModelObj(y, x).setTowerRange(sModel.getTowerID(), new Rectangle(ccCont.getgcModelObj(y, x).x - ((ConfigModel.airTowerRanger[sModel.getTowerID()])/2), ccCont.getgcModelObj(y, x).y - ((ConfigModel.airTowerRanger[sModel.getTowerID()])/2), ccCont.getgcModelObj(y, x).width + ConfigModel.airTowerRanger[sModel.getTowerID()], ccCont.getgcModelObj(y, x).height + ConfigModel.airTowerRanger[sModel.getTowerID()]));
                                    }
                                }
                            }
                        }
                    }
                }                
            }
                        
            }else if(button == 0 && sModel.isHoldsItem()){
                sModel.setHoldsItem(false);
            } else if(button == 0 && !sModel.isHoldsItem()){
                    for(int y=0;y<ccCont.getyC();y++){
                            for(int x=0;x<ccCont.getxC();x++){
                                if(ccCont.getgcModelObj(y, x).contains(ConfigModel.mse)){
                                    removeTower(y, x);
                                }
                            }
                        }
            }
        }
    }    
}

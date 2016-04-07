package TD.model;

import java.awt.Rectangle;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import TD.config.ConfigModel;
import TD.view.PlayScreen_View;
import TowerDefenceGame.LogGenerator;
import strategy_pattern.Context;
import strategy_pattern.NearToEnd;
import strategy_pattern.NearestToTower;
import strategy_pattern.Strongest;
import strategy_pattern.Weakest;

/**
 * This is model for Grid Cells Module.
 * @author peilin
 */
public class GridCell_Model extends Rectangle{
    public static int strategyno;
    private Rectangle[] towerRange;
    public String[] towerLog;
    public int[] towerActiveTime;
    public int[] towerCKilled;
    private int towerRangeSize = 100;
    private int gID;
    private int airID;
    private int loseTime = 100, loseFrame = 0;
    //public int towerid
	private int shotMob = -1;
    private int[] MobList = new int[100];
    private boolean freeze = false;
    private boolean fire = false;
    private boolean firing = false;
    private int xC=0,yC=0;
    private String startTime;
    private boolean startFlag = false;
    private String endTime;
    public static int creature_id=0;
    public static int creature_y=0;
    public static int creature_x=0;
    public static int creature_yprev=0;
    public static int creature_xprev=0;
    public static int difference=0;
    
   /**
    * this is constructor of the class 
    */
   public  GridCell_Model(){
    	
    }
    /**
     * * This is constructor method for Grid Cell. It will set different properties for each grid cell.
     * @param x x point
     * @param y y point
     * @param width width of cell
     * @param height height of cell
     * @param gId ground id of cell
     * @param airId  air id of cell
     */
    GridCell_Model(int x, int y, int width, int height, int gId, int airId) {
        Arrays.fill(MobList, 0);
        setBounds(x, y, width, height);
        towerRange = new Rectangle[ConfigModel.airTowerLaser.length];
        //towerLog = new String[ConfigModel.airTowerLaser.length];
        towerActiveTime = new int[ConfigModel.airTowerLaser.length];
        towerCKilled = new int[ConfigModel.airTowerLaser.length];
        for(int i=0;i<ConfigModel.airTowerLaser.length;i++)
        {
            towerRange[i] = new Rectangle(x - ((ConfigModel.airTowerRanger[i])/2), y - ((ConfigModel.airTowerRanger[i])/2), width + ConfigModel.airTowerRanger[i], height + ConfigModel.airTowerRanger[i]);
            towerActiveTime[i] = 0;
            towerCKilled[i] = 0;
        }
        this.gID = gID;
        this.airID = airID;   
    }
    
    /**
     * This is physic function for Grid Cell.
     * @param cModel
     * @throws ParseException
     */
    public void physic(Creature_Model[] cModel) throws ParseException{
        
        for(int i=0;i<ConfigModel.airTowerLaser.length;i++)
        {
                if(getShotMob() != -1 && getTowerRange()[gID].intersects(cModel[getShotMob()])){
                }
                else{
                    setFiring(false);
                }
        }
        
        for(int tid=0;tid<ConfigModel.airTowerLaser.length;tid++){
        	if(getAirID() == ConfigModel.airTowerLaser[tid]){
        		for(int i=0;i<cModel.length;i++){
        			if(cModel[i].isInGame()){
        				if(getTowerRange()[tid].intersects(cModel[i])){
        					if(shotMob!=i) {
        						if(shotMob!=-1){	
                    	//System.out.println("Shot Mob "+cModel[shotMob].getHealth() + " other than shotmob" + cModel[i].getHealth() );
                        
                    	//this.airID tower id
                    	//cModel[shotMob] creature id	
                    	//ConfigModel.waveLap  
                    	//LogGenerator.addLogInfo(Integer.toString(this.airID), Integer.toString(shotMob),Integer.toString(ConfigModel.waveLap), "Tower_"+(this.airID-2)+" attacked creatur"+shotMob);	
                    	//	
        							switch(strategyno){
        							case 1://nearest to the tower
        								
        								Context context = new Context(new NearestToTower());
        								shotMob=context.killCreateure(cModel, shotMob, i, this.y);
        								
        								break;
        							case 2://weakest
        								Context context1 = new Context(new Weakest());
        								shotMob=context1.killCreateure(cModel, shotMob, i, this.y);
        								
        								break;
        							case 3://strongest
        								Context context2 = new Context(new Strongest());
        								shotMob=context2.killCreateure(cModel, shotMob, i, this.y);
        								
        								break;
        							case 4://near to tht end
        								Context context3 = new Context(new NearToEnd());
        								shotMob=context3.killCreateure(cModel, shotMob, i, this.y);
        								
        								break;
        							default:
                    		//near to tht end
        								if(shotMob<i){
        									shotMob=i;	
        								}	
        							}
        						}
        					}  
        				}
        			}
        		}
        	}
        }
        
        if(!isFiring()){
            for(int tid=0;tid<ConfigModel.airTowerLaser.length;tid++){
            	if(airID == ConfigModel.airTowerLaser[tid]){
            		//System.out.println("Tower Id "+airID);
            		for(int i=0;i<cModel.length;i++){
                        if(cModel[i].isInGame()){
                            if(getTowerRange()[tid].intersects(cModel[i]))
                            {
//                                if(i == 0){
//                                	setFiring(true);
//                                	shotMob = i;
//                                }
                                //System.out.println(i);
                               
                            	setFiring(true);
                                shotMob = i;
                                if(!startFlag){
                                    startTime = getCurrentTime();
                                    //System.out.println("Start");
                                    startFlag = true;
                                } else
                                    endTime();
                            }
                        }
                    }
                }
            }
        }
        
        if(isFiring() && getAirID() != -1){
            if(loseFrame >= loseTime){
            	if(getAirID() == 3){
            		cModel[getShotMob()].loseHealth(ConfigModel.TowerFiringRate[0]);
            		LogGenerator.addLogInfo("WAVE_"+Integer.toString(ConfigModel.waveLap), "Tower_1", " attack creature." + this.getShotMob() );
            	}else if(getAirID() == 4){
            		cModel[getShotMob()].setFire(true);
            		for(int i=0;i<cModel.length;i++){
            			if(cModel[i].isInGame()){
            				if(cModel[i].isFire()){
            						cModel[i].loseHealth(1);
            						LogGenerator.addLogInfo("WAVE_"+Integer.toString(ConfigModel.waveLap), "Tower_2", " attack creature." + this.getShotMob());
            						this.setFire(true);
            				}
            				if(!getTowerRange()[1].contains(cModel[i])){	
            					cModel[i].setFire(false);
            				}
                    	}
            		}
            		
            		
            	}else if(getAirID() == 5){
            		cModel[getShotMob()].walkSpeed = 30;
            		cModel[getShotMob()].loseHealth(ConfigModel.TowerFiringRate[2]);
            		for(int i=0;i<cModel.length;i++){
            			if(cModel[i].isInGame()){
            				LogGenerator.addLogInfo("WAVE_"+Integer.toString(ConfigModel.waveLap), "Tower_3", " attack creature." + this.getShotMob());
            				this.setFreeze(true);
            				if(!getTowerRange()[2].contains(cModel[i])){
            					cModel[i].walkSpeed = ConfigModel.walkSpeed;
            				}
                    	}
            		}
            	}else if(getAirID() == 6){
            		LogGenerator.addLogInfo("WAVE_"+Integer.toString(ConfigModel.waveLap), "Tower_4", " attack creature." + this.getShotMob());
            		if(getShotMob()==0){
            			cModel[getShotMob()].loseHealth(ConfigModel.TowerFiringRate[3]);
                		cModel[getShotMob()+1].loseHealth(ConfigModel.TowerFiringRate[1]);
            		}else if(getShotMob()==(cModel.length-1)){
            			if(cModel[getShotMob()-1].checkDeath()){
            				cModel[getShotMob()].loseHealth(ConfigModel.TowerFiringRate[3]);
            			}else{
            			cModel[getShotMob()-1].loseHealth(ConfigModel.TowerFiringRate[1]);
                		cModel[getShotMob()].loseHealth(ConfigModel.TowerFiringRate[3]);
            			}
            		}else{
            			if(cModel[getShotMob()-1].checkDeath()){
            				cModel[getShotMob()].loseHealth(ConfigModel.TowerFiringRate[3]);
            				cModel[getShotMob()+1].loseHealth(ConfigModel.TowerFiringRate[1]);
            				}else{
            					cModel[getShotMob()-1].loseHealth(ConfigModel.TowerFiringRate[1]);
            					cModel[getShotMob()].loseHealth(ConfigModel.TowerFiringRate[3]);
            					cModel[getShotMob()+1].loseHealth(ConfigModel.TowerFiringRate[1]);
            				}
            		}
            	}
            	loseFrame = 0;
            }else {
                loseFrame +=1;
            }
            if(cModel[getShotMob()].isDead()){
                towerCKilled[getAirID()-3]++;
                setFiring(false);
                shotMob = -1;
                PlayScreen_View.hasWon();
            }
        }
        
        if(!isFiring() && startFlag && getAirID() != -1){
                //System.out.println("End");
                endTime();
            }
    }
    
    /**
     * this is a method to measure end time 
     * @throws ParseException
     */
    public void endTime() throws ParseException
    {
        endTime = getCurrentTime();
                //System.out.println("sT: "+startTime);
                //System.out.println("eT: "+endTime);
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date date1 = format.parse(startTime);
                    Date date2 = format.parse(endTime);
                    long difference = date2.getTime() - date1.getTime();
                    //System.out.println("diff: "+(long)difference);
                    towerActiveTime[getAirID()-3] += difference;
                startFlag = false;
    }

    /**This is a method for set gID.
     * @param gID the gID to set
     */
    public void setgID(int gID) {
        this.gID = gID;
    }

    /**This is a method for set AirID.
     * @param airID the airID to set
     */
    public void setAirID(int airID) {
        this.airID = airID;
    }

    /**This is a method for get gID.
     * @return the gID
     */
    public int getgID() {
        return gID;
    }

    /**This is a method for get AirID.
     * @return the airID
     */
    public int getAirID() {
        return airID;
    }

    /**This is a method for get the range of tower.
     * @return the towerRange
     * @param x x index
     */
    public Rectangle getTowerRange(int x) {
        return getTowerRange()[x];
    }
    
    /**
     * This is a method for get money.
     * @param mobID
     */
    public void getMoney(int mobID){
        //System.out.println("Money Is increased");
        ConfigModel.money += ConfigModel.deathReward[0];
    }

    /**This is a method for get shot mob.
     * @return the shotMob
     */
    public int getShotMob() {
        return shotMob;
    }

    /**This is a method for firing .
     * @return the firing
     */
    public boolean isFiring() {
        return firing;
    }

    /**This is a method for set firing.
     * @param firing the firing to set
     */
    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    /**This is a method for get the range of tower.
     * @return the towerRange
     */
    public Rectangle[] getTowerRange() {
        return towerRange;
    }

    /**This is a method for set the range of tower.
     * @param towerRange the towerRange to set
     * @param i the towerRange ID
     */
    public void setTowerRange(int i,Rectangle towerRange) {
        this.towerRange[i] = towerRange;
    }
    
    /**
     * this method is get current time
     * @return string
     */
    public String getCurrentTime(){
        Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	return sdf.format(cal.getTime());
    }
    /**
     * This is a method for confirm the status of freeze.
     * @return
     */
	public boolean isFreeze() {
		freeze =true;
		return freeze;
	}
	
	/**
	 * This is a method for set freeze.
	 * @param freeze
	 */
	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}
	/**
	 * This is a method for confirm the status of fire.
	 * @return
	 */
	public boolean isFire() {
		fire = true;
		return fire;
	}
	/**
	 * This is a method for set fire.
	 * @param fire
	 */
	public void setFire(boolean fire) {
		this.fire = fire;
	}
 
}

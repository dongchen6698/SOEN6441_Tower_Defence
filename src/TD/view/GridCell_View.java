package TD.view;

import java.awt.Color;
import java.awt.Graphics;

import TD.config.ConfigModel;
import TD.model.GridCell_Model;

/**
 * This is GUI class of Grid Cell Module.
 * @author peilin
 */
public class GridCell_View {
    
    /**
     * This is constructor.
     */
    public GridCell_View(){
        
    }
    
    /**
     * This method will draw GUI Components.
     * @param g the Graphics
     * @param gcModel the model object
     */
    public void draw(GridCell_Model gcModel,Graphics g){
           g.drawImage(ConfigModel.ground_level[gcModel.getgID()], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
        
        if(gcModel.getAirID() != ConfigModel.airAir && (gcModel.getAirID() ==8)){
           g.drawImage(ConfigModel.air_level[0], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
       }
        
        if(gcModel.getAirID() != ConfigModel.airAir && (gcModel.getAirID() == 7)){
           g.drawImage(ConfigModel.air_level[1], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
       }
        
        if(gcModel.getAirID() != ConfigModel.airAir){
           g.drawImage(ConfigModel.air_level[gcModel.getAirID()], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
       }
    }
    
    /**
     * This method will draw GUI Components.
     * @param g the Graphics
     * @param gcModel the model object
     */
    public void fireRangeOutline(GridCell_Model gcModel,Graphics g){
        if(true){
        	//draw the range of the each tower.
            for(int i=0;i<ConfigModel.airTowerLaser.length;i++){
                if(gcModel.getAirID() == ConfigModel.airTowerLaser[i]){
                    g.setColor(Color.white);
                    g.drawRect(gcModel.getTowerRange(i).x, gcModel.getTowerRange(i).y, gcModel.getTowerRange(i).width, gcModel.getTowerRange(i).height);
                }
            }
            g.setColor(Color.white);
        }
        if(gcModel != null && gcModel.isFiring() && gcModel.getAirID() != -1){
        	//draw the special image to the critters.
            if(gcModel.getAirID() == 4){
                //g.drawImage(ConfigModel.fire[0], PlayScreen_View.Creatures[gcModel.getShotMob()].x, PlayScreen_View.Creatures[gcModel.getShotMob()].y, gcModel.width, gcModel.height, null);
            } else if(gcModel.getAirID() == 5){
            	//g.drawImage(ConfigModel.ice[0], PlayScreen_View.Creatures[gcModel.getShotMob()].x, PlayScreen_View.Creatures[gcModel.getShotMob()].y, gcModel.width, gcModel.height, null);
            } else if(gcModel.getAirID() == 6){
                g.drawImage(ConfigModel.star[0], PlayScreen_View.Creatures[gcModel.getShotMob()].x, PlayScreen_View.Creatures[gcModel.getShotMob()].y, gcModel.width, gcModel.height, null);
            }
            
            //set the fire line color depends on the tower.
            if(gcModel.getAirID() == 3){
            	g.setColor(Color.YELLOW);
            } else if(gcModel.getAirID() == 4){
                g.setColor(Color.GREEN);
            } else if(gcModel.getAirID() == 5){
                g.setColor(Color.BLUE);
            } else if(gcModel.getAirID() == 6){
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
               g.drawLine(gcModel.x + (gcModel.width/2)+1, gcModel.y + (gcModel.height/2)+1, PlayScreen_View.Creatures[gcModel.getShotMob()].x + (PlayScreen_View.Creatures[gcModel.getShotMob()].width/2) + 1, PlayScreen_View.Creatures[gcModel.getShotMob()].y + (PlayScreen_View.Creatures[gcModel.getShotMob()].height/2) + 1);
               g.drawLine(gcModel.x + (gcModel.width/2), gcModel.y + (gcModel.height/2), PlayScreen_View.Creatures[gcModel.getShotMob()].x + (PlayScreen_View.Creatures[gcModel.getShotMob()].width/2), PlayScreen_View.Creatures[gcModel.getShotMob()].y + (PlayScreen_View.Creatures[gcModel.getShotMob()].height/2));
        }
    }
}

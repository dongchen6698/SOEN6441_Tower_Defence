package TowerDefenceGame;

import TD.config.ConfigModel;
import TD.model.Creature_Model;
/**
 * This is a Factory for creating creatures based on different wave of the game
 * @author peilin
 *
 */
public class CreatureFactory {
	/**
	 * this is a static method for creating creatures
	 * @param wave
	 * @return
	 */
	   public static Creature_Model[] getCreature(int wave){
		   switch(wave){
		   case 1:	
			   return new Creature_Model[ConfigModel.creaturesNo];
		   case 2:
			   ConfigModel.creaturesNo=ConfigModel.creaturesNo+5;
			   return new Creature_Model[ConfigModel.creaturesNo];
		   case 3:
			   ConfigModel.creaturesNo=ConfigModel.creaturesNo+5; 
			   return new Creature_Model[ConfigModel.creaturesNo];
		   default: return null;
		   }
	/*	   if(wave == 1){
	         return new Creature_Model[ConfigModel.creaturesNo];
	      }	else if(wave == 2){
	    	 ConfigModel.creaturesNo=ConfigModel.creaturesNo+5;
	    	 return new Creature_Model[ConfigModel.creaturesNo];
	      } else if(wave == 3){
	    	  ConfigModel.creaturesNo=ConfigModel.creaturesNo+5;
	    	  return new Creature_Model[ConfigModel.creaturesNo];
	      } 
	      return null;
	      */
	   }
	}
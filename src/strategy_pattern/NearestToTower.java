package strategy_pattern;

import TD.model.Creature_Model;
/**
 * this class is one of Strategy pattern
 * @author peilin
 *
 */
public class NearestToTower implements strategyInterface{
	
	/**
	 * this is one of method of strategy
	 */
	@Override
	public int killCreateure(Creature_Model[] cModel, int shotMob,int i,int towery) 
	{
		int diff1 = towery - cModel[shotMob].y;
		int diff2 = towery - cModel[i].y;
		if(Math.abs(diff2)<Math.abs(diff1)){
			shotMob=i;
		}
		
		return shotMob;
	}
	   

}

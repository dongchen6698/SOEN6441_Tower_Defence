package strategy_pattern;

import TD.model.Creature_Model;
/**
 * this class is one of Strategy pattern
 * @author peilin
 *
 */
public class Strongest  implements strategyInterface{
	
	/**
	 * this is one of method of strategy
	 */
	@Override
	public int killCreateure(Creature_Model[] cModel, int shotmob,int i,int towery) 
	{
		if(cModel[shotmob].getHealth()>cModel[i].getHealth()){
			//shotMob=i;	
		}else{
			shotmob=i;
		}
		
		return shotmob;
	}
}

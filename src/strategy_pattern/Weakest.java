package strategy_pattern;

import TD.model.Creature_Model;

/**
 * this class is one of strategy pattern
 * @author peilin
 *
 */
public class Weakest  implements strategyInterface{

	/**
	 * this is one of method of strategy
	 */
	@Override
	public int killCreateure(Creature_Model[] cModel, int shotMob,int i,int towery) 
	{
		if(cModel[shotMob].getHealth()<cModel[i].getHealth()){
			//shotMob=i;	
		}else{
			shotMob=i;
		}
		
		return shotMob;
	}

}

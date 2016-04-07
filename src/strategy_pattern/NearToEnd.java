package strategy_pattern;

import TD.model.Creature_Model;

/**
 *	this class is one of Strategy pattern
 * @author peilin
 *
 */
public class NearToEnd  implements strategyInterface
{
	/**
	 * this is one of method of strategy
	 */
	@Override
	public int killCreateure(Creature_Model[] cModel1, int shotmob,int i,int towery) 
	{
		if(shotmob<i)
		{
			shotmob=i;	
		}
		
		return shotmob;
	}

}

package strategy_pattern;

import TD.model.Creature_Model;

public class NearToEnd  implements strategyInterface
{

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

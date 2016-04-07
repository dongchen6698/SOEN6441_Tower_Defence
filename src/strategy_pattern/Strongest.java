package strategy_pattern;

import TD.model.Creature_Model;

public class Strongest  implements strategyInterface{

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

package strategy_pattern;

import TD.model.Creature_Model;

public class Weakest  implements strategyInterface{

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

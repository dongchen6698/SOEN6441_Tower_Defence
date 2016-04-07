package strategy_pattern;

import TD.model.Creature_Model;

public class Context 
{
	private strategyInterface strategy;

	   public Context(strategyInterface strategy)
	   {
	      this.strategy = strategy;
	   }

	   public int killCreateure(Creature_Model[] cModel1, int shotmob,int i,int towery)
	   {
	      return strategy.killCreateure(cModel1, shotmob, i, towery);
	   }
	   
}

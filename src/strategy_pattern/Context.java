package strategy_pattern;

import TD.model.Creature_Model;

/**
 * this is a class about strategy pattern
 * @author PEILIN
 *
 */
public class Context {
	private strategyInterface strategy;	
	/**
	 * this is a constructor of the class 
	 * @param strategy
	 */
	public Context(strategyInterface strategy)
	   {
	      this.strategy = strategy;
	   }
	/**
	 * this method is use different strategy to kill creatures
	 * @param cModel1
	 * @param shotmob
	 * @param i
	 * @param towery
	 * @return
	 */
    public int killCreateure(Creature_Model[] cModel1, int shotmob,int i,int towery)
	   {
	      return strategy.killCreateure(cModel1, shotmob, i, towery);
	   }
	   
}

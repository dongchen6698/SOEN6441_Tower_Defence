package Model;

/**
 * A subclass of Tower
 * A type of a tower 
 * @author peilin
 *
 */
public class IceTower extends Tower {
	
	/**
	 * A constructor of IceTower
	 * @param x			the position of x-axis of the tower
	 * @param y		    the position of y-axis of the tower
	 * @param size		the size of the tower
	 */
	public IceTower(int x, int y, int size) {
		int[] powers = { 25, 50, 75, 100, 125, 150 };
		int[] fightNums = { 1, 1, 2, 2, 3, 3 };
		int[] ranges = {150, 200, 250, 300, 350, 400};
		this.setPowers(powers);
		this.setFightNums(fightNums);
		this.setRanges(ranges);
		this.setLevel(1);
		this.setType(0);
		this.setX(x);
		this.setY(y);
		this.setSpeed(1000);
		this.setSpecialEffects("Stun");
		this.setPrice(100);
		setPower_FinghtNum_Range();
	}
}

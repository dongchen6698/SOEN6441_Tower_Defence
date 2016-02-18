package Model;

/**
 * A subclass of Tower
 * A type of a tower 
 * @author peilin
 *
 */
public class ElectricTower extends Tower {
	
	/**
	 * A constructor of ElectricTower
	 * @param x			the position of x-axis of the tower
	 * @param y		    the position of y-axis of the tower
	 * @param size		the size of the tower
	 */
	public ElectricTower(int x, int y, int size) {
		int[] powers = { 30, 60, 90, 120, 150, 180 };
		int[] fightNums = { 1, 1, 2, 2, 3, 3 };
		int[] ranges = {200, 250, 300, 350, 400, 450};
		this.setPowers(powers);
		this.setFightNums(fightNums);
		this.setRanges(ranges);
		this.setLevel(1);
		this.setType(2);
		this.setX(x);
		this.setY(y);
		this.setSpeed(1000);
		this.setSpecialEffects("Long_range");
		this.setPrice(200);
		setPower_FinghtNum_Range();
	}
}

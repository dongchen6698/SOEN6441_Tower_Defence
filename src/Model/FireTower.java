package Model;

public class FireTower extends Tower {
	
	public FireTower(int x, int y, int size) {
		int[] powers = { 30, 60, 90, 120, 150, 180 };
		int[] fightNums = { 1, 1, 2, 2, 3, 3 };
		int[] ranges = {150, 200, 250, 300, 350, 400};
		this.setPowers(powers);
		this.setFightNums(fightNums);
		this.setRanges(ranges);
		this.setLevel(1);
		this.setType(1);
		this.setX(x);
		this.setY(y);
		this.setSpeed(1000);
		this.setSpecial_effects("Big_power");
		//this.setData(data);
		//this.setFighterList(data.getFighterList());
		this.setPrice(150);
		setPowerAndFinghtNum();
	}
}

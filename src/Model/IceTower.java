package Model;

public class IceTower extends Tower {
	
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
		this.setSpecial_effects("Stun");
		//this.setData(data);
		//this.setFighterList(data.getFighterList());
		this.setPrice(100);
		setPowerAndFinghtNum();
	}
}

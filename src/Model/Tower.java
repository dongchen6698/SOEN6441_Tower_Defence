package Model;

/**
 * This class contain the information of towers
 * 
 * @author peilin
 *
 */
public class Tower {

	private int x;				// the position of X-axis of the tower

	private int y;				// the position of Y-axis of the tower

	private int type;			// the type of tower

	private int power;

	private int level;
	
	private int range;

	private int bulletType;

	private int fightNum;

	private int speed;
	
    private int price;
    
	private int[] powers;

	private int[] fightNums;
	
	private int[] ranges;
	
	private boolean enable ;
	
	private boolean life;
	
	private String specialEffects;
	
	public Tower(){
		enable = true;
		life = true;
	}
	
	/**
	 * A method player can update tower until level 6
	 */
	public void levelUp(){
		if (level < 6) {
			level++;
			enable = true;
			setPower_FinghtNum_Range();
		}
	}
	
	public void setPower_FinghtNum_Range(){
		this.power = powers[level-1];
		this.fightNum = fightNums[level-1];
		this.range = ranges[level-1];
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBulletType() {
		return bulletType;
	}

	public void setBulletType(int bulletType) {
		this.bulletType = bulletType;
	}

	public int getFightNum() {
		return fightNum;
	}

	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int[] getPowers() {
		return powers;
	}

	public void setPowers(int[] powers) {
		this.powers = powers;
	}

	public int[] getFightNums() {
		return fightNums;
	}

	public void setFightNums(int[] fightNums) {
		this.fightNums = fightNums;
	}
	
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public boolean isEnable() {
		return enable;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public boolean isLife() {
		return life;
	}
	
	public void setLife(boolean life) {
		this.life = life;
	}
	
	public String getSpecialEffects() {
		return specialEffects;
	}

	public void setSpecialEffects(String specialEffects) {
		this.specialEffects = specialEffects;
	}

	public int[] getRanges() {
		return ranges;
	}

	public void setRanges(int[] range) {
		this.ranges = range;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

}

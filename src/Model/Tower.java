package Model;

public class Tower {

	private int x;

	private int y;

	private int type;

	private int power;

	private int level;

	private int bulletType;

	private int fightNum;

	private int speed;
	
    private int price;
    
	private int[] powers;

	private int[] fightNums;
	
	private boolean enable ;
	
	private boolean life;

	public Tower(){
		enable = true;
		life = true;
	}
	
	public void levelUp(){
		if (level < 6) {
			level++;
			enable = true;
			setPowerAndFinghtNum();
		}
	}
	
	public void setPowerAndFinghtNum(){
		this.power = powers[level-1];
		this.fightNum = fightNums[level-1];
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @param power
	 *            the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the bulletType
	 */
	public int getBulletType() {
		return bulletType;
	}

	/**
	 * @param bulletType
	 *            the bulletType to set
	 */
	public void setBulletType(int bulletType) {
		this.bulletType = bulletType;
	}

	/**
	 * @return the fightNum
	 */
	public int getFightNum() {
		return fightNum;
	}

	/**
	 * @param fightNum
	 *            the fightNum to set
	 */
	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the powers
	 */
	public int[] getPowers() {
		return powers;
	}

	/**
	 * @param powers the powers to set
	 */
	public void setPowers(int[] powers) {
		this.powers = powers;
	}

	/**
	 * @return the fightNums
	 */
	public int[] getFightNums() {
		return fightNums;
	}

	/**
	 * @param fightNums the fightNums to set
	 */
	public void setFightNums(int[] fightNums) {
		this.fightNums = fightNums;
	}
	
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public boolean isEnable() {
		// TODO Auto-generated method stub
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

}

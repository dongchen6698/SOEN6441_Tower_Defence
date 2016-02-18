package Util;
/**
 * This class is point the position of the tower on the toolbar
 * @author peilin
 *
 */
public class Point {

	private int x;			// the position of x-axis

	private int y;			// the position of y-axis
	
	/**
	 * A constructor of Point
	 * @param x	the position of x-axis
	 * @param y the position of y-axis
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
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
}

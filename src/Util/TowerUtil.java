package Util;
/**
 * This class is setting up the price of towers
 * @author peilin
 *
 */
public class TowerUtil {
	
	public static int getPriceByTowerType(int type) {
		int price = 0;
		if (type == 0) {
			price = 100;
		}
		if (type == 1) {
			price = 150;
		}
		if (type == 2) {
			price = 200;
		}
		if (type == 3) {
			price = 250;
		}
		return price;
	}

}

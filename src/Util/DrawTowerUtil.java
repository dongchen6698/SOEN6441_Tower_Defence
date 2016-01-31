package Util;

import java.awt.Color;
import java.awt.Graphics;

public class DrawTowerUtil {

	public static void drawTowerByType(int type, int level, Graphics g2, int x,
			int y, int size) {
		if (type == 0) {
			g2.setColor(Color.blue);
			g2.fillRect(x, y,size,size);
		}
		if (type == 1) {
			g2.setColor(Color.red);
			g2.fillRect(x, y,size,size);
		}
		if (type == 2) {
			g2.setColor(Color.yellow);
			g2.fillRect(x, y,size,size);
		}
		if (type == 3) {
			g2.setColor(Color.green);
			g2.fillRect(x, y,size,size);
		}
	}

}

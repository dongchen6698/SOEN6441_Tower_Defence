package Util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import Model.Tower;

/**
 * This class is draw towers what is look like
 * @author peilin
 *
 */
public class DrawTowerUtil {

	public static void drawTowerByType(int type, int level, Graphics g2, int x,
			int y, int size) {
		
		if (type == 0) {
		    Image image = Toolkit.getDefaultToolkit().getImage("Resource/Tower/I_tower.png");
			g2.drawImage(image, x, y, 50, 50, null);
		}

		if (type == 1) {
		    Image image1 = Toolkit.getDefaultToolkit().getImage("Resource/Tower/F_tower.png");
			g2.drawImage(image1, x, y, 50, 50, null);
		}

		if (type == 2) {
		    Image image2 = Toolkit.getDefaultToolkit().getImage("Resource/Tower/E_tower.png");
			g2.drawImage(image2, x, y, 50, 50, null);
		}

		if (type == 3) {
		    Image image1 = Toolkit.getDefaultToolkit().getImage("Resource/Tower/W_tower.png");
			g2.drawImage(image1, x, y, 50, 50, null);	
		}
	}
	public static void drawTowerLife(Graphics g, Tower tower) {
		if (!tower.isEnable()) {
			
			g.setColor(Color.red);
			g.fillRect(tower.getX(), tower.getY(), 50, 5);
			g.setColor(Color.green);
			
		}
	}
}

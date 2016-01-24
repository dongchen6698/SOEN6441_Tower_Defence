package BaseGameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class CreatMapScreen extends JPanel implements Runnable, MouseMotionListener, MouseListener{
	private int maprow;
	private int mapcol;
	private int focusX = -100;
	private int focusY = -100;
	private int clickfocusX = -100;
	private int clickfocusY = -100;
	int count = 1 ;
	public MainScreen mainscreen;
	Thread creatmapscreen = new Thread(this);
	
	public CreatMapScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		this.setBackground(Color.pink);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		creatmapscreen.start();
	}
	
	public void paintComponent(Graphics g) {

		if(count == 1){
		g.clearRect(0, 0, StaticGameInfo.frameWidth, StaticGameInfo.frameHeight);
		count++;
		}
		g.setColor(Color.BLACK);
		for(int x = 0;x < mapcol;x++){
			for(int y = 0;y < maprow;y++){
				g.drawRect(StaticGameInfo.gridSize + (x*StaticGameInfo.gridSize), StaticGameInfo.gridSize + (y*StaticGameInfo.gridSize), StaticGameInfo.gridSize, StaticGameInfo.gridSize);
			}		
		}
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
		g.setColor(Color.BLUE);
		g.fillRect(clickfocusX, clickfocusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
	}
	
	@Override
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public int getMaprow() {
		return maprow;
	}

	public void setMaprow(int maprow) {
		this.maprow = maprow;
	}

	public int getMapcol() {
		return mapcol;
	}
	
	public void setMapcol(int mapcol) {
		this.mapcol = mapcol;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > StaticGameInfo.gameLocationX && x < StaticGameInfo.gameLocationX + mapcol*StaticGameInfo.gridSize && y > StaticGameInfo.gameLocationY
				&& y < StaticGameInfo.gameLocationY + maprow*StaticGameInfo.gridSize) {
			focusX = (x - StaticGameInfo.gameLocationX) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
			focusY = (y - StaticGameInfo.gameLocationY) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
		} else {
			focusX = -100;
			focusY = -100;
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			clickfocusX = focusX;
			clickfocusY = focusY;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	
	

}

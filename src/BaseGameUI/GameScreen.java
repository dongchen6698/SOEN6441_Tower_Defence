package BaseGameUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;


public class GameScreen extends JPanel implements Runnable, MouseMotionListener{
	public MainScreen mainscreen;
	Thread baseScreenThread = new Thread(this);
	public int focusX;
	public int focusY;
	
	public GameScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		this.addMouseMotionListener(this);
		baseScreenThread.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, StaticGameInfo.frameWidth, StaticGameInfo.frameHeight);
		g.setColor(Color.BLACK);
		for(int x = 0;x < 14;x++){
			for(int y = 0;y < 7;y++){
				g.drawRect(StaticGameInfo.gridSize + (x*StaticGameInfo.gridSize), StaticGameInfo.gridSize + (y*StaticGameInfo.gridSize), StaticGameInfo.gridSize, StaticGameInfo.gridSize);
			}			
		}
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
	}

	@Override
	public void run() {
		
		while(true){
			repaint();
			try {
				baseScreenThread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > StaticGameInfo.gameLocationX && x < StaticGameInfo.gameLocationX + 14*StaticGameInfo.gridSize && y > StaticGameInfo.gameLocationY
				&& y < StaticGameInfo.gameLocationY + 7*StaticGameInfo.gridSize) {
			focusX = (x - StaticGameInfo.gameLocationX) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
			focusY = (y - StaticGameInfo.gameLocationY) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
		} else {
			focusX = -100;
			focusY = -100;
		}
		
	}
	
}

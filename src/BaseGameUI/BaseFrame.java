package BaseGameUI;

import java.awt.Color;

import javax.swing.JFrame;

import GameData.StaticGameInfo;
/**
 * This class is a first main window of the game
 * 
 * @author peilin
 *
 */
public class BaseFrame extends JFrame{
	
	public BaseFrame(){
		this.setSize(StaticGameInfo.FRAME_WIDTH,StaticGameInfo.FRAME_HEIGHT);
		this.setTitle("Tower Defence for SOEN6441");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.orange);
		this.setBackground(Color.GRAY);
		init();
	}

	public void init(){
		this.add(new MainScreen());
		this.setVisible(true);
	}

}

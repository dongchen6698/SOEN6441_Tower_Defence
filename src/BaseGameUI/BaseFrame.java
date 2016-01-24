package BaseGameUI;
import javax.swing.JFrame;

import GameData.StaticGameInfo;

public class BaseFrame extends JFrame{
	
	private boolean checkFirstTime;
	
	public BaseFrame(){
		this.setSize(StaticGameInfo.frameWidth,StaticGameInfo.frameHeight);
		this.setTitle("Tower Defence for SOEN6441");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		init();
	
	}
	
	public void init(){
		this.add(new MainScreen());
		this.setVisible(true);
	}

}

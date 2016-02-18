package BaseGameUI;

import java.awt.GridLayout;

import javax.swing.JPanel;
/**
 * This class extends JPanel 
 * @author peilin
 *
 */
public class MainScreen extends JPanel{
	
	public MainScreen(){
		this.setLayout(new GridLayout(1, 1));
		this.add(new StartScreen(this));
	}
}

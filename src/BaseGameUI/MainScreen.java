package BaseGameUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MainScreen extends JPanel{
	
	public MainScreen(){
		this.setLayout(new GridLayout(1, 1));
		this.add(new StartScreen(this));
	}
}

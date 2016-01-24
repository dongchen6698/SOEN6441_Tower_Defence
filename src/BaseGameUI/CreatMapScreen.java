package BaseGameUI;

import java.awt.Color;

import javax.swing.JPanel;

public class CreatMapScreen extends JPanel{
	public int maprow;
	public int mapcol;
	public MainScreen mainscreen;
	
	public CreatMapScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		this.setBackground(Color.pink);
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

	
	

}

package Model;

import java.awt.Color;
import java.awt.Graphics;

import BaseGameUI.GameScreen;
import GameData.StaticGameInfo;

public class Ball extends Fighter{
	public Ball(int x, int y,GameScreen GS) {
		super(x, y,GS);
	}

	@Override
	public void draw(Graphics g) {	
		g.setColor(Color.pink);
		g.fillOval(y, x, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
	}
}

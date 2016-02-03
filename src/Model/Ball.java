package Model;

import java.awt.Color;
import java.awt.Graphics;

import BaseGameUI.GameScreen;
import GameData.StaticGameInfo;

public class Ball extends Fighter{
	
	public Ball(int x, int y,GameScreen gamescreen) {
		super(x, y,gamescreen);
	}

	@Override
	public void draw(Graphics g) {	
		g.setColor(Color.WHITE);
		g.fillOval(y, x, StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
	}
}

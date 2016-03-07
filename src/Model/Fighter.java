package Model;

import java.awt.Graphics;

import BaseGameUI.GameScreen;
import GameData.StaticGameInfo;

public abstract class Fighter {
	
	public int x = 0;
	public int y = 0;
	public int futureX;
	public int futureY;
	public int life;
	public int nowLife;
	public int speed=5;
	public int direction;
	private int path[][];
	
	public Fighter(int x, int y,GameScreen gamescreen) {
		this.x = x * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X;
		this.y = y * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X;
		this.futureX = this.x;
		this.futureY = this.y;
		this.path = gamescreen.getPath();
		for(int i=0;i<path.length;i++){
			for(int j=0;j<path[i].length;j++){
				if(path[i][j]==2&&i!=0&&j!=0){        // find entry coordinator 
					if(path[i-1][j]==1)
						direction =1 ;
					if(path[i+1][j]==1)
						direction = 2;
					if(path[i][j-1]==1)
						direction =  3;
					if(path[i][j+1]==1)
						direction = 4 ;
				} else if(path[i][j]==2&&j==0&&i!=0) {
					if(path[i-1][j]==1)
						direction =1 ;
					if(path[i+1][j]==1)
						direction = 2;
					if(path[i][j+1]==1)
						direction = 4 ;	
				}else if (path[i][j]==2&&i==0&&j!=0){
					if(path[i+1][j]==1)
						direction = 2;
					if(path[i][j-1]==1)
						direction =  3;
					if(path[i][j+1]==1)
						direction = 4 ;
				}else if (path[i][j]==2&&i==0&&j==0){
					if(path[i+1][j]==1)
						direction = 2;
					if(path[i][j+1]==1)
						direction = 4 ;
				}
			}
		}
	}
	
	public abstract void draw(Graphics g);
	
	public void move() {
		if (direction == 1) {
			x-=speed;
		}
		if (direction == 2) {
			x+=speed;
		}
		if (direction == 3) {
			y-=speed;
		}
		if (direction == 4) {
			y+=speed;
		}
	}
	
	public boolean isNeedMove() {
		if (x != futureX || y != futureY) {
			return true;
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getFutureX() {
		return futureX;
	}

	public void setFutureX(int futureX) {
		this.futureX = futureX;
	}

	public int getFutureY() {
		return futureY;
	}

	public void setFutureY(int futureY) {
		this.futureY = futureY;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getNowLife() {
		return nowLife;
	}

	public void setNowLife(int nowLife) {
		this.nowLife = nowLife;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}

package FighterThread;

import java.util.List;

import BaseGameUI.GameScreen;
import GameData.StaticGameInfo;
import Model.Fighter;

public class FighterThread implements Runnable{
	private GameScreen gamescreen;
	private List<Fighter> fighterList;
	private int path[][];
	
	public FighterThread(GameScreen GS){
		this.gamescreen = GS;
		this.path = GS.getPath();
		this.fighterList = GS.getFighterList();
	}

	@Override
	public void run() {
		try {
			while (true) {
				for (int i = 0; i < fighterList.size(); i++) {
					Fighter fighter = fighterList.get(i);
					if (fighter.isNeedMove()) {
						fighter.move();
					} else {
						getFuturePoint(fighter);
							fighter.move();	
					}
					if(
						(fighter.getX()==StaticGameInfo.GAMELOCATION_X+StaticGameInfo.GRID_SIZE*gamescreen.getEndX() 
						&& fighter.getY()==StaticGameInfo.GAMELOCATION_X+StaticGameInfo.GRID_SIZE*gamescreen.getEndY())
						||	
					   (fighter.getX()<StaticGameInfo.GAMELOCATION_X || 
						fighter.getX()>StaticGameInfo.GAMELOCATION_X + StaticGameInfo.GRID_SIZE*gamescreen.getMap_row()|| 
						fighter.getY()<StaticGameInfo.GAMELOCATION_Y || 
						fighter.getY() > StaticGameInfo.GAMELOCATION_Y + StaticGameInfo.GRID_SIZE*gamescreen.getMap_col())
							){
						fighterList.remove(i);
						i--;
					}
				}
				Thread.sleep(20);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getFuturePoint(Fighter f) {
		int x = (f.getX() - StaticGameInfo.GAMELOCATION_X) / StaticGameInfo.GRID_SIZE;
		int y = (f.getY() - StaticGameInfo.GAMELOCATION_Y) / StaticGameInfo.GRID_SIZE;
		int dir = f.getDirection();
		if (dir == 1) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < gamescreen.getMap_row() && y < gamescreen.getMap_col()) {
				if (path[x - 1][y] == 1 || path[x - 1][y] == 3) {
					f.setFutureX((x - 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY(y * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(1);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < gamescreen.getMap_row() && y - 1 < gamescreen.getMap_col()) {
				if (path[x][y - 1] == 1 || path[x][y - 1] == 3) {
					f.setFutureX(x * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY((y - 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(3);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < gamescreen.getMap_row() && y + 1 < gamescreen.getMap_col()) {
				if (path[x][y + 1] == 1 || path[x][y + 1] == 3) {
					f.setFutureX(x * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY((y + 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(4);
				}
			}
		} else if (dir == 2) {
			if (x + 1 >= 0 && y >= 0 && x + 1 < gamescreen.getMap_row() && y < gamescreen.getMap_col()) {
				if (path[x + 1][y] == 1 || path[x + 1][y] == 3) {
					f.setFutureX((x + 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY(y * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < gamescreen.getMap_row() && y - 1 < gamescreen.getMap_col()) {
				if (path[x][y - 1] == 1 || path[x][y - 1] == 3) {
					f.setFutureX(x * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY((y - 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(3);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < gamescreen.getMap_row() && y + 1 < gamescreen.getMap_col()) {
				if (path[x][y + 1] == 1 || path[x][y + 1] == 3) {
					f.setFutureX(x * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY((y + 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(4);
				}
			}
		} else if (dir == 3) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < gamescreen.getMap_row() && y < gamescreen.getMap_col()) {
				if (path[x - 1][y] == 1 || path[x - 1][y] == 3) {
					f.setFutureX((x - 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY(y * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(1);
				}
			}
			if (x + 1 >= 0 && y >= 0 && x + 1 < gamescreen.getMap_row() && y < gamescreen.getMap_col()) {
				if (path[x + 1][y] == 1 ||  path[x + 1][y] == 3) {
					f.setFutureX((x + 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY(y * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < gamescreen.getMap_row() && y - 1 < gamescreen.getMap_col()) {
				if (path[x][y - 1] == 1 || path[x][y - 1] == 3) {
					f.setFutureX(x * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY((y - 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(3);
				}
			}
		} else if (dir == 4) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < gamescreen.getMap_row() && y < gamescreen.getMap_col()) {
				if (path[x - 1][y] == 1 || path[x - 1][y] == 3) {
					f.setFutureX((x - 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY(y * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(1);
				}
			}
			if (x + 1 >= 0 && y >= 0 && x + 1 < gamescreen.getMap_row() && y < gamescreen.getMap_col()) {
				if (path[x + 1][y] == 1 || path[x + 1][y] == 3) {
					f.setFutureX((x + 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY(y * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < gamescreen.getMap_row() && y + 1 < gamescreen.getMap_col()) {
				if (path[x][y + 1] == 1 || path[x][y + 1] == 3) {
					f.setFutureX(x * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X);
					f.setFutureY((y + 1) * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_Y);
					f.setDirection(4);
				}
			}
		}
	}

}

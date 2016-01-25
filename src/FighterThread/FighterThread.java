package FighterThread;

import java.util.List;

import BaseGameUI.GameScreen;
import GameData.StaticGameInfo;
import Model.Fighter;

public class FighterThread implements Runnable{
	private List<Fighter> fighterList;
	private int path[][];
	
	public FighterThread(GameScreen GS){
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
					if(fighter.getX()<StaticGameInfo.gameLocationX || 
					   fighter.getX()>StaticGameInfo.gameLocationX + StaticGameInfo.gridSize*6 || 
					   fighter.getY()<StaticGameInfo.gameLocationY || 
					   fighter.getY() > StaticGameInfo.gameLocationY + StaticGameInfo.gridSize* 11){
						System.out.println("remove");
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
		int x = (f.getX() - StaticGameInfo.gameLocationX) / StaticGameInfo.gridSize;
		int y = (f.getY() - StaticGameInfo.gameLocationY) / StaticGameInfo.gridSize;
		int dir = f.getDirection();
		if (dir == 1) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < 7 && y < 12) {
				if (path[x - 1][y] == 1) {
					f.setFutureX((x - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(1);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < 7 && y - 1 < 12) {
				if (path[x][y - 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(3);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < 7 && y + 1 < 12) {
				if (path[x][y + 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(4);
				}
			}
			// if (f.getX() == f.getFutureX() && f.getY() == f.getFutureY()) {
			// f.setFutureX((x - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
			// f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
			// f.setDirection(1);
			// }
		} else if (dir == 2) {
			if (x + 1 >= 0 && y >= 0 && x + 1 < 7 && y < 12) {
				if (path[x + 1][y] == 1) {
					f.setFutureX((x + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < 7 && y - 1 < 12) {
				if (path[x][y - 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(3);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < 7 && y + 1 < 12) {
				if (path[x][y + 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(4);
				}
			}
			// if (f.getX() == f.getFutureX() && f.getY() == f.getFutureY()) {
			// f.setFutureX((x + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
			// f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
			// f.setDirection(2);
			// }
		} else if (dir == 3) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < 7 && y < 12) {
				if (path[x - 1][y] == 1) {
					f.setFutureX((x - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(1);
				}
			}
			if (x + 1 >= 0 && y >= 0 && x + 1 < 7 && y < 12) {
				if (path[x + 1][y] == 1) {
					f.setFutureX((x + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < 7 && y - 1 < 12) {
				if (path[x][y - 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(3);
				}
			}
			// if (f.getX() == f.getFutureX() && f.getY() == f.getFutureY()) {
			// f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
			// f.setFutureY((y - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
			// f.setDirection(3);
			// }
		} else if (dir == 4) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < 7 && y < 12) {
				if (path[x - 1][y] == 1) {
					f.setFutureX((x - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(1);
				}
			}
			if (x + 1 >= 0 && y >= 0 && x + 1 < 7 && y < 12) {
				if (path[x + 1][y] == 1) {
					f.setFutureX((x + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < 7 && y + 1 < 12) {
				if (path[x][y + 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(4);
				}
			}
			// if (f.getX() == f.getFutureX() && f.getY() == f.getFutureY()) {
			// f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
			// f.setFutureY((y + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
			// f.setDirection(4);
			// }
		}
	}

}

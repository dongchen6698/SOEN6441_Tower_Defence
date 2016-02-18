package MapVerification;

import javax.swing.JOptionPane;
/**
 * This class is verify the path of a map whether it is available
 * @author peilin
 *
 */
public class MapVerification {
	
	boolean state = true;			// a state check the map whether is available
	int numEntryPoint = 0;
	int numPath = 0;
	int numExitPoint = 0;	
	int numFindpath = 0;			// find the direction of the path 1 means up,  2 means down, 3 means left, 4 means right
	
	public MapVerification(int[][] creatmappath){
		
		for(int i=0;i<creatmappath.length;i++){
			for(int j=0;j<creatmappath[i].length;j++){
				if(creatmappath[i][j] == 1){
					numPath = numPath + 1;
				}
				if(creatmappath[i][j] == 2){
					numEntryPoint = numEntryPoint + 1;
				}
				if(creatmappath[i][j] == 3){
					numExitPoint = numExitPoint + 1;
				}
			}
		}
		
		if(numEntryPoint!=1){
			if(numEntryPoint == 0){
				JOptionPane.showMessageDialog(null, "At least have one EntryPoint!!");
				state = false;
			}else{
				JOptionPane.showMessageDialog(null, "EntryPoint can only have one!!");
				state = false;
			}
		}
		
		if(numExitPoint!=1){
			if(numExitPoint ==0){
				JOptionPane.showMessageDialog(null, "At least have one ExitPoint!!");
				state = false;
			}else{
			JOptionPane.showMessageDialog(null, "ExitPoint can only have one!!");
			state = false;
			}
		}
		
		if(numPath < 1){
			JOptionPane.showMessageDialog(null, "Path need at least have one!!");
			state = false;
		}
		
		for(int i=0;i<creatmappath.length;i++){
			for(int j=0;j<creatmappath[i].length;j++){
				if(creatmappath[i][j] == 2){
				if(i!=0 && j!=0 && i!=creatmappath.length-1 && j!=creatmappath[i].length-1){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}					
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "The path only can have one way!!");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
							return;
						}
					}
				if(i == 0 && j == 0){
						if(creatmappath[i][j+1] == 1){
						numFindpath = numFindpath + 1;
						}
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "The path can have one way!!");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
							return;
						}
					}
				if(i == 0 && j == creatmappath[i].length-1){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "The path can have one way!!");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
							return;
						}
					}
				if(i == creatmappath.length-1 && j == 0){
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "The path can have one way!!");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
							return;
						}
					}
				if(i == creatmappath.length-1 && j == creatmappath[i].length-1){
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "The path can have one way!!");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
							return;
						}				
					}
				
				if(i == 0 && j != 0 && j!=creatmappath[i].length-1){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "The path can have one way!!");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
							return;
						}		
					}
				
				if(i != 0 && j == 0 && i!=creatmappath.length-1){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "The path can have one way!!");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
							return;
						}		
					}
				
				if(i == creatmappath.length-1 && j != 0 && j!= creatmappath[i].length-1){
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "The path can have one way!!");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
							return;
						}		
					}
				
				if(i != 0 && j == creatmappath[i].length-1 && i!= creatmappath.length-1){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "The path can have one way!!");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
							return;
						}		
					}
					numFindpath = 0;
				}
				
			if(creatmappath[i][j] == 3){
				if(i!=0 && j!=0 && i!=creatmappath.length-1 && j!=creatmappath[i].length-1 && i!=creatmappath.length-1 && j!=creatmappath[i].length){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}					
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "Only one way can get through the Exitpoint");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
							return;
						}
					}
				if(i == 0 && j == 0){
						if(creatmappath[i][j+1] == 1){
						numFindpath = numFindpath + 1;
						}
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "Only one way can get through the Exitpoint");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
							return;
						}
					}
				if(i == 0 && j == creatmappath[i].length-1){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "Only one way can get through the Exitpoint");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
							return;
						}
					}
				if(i == creatmappath.length-1 && j == 0){
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "Only one way can get through the Exitpoint");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
							return;
						}
					}
				if(i == creatmappath.length-1 && j == creatmappath[i].length-1){
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "Only one way can get through the Exitpoint");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
							return;
						}				
					}
					
				if(i == 0 && j != 0 && j!=creatmappath[i].length-1){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "Only one way can get through the Exitpoint");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
							return;
						}	
					}
					
				if(i != 0 && j == 0 && i!=creatmappath.length-1){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "Only one way can get through the Exitpoint");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
							return;
						}		
					}
					
				if(i == creatmappath.length-1 && j != 0 && j!= creatmappath[i].length-1){
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "Only one way can get through the Exitpoint");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
							return;
						}		
					}
					
				if(i != 0 && j == creatmappath[i].length-1 && i!= creatmappath.length-1){
						if(creatmappath[i+1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i-1][j] == 1){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 1){
							JOptionPane.showMessageDialog(null, "Only one way can get through the Exitpoint");
							state = false;
							return;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
							return;
						}	
					}
					numFindpath = 0;
				}
				
			if(creatmappath[i][j] == 1){
				if(i!=0 && j!=0 && i!=creatmappath.length-1 && j!=creatmappath[i].length-1){
						if(creatmappath[i+1][j] == 1 || creatmappath[i+1][j] == 2 || creatmappath[i+1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i-1][j] == 1 || creatmappath[i-1][j] == 2 || creatmappath[i-1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1 || creatmappath[i][j-1] == 2 || creatmappath[i][j-1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1 || creatmappath[i][j+1] == 2 || creatmappath[i][j+1] == 3){
							numFindpath = numFindpath + 1;
						}					
						if(numFindpath > 2){
							JOptionPane.showMessageDialog(null, "Only can have one direction!!");
							state = false;
							return;
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
							return;
						}
					}
				if(i == 0 && j == 0){
						if(creatmappath[i+1][j] == 1 || creatmappath[i+1][j] == 2 || creatmappath[i+1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1 || creatmappath[i][j+1] == 2 || creatmappath[i][j+1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 2){
							JOptionPane.showMessageDialog(null, "Only can have one direction!!");
							state = false;
							return;
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
							return;
						}
					}
				if(i == 0 && j == creatmappath[i].length-1){
						if(creatmappath[i+1][j] == 1 || creatmappath[i+1][j] == 2 || creatmappath[i+1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1 || creatmappath[i][j-1] == 2 || creatmappath[i][j-1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 2){
							JOptionPane.showMessageDialog(null, "Only can have one direction!!");
							state = false;
							return;
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
							return;
						}
					}
				if(i == creatmappath.length-1 && j == 0){
						if(creatmappath[i-1][j] == 1 || creatmappath[i-1][j] == 2 || creatmappath[i-1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1 || creatmappath[i][j+1] == 2 || creatmappath[i][j+1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 2){
							JOptionPane.showMessageDialog(null, "Only can have one direction!!");
							state = false;
							return;
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
							return;
						}
					}
				if(i == creatmappath.length-1 && j == creatmappath[i].length-1){
						if(creatmappath[i-1][j] == 1 || creatmappath[i-1][j] == 2 || creatmappath[i-1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1 || creatmappath[i][j-1] == 2 || creatmappath[i][j-1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 2){
							JOptionPane.showMessageDialog(null, "Only can have one direction!!");
							state = false;
							return;
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
							return;
						}				
					}
					
				if(i == 0 && j != 0 && j!=creatmappath[i].length-1){
						if(creatmappath[i+1][j] == 1 || creatmappath[i+1][j] == 2 || creatmappath[i+1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1 || creatmappath[i][j-1] == 2 || creatmappath[i][j-1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1 || creatmappath[i][j+1] == 2 || creatmappath[i][j+1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 2){
							JOptionPane.showMessageDialog(null, "Only can have one direction!!");
							state = false;
							return;
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
							return;
						}
					}
					
					if(i != 0 && j == 0 && i!=creatmappath.length-1){
						if(creatmappath[i+1][j] == 1 || creatmappath[i+1][j] == 2 || creatmappath[i+1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i-1][j] == 1 || creatmappath[i-1][j] == 2 || creatmappath[i-1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1 || creatmappath[i][j+1] == 2 || creatmappath[i][j+1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 2){
							JOptionPane.showMessageDialog(null, "Only can have one direction!!");
							state = false;
							return;
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
							return;
						}	
					}
					
					if(i == creatmappath.length-1 && j != 0 && j!= creatmappath[i].length-1){
						if(creatmappath[i-1][j] == 1 || creatmappath[i-1][j] == 2 || creatmappath[i-1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1 || creatmappath[i][j-1] == 2 || creatmappath[i][j-1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j+1] == 1 || creatmappath[i][j+1] == 2 || creatmappath[i][j+1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 2){
							JOptionPane.showMessageDialog(null, "Only can have one direction!!");
							state = false;
							return;
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
							return;
						}	
					}
					
					if(i != 0 && j == creatmappath[i].length-1 && i!= creatmappath.length-1){
						if(creatmappath[i+1][j] == 1 || creatmappath[i+1][j] == 2 || creatmappath[i+1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i-1][j] == 1 || creatmappath[i-1][j] == 2 || creatmappath[i-1][j] == 3){
							numFindpath = numFindpath + 1;
						}
						if(creatmappath[i][j-1] == 1 || creatmappath[i][j-1] == 2 || creatmappath[i][j-1] == 3){
							numFindpath = numFindpath + 1;
						}
						if(numFindpath > 2){
							JOptionPane.showMessageDialog(null, "Only can have one direction!!");
							state = false;
							return;
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
							return;
						}	
					}
					numFindpath = 0;
				}
				
			}
		}
		
	}
	
	public boolean isState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}

	public int getNumEntryPoint() {
		return numEntryPoint;
	}

	public void setNumEntryPoint(int numEntryPoint) {
		this.numEntryPoint = numEntryPoint;
	}

	public int getNumPath() {
		return numPath;
	}

	public void setNumPath(int numPath) {
		this.numPath = numPath;
	}

	public int getNumExitPoint() {
		return numExitPoint;
	}

	public void setNumExitPoint(int numExitPoint) {
		this.numExitPoint = numExitPoint;
	}

	public int getNumFindpath() {
		return numFindpath;
	}

	public void setNumFindpath(int numFindpath) {
		this.numFindpath = numFindpath;
	}

}

package MapVerification;

import javax.swing.JOptionPane;

public class MapVerification {
	
	boolean state = true;
	
	public MapVerification(int[][] creatmappath){
		
		int numEntryPoint = 0;
		int numPath = 0;
		int numExitPoint = 0;	
		int numFindpath = 0;
		
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
							JOptionPane.showMessageDialog(null, "The path can have one way!!");
							state = false;
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the EntryPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
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
						}else if(numFindpath < 1){
							JOptionPane.showMessageDialog(null, "No path connected the ExitPoint!!");
							state = false;
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
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
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
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
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
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
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
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
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
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
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
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
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
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
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
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
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
						}else if(numFindpath < 2){
							JOptionPane.showMessageDialog(null, "The path can not get through!!");
							state = false;
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

}

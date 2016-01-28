package BaseGameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.peer.MouseInfoPeer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GameData.MapData;
import GameData.StaticGameInfo;
import MapVerification.MapVerification;

public class CreatMapScreen extends JPanel implements Runnable, MouseMotionListener, MouseListener{
	private int maprow;
	private int mapcol;
	private int focusX = -100;
	private int focusY = -100;
	private int clickfocusX = -100;
	private int clickfocusY = -100;
	
	private CreatMapScreen CMS;
	private String map_path;
	private int entryX;
	private int entryY;
	private int endX;
	private int endY;
	
	private JButton save,edit,back;
	private int[][] creatmappath;
	public int clickinfo;
	int count = 1;
	public MainScreen mainscreen;
	Thread creatmapscreen = new Thread(this);
	
	public CreatMapScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		this.CMS = this;
		init();
	}
	
	public void init(){
		this.setLayout(null);
		save = new JButton("SAVE Maps");
		edit = new JButton("EDIT Maps");
		back = new JButton("BACK");
		this.add(save);
		this.add(edit);
		this.add(back);
		save.setBounds(700,475,100,30);
		edit.setBounds(700,515,100,30);
		back.setBounds(700,550,100,30);
		save.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(new MapVerification(creatmappath).isState()){
				try {
					saveMap();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Congratulation~!");
				}
			}
		});
		edit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String mappath = fileChooser();
				CMS.map_path = mappath;
				try {
					loadMap();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		back.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				mainscreen.removeAll();
				mainscreen.add(new StartScreen(mainscreen));
				mainscreen.validate();
				mainscreen.repaint();		
			}
		});
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		creatmapscreen.start();
	}
	
	public void paintComponent(Graphics g) {
		if(count == 1){
		g.clearRect(0, 0, StaticGameInfo.frameWidth, StaticGameInfo.frameHeight);
		count++;
		}
		drawPath(g);
		drawGrid(g);
		switch(clickinfo){
		case 1: clickPath(g);break;
		case 2: clickEntryPoint(g);break;
		case 3: clickExitPoint(g);break;
		case 4: clickCancel(g);break;
		}
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
	}
	
	public void drawPath(Graphics g2) {
		for (int i = 0; i < creatmappath.length; i++) {
			for (int j = 0; j < creatmappath[i].length; j++) {
				if (creatmappath[i][j] == 1) {
					g2.setColor(Color.RED);
					g2.fillRect(j * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX, i* StaticGameInfo.gridSize
							+ StaticGameInfo.gameLocationY, StaticGameInfo.gridSize,
							StaticGameInfo.gridSize);
				}
				if (creatmappath[i][j] == 2) {
					g2.setColor(Color.BLUE);
					g2.fillRect(j * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX, i* StaticGameInfo.gridSize
							+ StaticGameInfo.gameLocationY, StaticGameInfo.gridSize,
							StaticGameInfo.gridSize);
				}
				if (creatmappath[i][j] == 3) {
					g2.setColor(Color.BLACK);
					g2.fillRect(j * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX, i* StaticGameInfo.gridSize
							+ StaticGameInfo.gameLocationY, StaticGameInfo.gridSize,
							StaticGameInfo.gridSize);
				}
			}
		}
	}
	
	public void drawGrid(Graphics g2) {
		g2.setColor(Color.BLACK);
		for(int x = 0;x < mapcol;x++){
			for(int y = 0;y < maprow;y++){
				g2.drawRect(StaticGameInfo.gridSize + (x*StaticGameInfo.gridSize), StaticGameInfo.gridSize + (y*StaticGameInfo.gridSize), StaticGameInfo.gridSize, StaticGameInfo.gridSize);
			}		
		}
	}
	
	public String fileChooser(){
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new File("/Users/AlexChen/Documents/workspace/SOEN6441_Tower_Defence/Maps"));	
        int result = jFileChooser.showOpenDialog(new JFrame());    
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        }else{
        	return null;
        }
	}
	
	public void loadMap() throws FileNotFoundException{
		MapData mapinfo = new MapData(map_path);
		maprow = mapinfo.getGridRow();
		mapcol = mapinfo.getGridCol();
		this.creatmappath = new int[maprow][mapcol];
		InputStream in = new FileInputStream(map_path); 
        Scanner scanner = new Scanner(in);
        while(scanner.hasNext()){   	
        	for(int i=0;i < mapinfo.getGridRow();i++){
        		for(int j=0;j < mapinfo.getGridCol();j++){
        			creatmappath[i][j] = scanner.nextInt();
        		}
        	}
        }
       try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void saveMap() throws IOException{
		String filename;
		filename = JOptionPane.showInputDialog("Please input the name of file!");
		File file = new File("Maps/"+ filename);
		FileWriter out = new FileWriter(file);
		for(int i=0;i<creatmappath.length;i++){
		for(int j=0;j<creatmappath[i].length;j++){
				out.write(creatmappath[i][j]+" ");
			}
			out.write("\n");
		}
		System.out.println("\n"+"over save");
		out.close();
		
		mainscreen.removeAll();
		mainscreen.add(new StartScreen(mainscreen));
		mainscreen.validate();
		mainscreen.repaint();
	}
	public void clickEntryPoint(Graphics g2){
		g2.setColor(Color.BLUE);
		g2.fillRect(clickfocusX, clickfocusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
		clickinfo = 0;
	}
	
	public void clickPath(Graphics g2){
		g2.setColor(Color.RED);
		g2.fillRect(clickfocusX, clickfocusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
		clickinfo = 0;
	}
		
	public void clickExitPoint(Graphics g2){
		g2.setColor(Color.BLACK);
		g2.fillRect(clickfocusX, clickfocusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
		clickinfo = 0;
	}
	
	public void clickCancel(Graphics g2){
		g2.clearRect(clickfocusX, clickfocusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
		clickinfo = 0;
	}
	

	@Override
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
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
	
	public void setMapcol(int mapcol) {
		this.mapcol = mapcol;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > StaticGameInfo.gameLocationX && x < StaticGameInfo.gameLocationX + mapcol*StaticGameInfo.gridSize && y > StaticGameInfo.gameLocationY
				&& y < StaticGameInfo.gameLocationY + maprow*StaticGameInfo.gridSize) {
			focusX = (x - StaticGameInfo.gameLocationX) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
			focusY = (y - StaticGameInfo.gameLocationY) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
		} else {
			focusX = -100;
			focusY = -100;
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > StaticGameInfo.gameLocationX && x < StaticGameInfo.gameLocationX + mapcol*StaticGameInfo.gridSize && y > StaticGameInfo.gameLocationY
				&& y < StaticGameInfo.gameLocationY + maprow*StaticGameInfo.gridSize) {
			clickfocusX = (x - StaticGameInfo.gameLocationX) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
			clickfocusY = (y - StaticGameInfo.gameLocationY) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
			
			Object[] clicklist = {"Entry Point","Path","Exit Point","Cancel"};
			String content = (String)JOptionPane.showInputDialog(null,"Which point you want to draw?\n","Draw point",JOptionPane.PLAIN_MESSAGE,null,clicklist,"Path");
			if(content == "Path"){
				clickinfo = 1;
				creatmappath[(clickfocusY/50)-1][(clickfocusX/50)-1] = 1;
			}else if(content == "Entry Point"){
				clickinfo = 2;
				creatmappath[(clickfocusY/50)-1][(clickfocusX/50)-1] = 2;
			}else if(content == "Exit Point"){
				clickinfo = 3;
				creatmappath[(clickfocusY/50)-1][(clickfocusX/50)-1] = 3;
			}else{
				clickinfo = 4;
				creatmappath[(clickfocusY/50)-1][(clickfocusX/50)-1] = 0;
			}
			
		} else {
			clickfocusX = -100;
			clickfocusY = -100;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int[][] getCreatmappath() {
		return creatmappath;
	}

	public void setCreatmappath(int[][] creatmappath) {
		this.creatmappath = creatmappath;
	}



	
	

}

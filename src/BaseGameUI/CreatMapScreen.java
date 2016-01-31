package BaseGameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
	private int[][] creatmappath;
	private int clickinfo;
	private String map_path;
	private JButton save,edit,back;
	private MainScreen mainscreen;
	private CreatMapScreen creatmapscreen;
	
	Thread CMST = new Thread(this);
	
	public CreatMapScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		this.creatmapscreen = this;
		
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
				String mappath = chooseFile();
				creatmapscreen.map_path = mappath;
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
		CMST.start();
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, StaticGameInfo.FRAME_WIDTH, StaticGameInfo.FRAME_HEIGHT);
		drawPath(g);
		drawGrid(g);
		switch(clickinfo){
		case 1: clickPath(g);break;
		case 2: clickEntryPoint(g);break;
		case 3: clickExitPoint(g);break;
		case 4: clickCancel(g);break;
		}
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
	}
	
	public void drawPath(Graphics g2) {
		for (int i = 0; i < creatmappath.length; i++) {
			for (int j = 0; j < creatmappath[i].length; j++) {
				if (creatmappath[i][j] == 1) {
					g2.setColor(Color.RED);
					g2.fillRect(j * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X, i* StaticGameInfo.GRID_SIZE
							+ StaticGameInfo.GAMELOCATION_Y, StaticGameInfo.GRID_SIZE,
							StaticGameInfo.GRID_SIZE);
				}
				if (creatmappath[i][j] == 2) {
					g2.setColor(Color.BLUE);
					g2.fillRect(j * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X, i* StaticGameInfo.GRID_SIZE
							+ StaticGameInfo.GAMELOCATION_Y, StaticGameInfo.GRID_SIZE,
							StaticGameInfo.GRID_SIZE);
				}
				if (creatmappath[i][j] == 3) {
					g2.setColor(Color.BLACK);
					g2.fillRect(j * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X, i* StaticGameInfo.GRID_SIZE
							+ StaticGameInfo.GAMELOCATION_Y, StaticGameInfo.GRID_SIZE,
							StaticGameInfo.GRID_SIZE);
				}
			}
		}
	}
	
	public void drawGrid(Graphics g2) {
		g2.setColor(Color.BLACK);
		for(int x = 0;x < mapcol;x++){
			for(int y = 0;y < maprow;y++){
				g2.drawRect(StaticGameInfo.GRID_SIZE + (x*StaticGameInfo.GRID_SIZE), StaticGameInfo.GRID_SIZE + (y*StaticGameInfo.GRID_SIZE), StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
			}		
		}
	}
	
	public String chooseFile(){
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new File("Maps/"));	
        int result = jFileChooser.showOpenDialog(new JFrame());    
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
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
		out.close();
		
		mainscreen.removeAll();
		mainscreen.add(new StartScreen(mainscreen));
		mainscreen.validate();
		mainscreen.repaint();
	}
	
	public void clickEntryPoint(Graphics g2){
		g2.setColor(Color.BLUE);
		g2.fillRect(clickfocusX, clickfocusY, StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
		clickinfo = 0;
	}
	
	public void clickPath(Graphics g2){
		g2.setColor(Color.RED);
		g2.fillRect(clickfocusX, clickfocusY, StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
		clickinfo = 0;
	}
		
	public void clickExitPoint(Graphics g2){
		g2.setColor(Color.BLACK);
		g2.fillRect(clickfocusX, clickfocusY, StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
		clickinfo = 0;
	}
	
	public void clickCancel(Graphics g2){
		g2.clearRect(clickfocusX, clickfocusY, StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > StaticGameInfo.GAMELOCATION_X && x < StaticGameInfo.GAMELOCATION_X + mapcol*StaticGameInfo.GRID_SIZE && y > StaticGameInfo.GAMELOCATION_Y
				&& y < StaticGameInfo.GAMELOCATION_Y + maprow*StaticGameInfo.GRID_SIZE) {
			focusX = (x - StaticGameInfo.GAMELOCATION_X) / StaticGameInfo.GRID_SIZE * StaticGameInfo.GRID_SIZE
					+ StaticGameInfo.GRID_SIZE;
			focusY = (y - StaticGameInfo.GAMELOCATION_Y) / StaticGameInfo.GRID_SIZE * StaticGameInfo.GRID_SIZE
					+ StaticGameInfo.GRID_SIZE;
		} else {
			focusX = -100;
			focusY = -100;
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > StaticGameInfo.GAMELOCATION_X && x < StaticGameInfo.GAMELOCATION_X + mapcol*StaticGameInfo.GRID_SIZE && y > StaticGameInfo.GAMELOCATION_Y
				&& y < StaticGameInfo.GAMELOCATION_Y + maprow*StaticGameInfo.GRID_SIZE) {
			clickfocusX = (x - StaticGameInfo.GAMELOCATION_X) / StaticGameInfo.GRID_SIZE * StaticGameInfo.GRID_SIZE
					+ StaticGameInfo.GRID_SIZE;
			clickfocusY = (y - StaticGameInfo.GAMELOCATION_Y) / StaticGameInfo.GRID_SIZE * StaticGameInfo.GRID_SIZE
					+ StaticGameInfo.GRID_SIZE;
			
			Object[] clicklist = {"Entry Point","Path","Exit Point","Cancel"};
			String content = (String)JOptionPane.showInputDialog(this,"Which point you want to draw?\n","Draw point",JOptionPane.PLAIN_MESSAGE,null,clicklist,"Path");
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

	public int[][] getCreatmappath() {
		return creatmappath;
	}

	public void setCreatmappath(int[][] creatmappath) {
		this.creatmappath = creatmappath;
	}

}

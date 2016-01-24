package BaseGameUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartScreen extends JPanel{
	private MainScreen mainscreen;
	private JButton creat,select,exit;
	public StartScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		System.out.println("come to startscreen");
		init();	
	}

	public void init(){
		System.out.println("start init()");
		this.setLayout(null);
 		this.setBackground(Color.orange);
		creat = new JButton("Creat Maps");
		select = new JButton("Select Maps");
		exit = new JButton("EXIT GAME");
		this.add(creat);
		this.add(select);
		this.add(exit);
		creat.setBounds(300, 100, 200,100);
		select.setBounds(300,250,200,100);
		exit.setBounds(300,400,200,100);
		creat.addMouseListener(new CreatMouseListener());
		select.addMouseListener(new SelectMouseListener());
		exit.addMouseListener(new ExitMouseListener());
		System.out.println("ready to add this");	
	}
	
	public String fileChooser(){
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new File("/Users/AlexChen/Documents/workspace/SOEN6441_Tower_Defence/Maps"));	
        int result = jFileChooser.showOpenDialog(new JFrame());    
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } 
		return null;		
	}
		
	public class CreatMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			CreatMapScreen CMS = new CreatMapScreen(mainscreen);
			JOptionPane requireROW = new JOptionPane();
			JOptionPane requireCOL = new JOptionPane();
			String row = requireCOL.showInputDialog("输入宽");
			String col = requireROW.showInputDialog("输入长");
			int x = Integer.parseInt(row);
			int y = Integer.parseInt(col);
			if(x > 7 || y > 14){
				System.out.println("错误");
			}else{
			CMS.setMaprow(x);
			CMS.setMapcol(y);		
			mainscreen.removeAll();
			mainscreen.add(CMS);
			mainscreen.validate();
			mainscreen.repaint();
			System.out.println("Creat Maps");
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
		
	}
	
	public class SelectMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			fileChooser();

			
			mainscreen.removeAll();
			mainscreen.add(new GameScreen(mainscreen));
			mainscreen.validate();
			mainscreen.repaint();
			System.out.println("Select Maps");
			
			
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
		
	}
	
	public class ExitMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			System.exit(0);
			
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
		
	}
}

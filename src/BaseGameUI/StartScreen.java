package BaseGameUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
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
		
	public class CreatMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			mainscreen.removeAll();
			CreatMapScreen CMS = new CreatMapScreen(mainscreen);
			mainscreen.add(CMS);
			mainscreen.validate();
			mainscreen.repaint();
			JOptionPane requireROW = new JOptionPane();
			JOptionPane requireCOL = new JOptionPane();
			String row = requireCOL.showInputDialog("输入宽");
			String col = requireROW.showInputDialog("输入长");
			int x = Integer.parseInt(row);
			int y = Integer.parseInt(col);

			System.out.println("Creat Maps");
			
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

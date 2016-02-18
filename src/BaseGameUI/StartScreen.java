package BaseGameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * This is beginning of game screen
 * @author peilin
 *
 */
public class StartScreen extends JPanel{

	private MainScreen mainscreen;
	private JButton creat,select,exit;
	
	public StartScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		init();	
	}

	/**
	 * This method is set up the value
	 */
	public void init(){
		this.setLayout(null);
		creat = new JButton("Creat Maps");
		select = new JButton("Select Maps");
		exit = new JButton("EXIT GAME");
		this.add(creat);
		this.add(select);
		this.add(exit);
		creat.setBounds(300, 100, 200,100);
		select.setBounds(300,250,200,100);
		exit.setBounds(300,400,200,100);
		creat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreatMapScreen CMS = new CreatMapScreen(mainscreen);
				int row = Integer.parseInt(JOptionPane.showInputDialog("Please input the number of row"));
				int col = Integer.parseInt(JOptionPane.showInputDialog("Please input the number of col"));
				if(row > 7 || col > 14){
					JOptionPane.showMessageDialog(null, "The row need to < 7 AND The col need to < 14");
				}else{
				CMS.setMaprow(row);
				CMS.setMapcol(col);
				int[][] path = new int[row][col];
				CMS.setCreatmappath(path);
				mainscreen.removeAll();
				mainscreen.add(CMS);
				mainscreen.validate();
				mainscreen.repaint();
				}
			}
		});
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mappath = chooseFile();
				GameScreen.MAP_PATH = mappath;
				GameScreen GS = new GameScreen(mainscreen);
				mainscreen.removeAll();
				mainscreen.add(GS);
				mainscreen.validate();
				mainscreen.repaint();
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * This method is choose a file when player select a map
	 * @return
	 */
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
}

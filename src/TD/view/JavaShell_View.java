package TD.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import TD.model.JavaShell_Model;

/**This class is a view of JavaShell.
 * @author DanQiao
 *
 */

public class JavaShell_View extends JFrame implements Observer{
	private JScrollPane jsp;
	private JTextArea jta;
	private JComboBox option;

	/**
	 * This is constructor of JavaSheel.
	 */
	
	public JavaShell_View() {
		this.setBackground(Color.BLACK);
		this.setTitle("LogInformation");
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] logtype = {"All","WAVE_1","WAVE_2","WAVE_3","Tower","Tower_1","Tower_2","Tower_3","Tower_4",};
        option = new JComboBox(logtype);
        this.add(option,BorderLayout.NORTH);
        jta = new JTextArea();
        jta.setEditable(false);
        jsp = new JScrollPane(jta);
        this.add(jsp,BorderLayout.CENTER);
	    this.setVisible(true);
	}
	
	/**
	 * This method is addoption listener.
	 * @param aListener
	 */
	public void addoptionListener(ActionListener aListener){
		option.addActionListener(aListener);
	}
	
	/**
	 * This method is get option content.
	 * @return
	 */
	public String getOptionContent(){
		return (String) option.getSelectedItem();
	}
	
	@Override
	/**
	 * This method is update.
	 */
	public void update(Observable arg0, Object arg1) {
		jta.setText("");
		BufferedReader br= ((JavaShell_Model) arg1).getBr();				
        String line;
        try {
			while ((line = br.readLine()) != null) {
			    jta.append(line+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        jta.append("End of log"+"\n");
        this.repaint();
        
		
	}
}

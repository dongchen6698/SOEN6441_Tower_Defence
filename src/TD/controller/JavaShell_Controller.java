package TD.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import TD.model.JavaShell_Model;
import TD.view.JavaShell_View;

/**
 * This is a class of Javashell_controller.
 * @author DanQiao
 *
 */

public class JavaShell_Controller {
	JavaShell_Model thejsModel;
	JavaShell_View thejsView;
	String type = "All";	
	
	/**
	 * This is a constructor of Javashell_Controller.
	 * @param jsView
	 * @param jsModel
	 */
	
	public JavaShell_Controller(JavaShell_View jsView, JavaShell_Model jsModel) {
		
		this.thejsModel = jsModel;
		this.thejsView = jsView;
		try {
			thejsModel.getSpecificLogInfo(type);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		this.thejsView.addoptionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String t = thejsView.getOptionContent();
				//System.out.println(t);	
				try {
					thejsModel.getSpecificLogInfo(t);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});	
	}
	
}

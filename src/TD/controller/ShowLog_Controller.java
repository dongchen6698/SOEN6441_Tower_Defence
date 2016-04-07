package TD.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import TD.model.ShowLog_Model;
import TD.view.ShowLog_View;

/**
 * This is a class of Javashell_controller.
 * @author DanQiao
 *
 */
public class ShowLog_Controller {
	public ShowLog_Model thejsModel;
	public ShowLog_View thejsView;	
	
	/**
	 * This is a constructor of Javashell_Controller.
	 * @param jsView
	 * @param jsModel
	 */
	public ShowLog_Controller(ShowLog_View jsView, ShowLog_Model jsModel, String type) {
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
				try {
					thejsModel.getSpecificLogInfo(t);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});	
	}
}

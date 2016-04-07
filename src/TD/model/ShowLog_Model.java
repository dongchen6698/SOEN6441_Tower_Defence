package TD.model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Observable;

/**
 * This is model for JavaSheel.
 * @author DanQiao
 *
 */
public class ShowLog_Model extends Observable{
	private BufferedReader br;
	public String filepath;
	
	/**
	 * this is a default show log constructor.
	 * @param new_filepath
	 */
	public ShowLog_Model(String new_filepath){
		this.filepath = new_filepath;
	}
	/**
	 * This is constructor for get specific log information.
	 * @param type
	 * @throws Exception
	 */	
	public void getSpecificLogInfo(String type) throws Exception{
		Runtime rt = Runtime.getRuntime();
		if(type == "All"){
			String[] cmd = { "/bin/sh", "-c", "cat "+filepath};
		    Process proc = rt.exec(cmd);
		    br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		}else{
			String[] cmd = { "/bin/sh", "-c", "cat "+filepath+"| grep "+type};
		    Process proc = rt.exec(cmd);
		    br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		}
	    	    
	    this.setChanged();
	    notifyObservers(this);
	    
	}

/**
 * This method is to get new bufferedReader.
 * @return
 */
	public BufferedReader getBr() {
		return br;
	}
 
}

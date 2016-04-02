package TD.model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Observable;

public class JavaShell_Model extends Observable{
	private BufferedReader br;
	
	public void getSpecificLogInfo(String type) throws Exception{
		Runtime rt = Runtime.getRuntime();
		String filepath = "logfile/logtest";
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


	public BufferedReader getBr() {
		return br;
	}
 
}

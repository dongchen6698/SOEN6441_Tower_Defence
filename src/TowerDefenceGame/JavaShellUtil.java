package TowerDefenceGame;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JavaShellUtil {
	
	public JavaShellUtil(String type) throws Exception{
		Runtime rt = Runtime.getRuntime();
		String path = "logfile/logtest";
	    String[] cmd = { "/bin/sh", "-c", "cat "+path+"| grep "+type };
	    Process proc = rt.exec(cmd);
	    BufferedReader is = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	    String line;
	    while ((line = is.readLine()) != null) {
	        System.out.println(line);
	    }
	}
	
}

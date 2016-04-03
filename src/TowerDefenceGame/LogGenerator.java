package TowerDefenceGame;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogGenerator {
	
	
	public static void addLogInfo(String TowerID,String creatureID,String wave,String content){
		getCurrentTime();
		
		
	}
	
	public static void creatLogFile(String logtype){
		if(logtype == "creatmap"){
			//creat creatmap log file
		}else if(logtype == "game"){
			//creat globol log file
		}
	}
	
	public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat ft =  new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
        return "["+ft.format(date)+"] : ";
    }
	
}

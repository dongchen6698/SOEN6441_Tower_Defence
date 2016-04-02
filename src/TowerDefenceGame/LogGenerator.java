package TowerDefenceGame;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogGenerator {
	
	
	public static void addLogInfo(){
		
	}
	
	public static void creatLogFile(){
		
	}
	
	public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat ft =  new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
        return "["+ft.format(date)+"] : ";
    }
	
}

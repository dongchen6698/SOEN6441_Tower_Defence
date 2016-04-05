package TowerDefenceGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogGenerator {
	private static File file;
	private static BufferedWriter bfw;
	
	public static void addLogInfo(String TowerID,String creatureID,String waveNum,String content){
		String logcontent = getCurrentTime()+" : "+TowerID+" : "+creatureID+" : "+waveNum+" : "+content;
		
		try {
			bfw = new BufferedWriter(new FileWriter(file,true));
			bfw.write(logcontent+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bfw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void creatLogFile(String logtype){
		if(logtype == "creatmap"){
			//creat creatmap log file
			File maplogfile = new File("logfile/maplog/MapLogFile.log");
			file = maplogfile;
		}else if(logtype == "game"){
			//creat globol log file
			File gamelogfile = new File("logfile/gamelog/GlobleGameLog.log");
			if(!gamelogfile.exists()){
				try {
					gamelogfile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				gamelogfile.delete();
				try {
					gamelogfile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			file = gamelogfile;
		}
	}
	
	public static void closeBufferedWriter(){
		try {
			bfw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat ft =  new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
        return "["+ft.format(date)+"]";
    }
	
}

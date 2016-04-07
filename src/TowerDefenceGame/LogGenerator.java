package TowerDefenceGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is for log generator.
 * @author DanQiao
 *
 */

public class LogGenerator {
	private static File file = new File("logfile/GlobleGameLog.log");
	private static BufferedWriter bfw;
	//private static File maplog;
	/**
	 * This is a constructor for add log information.
	 * @param waveNum
	 * @param TowerID
	 * @param content
	 */	
	public static void addLogInfo(String waveNum, String TowerID, String content){
		String logcontent = getCurrentTime()+" : "+waveNum+" : "+TowerID+" : "+content;
		
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
	
	public static void creatMapLog(String str, String content, int score){
		File maplog = new File("maplogfile/" + str+ ".log");
		if(!maplog.exists()){
			try{
				maplog.createNewFile();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		String maploginfo = getCurrentTime()+ ":" + "Map: "+ str+ " "+content;
		try {
			bfw = new BufferedWriter(new FileWriter(maplog,true));
			bfw.write(maploginfo+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bfw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method is for adding map log
	 * @param str
	 * @param score
	 */
	public static void addMapLog(File map, String mapname, String content, int score){
		
		String maploginfo = getCurrentTime()+ ":" + "Map: "+ mapname+ " "+content+". the score: "+score; 
		try {
			bfw = new BufferedWriter(new FileWriter(map,true));
			bfw.write(maploginfo+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bfw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This is a constructor for create log file.
	 * @param logtype
	 */	
	public static void creatLogFile(String logtype){
		if(logtype == "creatmap"){
			File maplogfile = new File("logfile/maplog/MapLogFile.log");
			file = maplogfile;
		}else if(logtype == "game"){
			File gamelogfile = new File("logfile/GlobleGameLog.log");
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
	
	/**
	 * This is a constructor for close buffer writer.
	 */	
	public static void closeBufferedWriter(){
		try {
			bfw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is get current time.
	 * @return
	 */	
	public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat ft =  new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
        return "["+ft.format(date)+"]";
    }	
}

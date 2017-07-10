package utility;

import java.util.HashMap;
import java.util.logging.Logger;

public class Log {
	
	//private static HashMap<String, TimeAndCount> lastloggedTime=new HashMap<>(String, TimeAndCount);
	private static Logger log=Logger.getLogger(Log.class.getName());
	
	public static void startTestCase(String sTestCaseName){
		log.info("*****************************************************************");
		log.info("**********"+sTestCaseName+ "************");
		log.info("******************************************************************");
	}
	
	public static void endTestCase(){
		log.info("XXX---------"+"E-N-D"+"--------------XXX");
		log.info("X");
		log.info("X");
	}
	
	public static void info(String message)
	{
		log.info(message);
	}
	
	/*public static void warn(String message){
		log.warn(message);
	}
	
	public static void error(String message){
		log.error(message);
	}
	
	public static void fatal(String message){
		log.fatal(message);
	}
	
	public static void debug(String message){
		log.debug(message);
	}
	public static void logConfig(Level level, long timetakenbetweenlogs, String message, Throwable t){
		if(log.isEnabledFor(level)){
			String uniqueIdentifier=getFileAndLine();
			TimeAndCount lastTimeAndCount=lastLoggedTime.get(uniqueIdentifier);
			if(lastTimeAndCount!=null){
				synchronized (lastTimeAndCount) {
					long now=System.currentTimeMillis();
					if(now-lastTimeAndCount.time<timetakenbetweenlogs){
						return ;
					}else{
						log(log,level,message,t);
					}
				}
			}else{
				log(log,level,message,t);
			}
			lastLoggedTime.put(uniqueIdentifier, new TimeAndCount());
		}
	}
	private static String getFileAndLine(){
		StackTraceElement[] stackTrace=Thread.currentThread().getStackTrace();
		boolean enteredLogConsolidated=false;
		for(StackTraceElement ste:stackTrace){
			if(ste.getClassName().equals(log.class.getName())){
				enteredLogConsolidated=true;
			}else if(enteredLogConsolidated){
				return ste.getFileName()+":"+ste.getLineNumber();
			}
			return "?";
		}
	}
	private static void log(java.util.logging.Logger logger, Level level, String message, Throwable t){
		if(t==null){
			logger.log(level, message);
		}else{
			logger.log(level, message, t);
		}
	}
	private static class TimeAndCount{
		long time;
		public TimeAndCount() {
			this.time=System.currentTimeMillis();
		}
	}
*/
}

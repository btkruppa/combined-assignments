package Util;

/**
 * This class is for outputting debug information
 * 
 * @author 12
 *
 */
public class Debugger {
	private static boolean debugMessages = false;
	private static boolean debugErrors = true;
	 
	public static void debugMessage(String message) {
		if(debugMessages)
			System.out.println("INFO: " + message);
		return;
	}
	
	public static void errorStackTrace(Exception e) {
		if(debugErrors)
			e.printStackTrace();
		return;
	}
}

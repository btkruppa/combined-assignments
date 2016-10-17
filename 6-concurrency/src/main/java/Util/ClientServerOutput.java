package Util;

public class ClientServerOutput {
	private static boolean clientOutput = false; //true to output client logs
	private static boolean serverOutput = true; //true to output server logs
	public static void clientOutput(String message) {
		if(clientOutput) {
			System.out.println("CLIENT: " + message);
		}
	}
	 
	public static void serverOutput(String message) {
		if(serverOutput) {
			System.out.println("SERVER: " + message);
		}
	}
}

package disconsented.anssrpg.common;

public class Settings {
	private static boolean debug = false;
	private static double levelCurve = 1.6;
	private static String statusMessage = "null";
	
	private static Settings instance = new Settings();
	
	private Settings(){}
	public static Settings getInstance()
	{
		return instance;
	}
	public static boolean getDebug(){
		return  debug;
	}
	public static void setDebug(Boolean yes){
		debug = yes;
	}
	public static double getLevelCurve() {
		return levelCurve;
	}
	public static void setLevelCurve(Double amount){
		levelCurve = amount;
	}
	public static void setStatusMessage(String message){
		statusMessage = message;
	}
	public static String getStatusMessage()
	{
		return statusMessage;
	}
}

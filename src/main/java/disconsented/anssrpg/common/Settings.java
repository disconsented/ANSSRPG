package disconsented.anssrpg.common;

import java.io.File;

import net.minecraft.server.MinecraftServer;

public class Settings {
	private static boolean debug = false;
	private static double levelCurve = 1.6;
	private static String statusMessage = "null";
	private static double pointsRatio = .2;
	/**
	 * 0 - Disabled
	 * 1 - Points awarded based on XP from skills
	 * 2 - Points can be converted from vanilla levels
	 */
	private static int pointsMode = 1;
	
	private static Settings instance = new Settings();
	public static boolean isServer = false;
	
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
	public static void setPointsMode(int int1) {
		pointsMode = int1;
	}
	public static int getPointsMode(){
		return pointsMode;
	}
	public static File getFolder(){
		if (isServer){
			return new File("\\"+MinecraftServer.getServer().getFolderName()+"\\data");
		}
		else{
			return new File("\\saves\\"+MinecraftServer.getServer().getFolderName()+"\\data");
		}
				
	}
}

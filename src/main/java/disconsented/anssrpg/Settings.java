package disconsented.anssrpg;

public class Settings {
	static boolean printOutItem = false;
	static boolean printOutBlock = false;
	static boolean printOutEntity = false;
	static boolean debug = false;
	static double levelCurve = 1.6;
	
	public static boolean getDebug(){
		return  debug;
	}
	public static void setDebug(Boolean yes){
		debug = yes;
	}
	public static boolean getPrintItem(){
		return printOutItem;
	}
	public static void setPrintItem(Boolean yes){
		printOutItem = yes;
	}
	public static boolean getPrintBlock(){
		return printOutBlock;
	}
	public static void setPrintBlock(Boolean yes){
		printOutBlock = yes;
	}
	public static boolean getPrintEntity(){
		return printOutEntity;
	}
	public static void setPrintEntity(Boolean yes){
		printOutEntity = yes;
	}
	public static double getLevelCurve() {
		return levelCurve;
	}
	public static void setLevelCurve(Double amount){
		levelCurve = amount;
	}
}

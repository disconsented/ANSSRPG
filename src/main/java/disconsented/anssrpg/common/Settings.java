/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package disconsented.anssrpg.common;

import java.io.File;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import disconsented.anssrpg.commands.ConfigGUI;
import disconsented.anssrpg.network.PerkInfo;
import disconsented.anssrpg.network.PerkInfoHandler;
import disconsented.anssrpg.network.Request;
import disconsented.anssrpg.network.RequestHandler;
import disconsented.anssrpg.network.Responce;
import disconsented.anssrpg.network.ResponceHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Configuration;

public class Settings {
	private static boolean debug = false;
	private static double levelCurve = 1.6;
	private static String statusMessage = "null";
	private static double pointsRatio = .2;
	/**
	 * Checks if the references in the config gui are valid when saving
	 */
	private static boolean checkValidReference = false;
	/**
	 * 0 - Disabled
	 * 1 - Points awarded based on XP from skills
	 * 2 - Points can be converted from vanilla levels
	 */
	private static int pointsMode = 1;
	
	private static Settings instance = new Settings();
	public static boolean isServer = false;
	
	private Settings(){}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {        
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        setLevelCurve(config.get(config.CATEGORY_GENERAL, "Level Curve", 1.3).getDouble());
        setDebug(config.get(config.CATEGORY_GENERAL, "debug", false).getBoolean(false));
        setCheckValidReference(config.get(config.CATEGORY_GENERAL, "Check valid references", false).getBoolean(false));
        setPointsMode(config.get(config.CATEGORY_GENERAL, "Points Mode", 1,"0 = disabled, 1 = based on assrpg xp, 2 = convert vanilla levels to points").getInt());
        config.save();
	}
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
			return new File(MinecraftServer.getServer().getFolderName()+"\\data\\");
		}
		else{
			return new File("saves\\"+MinecraftServer.getServer().getFolderName()+"\\data\\");
		}
				
	}
    public static boolean isCheckValidReference() {
        return checkValidReference;
    }
    public static void setCheckValidReference(boolean checkValidReference) {
        Settings.checkValidReference = checkValidReference;
    }
}

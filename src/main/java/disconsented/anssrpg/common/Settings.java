/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr
Copyright (c) 2015 TehNut

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

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Settings {
    // Categories
    public static String balancing = "Balancing";
    public static String misc = "Misc Options";
    public static boolean isServer = false;
    private static boolean debug = false;
    private static boolean logging = true;
    private static String statusMessage = "null";
    private static double pointsRatio = .2;
    private static boolean externalConfig = false;
    /**
     * 0 - Disabled
     * 1 - Points awarded based on XP from skills
     * 2 - Points can be converted from vanilla levels
     */
    private static int pointsMode = 1;
    private static Settings instance = new Settings();

    private Settings() {
    }

    public static boolean getDebug() {
        return debug;
    }

    public static void setDebug(Boolean yes) {
        debug = yes;
    }

    public static File getFolder() {
        if (isServer) {
            return new File(MinecraftServer.getServer().getFolderName() + "\\anssrpgdata\\");
        } else {
            return new File("saves\\" + MinecraftServer.getServer().getFolderName() + "\\anssrpgdata\\");
        }

    }

    public static Settings getInstance() {
        return instance;
    }

    public static int getPointsMode() {
        return pointsMode;
    }

    public static void setPointsMode(int int1) {
        pointsMode = int1;
    }

    public static String getStatusMessage() {
        return statusMessage;
    }

    public static void setStatusMessage(String message) {
        statusMessage = message;
    }

    public static boolean getLogging() {
        return logging;
    }

    public void load(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        config.addCustomCategoryComment(balancing, "Balancing tweaks.");
        config.addCustomCategoryComment(misc, "Settings that don't fit in other categories");

        pointsRatio = config.get(balancing, "pointsRatio", .2, "Points ratio settings.").getDouble();
        pointsMode = config.get(balancing, "pointsMode", 1, "Points Mode. \n0 - Disabled \n1 - Points awarded based on XP from skills \n2 - Points can be converted from vanilla levels").getInt();

        debug = config.get(misc, "enableDebugMode", false, "Enables debugging features. Meant for development use.").getBoolean();
        logging = config.get(misc, "enableLogging", true, "Enables logging to console.").getBoolean();
        externalConfig = config.get(misc, "useExternalConfig", false, "Use config files instead of default internal configs").getBoolean();

        config.save();
    }

    public static boolean isExternalConfig() {
        return externalConfig;
    }
}

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
package disconsented.anssrpg.server.common;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Settings {
    // Categories
    public static String balancing = "Balancing";
    public static String misc = "Misc Options";
    public static boolean isServer;
    private static boolean debug;
    private static boolean logging = true;
    private static String statusMessage = "null";
    private static double pointsRatio = .2;
    private static boolean externalConfig;
    private static boolean requiredOnClient = true;

    private static boolean blockFakePlayers = true;
    /**
     * 0 - Disabled
     * 1 - Points awarded based on XP from skills
     * 2 - Points can be converted from vanilla levels
     */
    private static int pointsMode = 1;
    private static final Settings instance = new Settings();

    private Settings() {
    }

    public static boolean getDebug() {
        return Settings.debug;
    }

    public static void setDebug(Boolean yes) {
        Settings.debug = yes;
    }

    public static File getFolder() {
        if (Settings.isServer) {
            return new File(MinecraftServer.getServer().getFolderName() + "\\anssrpgdata\\");
        } else {
            return new File("saves\\" + MinecraftServer.getServer().getFolderName() + "\\anssrpgdata\\");
        }

    }

    public static Settings getInstance() {
        return Settings.instance;
    }

    public static int getPointsMode() {
        return Settings.pointsMode;
    }

    public static boolean isBlockFakePlayers() {
        return Settings.blockFakePlayers;
    }

    public static void setPointsMode(int int1) {
        Settings.pointsMode = int1;
    }

    public static String getStatusMessage() {
        return Settings.statusMessage;
    }

    public static void setStatusMessage(String message) {
        Settings.statusMessage = message;
    }

    public static boolean getLogging() {
        return Settings.logging;
    }

    public void load(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        config.addCustomCategoryComment(Settings.balancing, "Balancing tweaks.");
        config.addCustomCategoryComment(Settings.misc, "Settings that don't fit in other categories");

        Settings.pointsRatio = config.get(Settings.balancing, "pointsRatio", .2, "Points ratio settings.").getDouble();
        Settings.pointsMode = config.get(Settings.balancing, "pointsMode", 1, "Points Mode. \n0 - Disabled \n1 - Points awarded based on XP from skills \n2 - Points can be converted from vanilla levels").getInt();
        Settings.blockFakePlayers = config.getBoolean("blockUnknownFakePlayers", Settings.balancing,true,"Enables fake players that are not associated with a real player being blocked by default(where appropriate)");

        Settings.debug = config.get(Settings.misc, "enableDebugMode", false, "Enables debugging features. Meant for development use.").getBoolean();
        Settings.logging = config.get(Settings.misc, "enableLogging", true, "Enables logging to console.").getBoolean();
        Settings.externalConfig = config.get(Settings.misc, "useExternalConfig", false, "Use config files instead of default internal configs").getBoolean();
        Settings.requiredOnClient = config.get(Settings.misc, "requiredOnClient", true, "Clients require the mod to be able to connect to the server").getBoolean();

        config.save();
    }

    public static boolean isExternalConfig() {
        return Settings.externalConfig;
    }
}

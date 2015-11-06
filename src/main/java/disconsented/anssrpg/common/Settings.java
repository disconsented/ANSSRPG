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

import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Settings {

    public static Configuration config;

    // Categories
    public static String balancing = "Balancing";
    public static String misc = "Misc Options";

    @Getter @Setter
    private static boolean isServer;
    @Getter @Setter
    private static boolean debugEnabled;
    @Getter
    private static boolean loggingEnabled;
    @Getter
    private static double pointsRatio;
    @Getter
    private static boolean externalConfig;
    @Getter
    private static boolean requiredOnClient;
    @Getter
    private static boolean blockFakePlayers;
    @Getter
    private static int pointsMode;

    public static File getFolder() {
        return new File((isServer ? "" : "saves/") + MinecraftServer.getServer().getFolderName());
    }

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        config.addCustomCategoryComment(balancing, "Balancing tweaks.");
        config.addCustomCategoryComment(misc, "Settings that don't fit in other categories");

        pointsRatio = config.get(balancing, "pointsRatio", .2, "Points ratio settings.").getDouble();
        pointsMode = config.getInt("pointsMode", balancing, 1, 0, 2, "Points Mode. \n0 - Disabled \n1 - Points awarded based on XP from skills \n2 - Points can be converted from vanilla levels");
        blockFakePlayers = config.getBoolean("blockUnknownFakePlayers", balancing, true, "Enables fake players that are not associated with a real player being blocked by default(where appropriate)");

        debugEnabled = config.getBoolean("enableDebugMode", misc, false, "Enables debugging features. Meant for development use.");
        loggingEnabled = config.getBoolean("enableLogging", misc, true, "Enables loggingEnabled to console.");
        externalConfig = config.getBoolean("useExternalConfig", misc, false, "Use config files instead of default internal configs");
        requiredOnClient = config.getBoolean("requiredOnClient", misc, true, "Clients require the mod to be able to connect to the server");

        config.save();
    }
}

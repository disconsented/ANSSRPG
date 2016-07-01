/*
 * Copyright (c)  2015-2016, James Kerr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package disconsented.anssrpg.core;

import disconsented.anssrpg.compat.CompatContainer;
import disconsented.anssrpg.compat.tconstruct.TiCon;
import disconsented.anssrpg.compat.tconstruct.TiConContainer;
import disconsented.anssrpg.core.server.common.Logging;
import disconsented.anssrpg.core.server.config.JsonConfigHandler;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;

public class Support {
    private static ArrayList<CompatContainer> containers = new ArrayList<>();
    private Support(){}
    public static void loadMods(){
        if(Loader.isModLoaded("tconstruct")){
            containers.add(new TiCon());
            Logging.debug("TConstruct loaded");
        }
    }

    public static void loadInternalConfig(){
        containers.forEach(container -> {
            container.getContainer().init();
            Logging.debug("Loading internal config for " + container.getName());
        });
    }

    public static void loadExternalConfig(){
        containers.forEach(container -> {
            JsonConfigHandler.readCompat(container);
            Logging.debug("Loading external config for " + container.getName());
        });
    }
}

/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr
Copyright (c) 2015 Abelistah

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
package disconsented.anssrpg.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import disconsented.anssrpg.common.Logging;

/**
 * @author Disconsented, Abelistah
 *         Json Config's
 */

public class JsonConfigHandler {
    private static File skillFile = new File("config/ANSSRPG", "skill.cfg");
    private static File perkFile = new File("config/ANSSRPG", "perk.cfg");
    private static File configFileLocation = new File("config/ANSSRPG");

    public static void createPerkAndSkill() {
        createSkillConfig(null);
        createPerkConfig(null);
    }

    /**
     * Writes the perkStore to disk, if it is null then it will create the default one
     *
     * @param perkStore
     */
    public static void createPerkConfig(PerkContainer perkStore) {
        if (perkStore == null) {
            perkStore = new PerkContainer(true);
        }


        try {
            configFileLocation.mkdirs();
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
            Writer osWriter = new OutputStreamWriter(new FileOutputStream(perkFile));
            gson.toJson(perkStore, osWriter);
            osWriter.close();

        } catch (Exception e) {
            Logging.error("Exception when creating perk config");
            Logging.error(e.getLocalizedMessage());
        }
    }

    /**
     * Writes the skillStore to disk, if skillStore is null it will create the default one
     *
     * @param skillStore
     */
    public static void createSkillConfig(SkillContainer skillStore) {
        if (skillStore == null) {
            skillStore = new SkillContainer();
            //			skillStore.addBlockSkill(new BlockSkill());
            //			skillStore.addBlockSkill(new BlockSkill());
            //			skillStore.addEntitySkill(new EntitySkill());
            //			skillStore.addEntitySkill(new EntitySkill());
            //			skillStore.addItemSkill(new ItemSkill());
            //			skillStore.addItemSkill(new ItemSkill());
        }
        try {
            configFileLocation.mkdirs();
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
            Writer osWriter = new OutputStreamWriter(new FileOutputStream(skillFile));
            gson.toJson(skillStore, osWriter);
            osWriter.close();

        } catch (Exception e) {
            Logging.error("Exception when creating skill config");
            Logging.error(e.getLocalizedMessage());
        }
    }

    public static void loadPerkAndSkill() {
        loadPerkConfig();
        loadSkillConfig();
    }

    /**
     * Loads perk data from disk into memory
     */
    private static void loadPerkConfig() {
        try {
            Gson gson = new Gson();
            Type objectStoreType = new TypeToken<PerkContainer>() {
            }.getType();
            Reader isReader = new InputStreamReader(new FileInputStream(perkFile));
            PerkContainer perkStore = gson.fromJson(isReader, objectStoreType);
            isReader.close();

            if (perkStore != null) {
                perkStore.touchUp();
            }
        } catch (FileNotFoundException e) {
            createPerkConfig(null);
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }

    /**
     * Loads skill file into memory
     */
    private static void loadSkillConfig() {
        try {
            Gson gson = new Gson();
            Type objectStoreType = new TypeToken<SkillContainer>() {
            }.getType();
            Reader isReader = new InputStreamReader(new FileInputStream(skillFile));
            SkillContainer skillStore = gson.fromJson(isReader, objectStoreType);
            isReader.close();

            if (skillStore != null) {
                skillStore.touchUp();
            }
        } catch (FileNotFoundException e) {
            createSkillConfig(null);
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
}

package com.disconsented.jrpg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Skill
 */
public abstract class Skill implements Loadable {
    protected static transient ArrayList<Skill> storage = new ArrayList<>();
    protected String name;
    protected int base;
    protected float modifier;
    protected ArrayList<Datum> entries = new ArrayList<>();

    /**
     * Calculates how much experience is needed for a given level
     *
     * @param level
     * @return
     */
    public double getExperienceForLevel(int level) {
        return base * Math.pow(level, modifier);
    }

    /**
     * Calculates what level a given quantity of experience is
     *
     * @param experience
     * @return
     */
    public double getLevelForExperience(long experience) {
        return Math.pow(experience, 1 / modifier) / Math.pow(base, 1 / modifier);
    }

    public String getName() {
        return name;
    }

    @Override
    public void serialize() {
        try {
            File location = new File("config/" + JRPG.MODID, this.getClass().getCanonicalName() + ".json");
            location.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(location);
            Gson serialiser = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            serialiser.toJson(new ArrayList<>(storage), writer);
            writer.close();
            JRPG.log.info("Saved " + this.getClass().getCanonicalName() + " to disk");
        } catch (Exception e) {
            JRPG.log.error("can't serialize ", e);
        }
    }

    @Override
    public void deserialize() {
        File location = new File("config/" + JRPG.MODID, this.getClass().getCanonicalName() + ".json");
        location.getParentFile().mkdirs();

        if (location.exists()) {
            try {
                FileReader reader = new FileReader(location);
                Gson gson = new Gson();
                storage = gson.fromJson(reader, new TypeToken<ArrayList<Skill>>() {
                }.getType());
                Skill skill = gson.fromJson(reader, this.getClass());
                JRPG.log.info("Loaded from disk " + skill.getName());
            } catch (Exception e) {
                JRPG.log.error(e);
            }
        } else {
            JRPG.log.warn("missing config file ", this.getClass().getCanonicalName());
        }
    }
}

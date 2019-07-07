package com.disconsented.jrpg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Skill
 */
public abstract class Skill<T extends Skill> implements Loadable {
    protected static transient ArrayList<Skill> storage = new ArrayList<>();
    protected String name;
    protected int base;
    protected float modifier;

    @Override
    public void attach() {
        storage.add(this);
    }

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
            serialiser.toJson(new ArrayList<>(getStorage()), writer);
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
                Type token = getTypeToken();
                ArrayList storage = gson.fromJson(reader, token);
                setStorage(storage);
                JRPG.log.info("loaded " + this.getClass().getCanonicalName());
            } catch (Exception e) {
                JRPG.log.error(e);
            }
        } else {
            JRPG.log.warn("missing config file " + this.getClass().getCanonicalName());
        }
    }

    public abstract ArrayList<? extends Skill> getStorage();

    protected abstract void setStorage(ArrayList<T> storage);
}

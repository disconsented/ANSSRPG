package com.disconsented.jrpg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Trait, formerly known as Perk
 * Used to gate content
 */
public abstract class Trait implements Loadable {
    protected static transient ArrayList<Trait> storage = new ArrayList<>();
    protected ArrayList<Requirement> requirements = new ArrayList<>();

    protected String name;

    public static ArrayList<Trait> getAllTraits() {
        return storage;
    }

    @Override
    public void attach() {
        storage.add(this);
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
                storage = gson.fromJson(reader, new TypeToken<ArrayList<Trait>>() {
                }.getType());
                Trait trait = gson.fromJson(reader, this.getClass());
                JRPG.log.info("Loaded from disk " + trait.getName());
            } catch (Exception e) {
                JRPG.log.error(e);
            }
        } else {
            JRPG.log.warn("missing config file ", this.getClass().getCanonicalName());
        }
    }

    public boolean satisfiesRequirements(Player player) {
        for (Requirement requirement : requirements) {
            switch (requirement.getType()) {
                case "LEVEL_GREATER":
                    if (player.getLevel(requirement.getOperator()) > Double.parseDouble(requirement.getObject())) {
                        continue;
                    }
                    return false;
                case "LEVEL_LESSER":
                    if (player.getLevel(requirement.getOperator()) < Double.parseDouble(requirement.getObject())) {
                        continue;
                    }
                    return false;
                case "LEVEL_EQUAL":
                    if (player.getLevel(requirement.getOperator()) == Double.parseDouble(requirement.getObject())) {
                        continue;
                    }
                    return false;
                case "TRAIT":
                    if (requirement.getOperator().equals("HAS")) {
                        if (player.hasTrait(requirement.getObject())) {
                            continue;
                        }
                    } else {
                        if (!player.hasTrait(requirement.getObject())) {
                            continue;
                        }
                    }
                    return false;
                case "TRAIT_NOT":
                    if (requirement.getOperator().equals("HAS")) {
                        if (!player.hasTrait(requirement.getObject())) {
                            continue;
                        }
                    } else {
                        if (player.hasTrait(requirement.getObject())) {
                            continue;
                        }
                    }
                    return false;
                default:
                    // ignore unknown requirements
                    JRPG.log.warn("Unknown requirement type " + requirement.getType());
            }
        }
        return true;
    }
}

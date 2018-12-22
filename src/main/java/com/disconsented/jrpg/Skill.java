package com.disconsented.jrpg;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Skill
 */
public abstract class Skill {
    static HashMap<String, ArrayList<Skill>> storage;
    private String name;
    private int base;
    private float modifier;

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
}

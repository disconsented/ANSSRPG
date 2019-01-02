package com.disconsented.jrpg;

import java.util.HashMap;

/**
 * Trait, formerly known as Perk
 */
public abstract class Trait {
    static HashMap<String, Trait> storage;

    private String name;

    public abstract String getName();
}

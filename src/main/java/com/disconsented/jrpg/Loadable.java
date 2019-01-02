package com.disconsented.jrpg;

public interface Loadable {
    void serialize();

    void deserialize();

    /**
     * Called after all of the objects have been deserialized
     */
    void load();
}

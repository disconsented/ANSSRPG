package com.disconsented.jrpg;


import java.lang.reflect.Type;

public interface Loadable {
    void serialize();

    void deserialize();

    /**
     * Called after all of the objects have been deserialized
     */
    void load();

    void attach();

    Type getTypeToken();
}

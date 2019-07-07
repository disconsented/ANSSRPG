package com.disconsented.jrpg;

import com.disconsented.jrpg.vanilla.SkillBlockBreak;
import com.disconsented.jrpg.vanilla.TraitBlockBreak;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


@Mod(modid = JRPG.MODID, name = JRPG.NAME, version = JRPG.VERSION)
public class JRPG {
    public static final String MODID = "jrpg";
    public static final String NAME = "JRPG";
    public static final String VERSION = "@VERSION@";

    public static Logger log;

    public static final ArrayList<Loadable> OBJS = new ArrayList<>();

    public static boolean register(Loadable loadable) {
        if (Loader.instance().isInState(LoaderState.PREINITIALIZATION)) {
            OBJS.add(loadable);
            log.info("registered type " + loadable.getClass().getCanonicalName());
            return true;
        } else {
            log.error("failed to register type " + loadable.getClass().getCanonicalName());
            return false;
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        log = event.getModLog();

        SkillBlockBreak skillBlockBreak = new SkillBlockBreak();
        skillBlockBreak.setDefault();
        register(skillBlockBreak);

        TraitBlockBreak traitBlockBreak = new TraitBlockBreak();
        traitBlockBreak.setDefault();
        register(traitBlockBreak);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
//        OBJS.forEach(Loadable::deserialize);
    }

    /**
     * Trigger the load function to allow for registry retrieval
     * e.g. log -> log object
     *
     * @param event
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        OBJS.forEach(Loadable::load);
        OBJS.forEach(Loadable::serialize);
        log.info("Loading finished");
    }

    public void serverStart(FMLServerStartingEvent event) {

    }

    public void serverStop(FMLServerStoppingEvent event) {
        Player.clear();
    }
}

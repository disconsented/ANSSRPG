package com.disconsented.jrpg;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = JRPG.MODID, name = JRPG.NAME, version = JRPG.VERSION)
public class JRPG {
    public static final String MODID = "jrpg";
    public static final String NAME = "JRPG";
    public static final String VERSION = "@VERSION@";

    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        logger.info("Hello, World!");
    }
}

package disconsented.anssrpg.core;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import disconsented.anssrpg.core.asm.ASMplayer;

@IFMLLoadingPlugin.Name(value = "ANSSRPGCore")
@IFMLLoadingPlugin.MCVersion(value = "1.7.10")
@IFMLLoadingPlugin.TransformerExclusions(value = "disconsented.anssrpg.core")
@IFMLLoadingPlugin.SortingIndex(value = 1001)
public class Coremod implements IFMLLoadingPlugin{
    

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"disconsented.anssrpg.core.asm.ASMplayer"};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}

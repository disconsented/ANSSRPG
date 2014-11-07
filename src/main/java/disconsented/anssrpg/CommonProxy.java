package disconsented.anssrpg;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.network.IGuiHandler;
import disconsented.anssrpg.gui.PerkGUI;
import disconsented.anssrpg.gui.PerkGUIContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{
	public void registerRenderers() {
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	     //return null; //If you use inventory slots this comes into play, I haven't experimented with how
		return new PerkGUIContainer();
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	   	return new PerkGUI();
	}
}
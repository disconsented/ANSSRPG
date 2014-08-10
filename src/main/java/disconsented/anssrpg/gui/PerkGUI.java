package disconsented.anssrpg.gui;

import scala.Console;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class PerkGUI extends GuiScreen {
	private static final ResourceLocation TEXTURE = new ResourceLocation("anssrpg", "textures/gui/gui_perk.png");
	public static final int GUI_ID = 1;
    @Override
    public void updateScreen() {
    	this.drawDefaultBackground();
    }

    @Override //Each frame
    public void drawScreen(int par1, int par2, float par3) {
    	this.drawDefaultBackground();
    	System.out.println("draw");
    }

    @Override //Opened and resized
    public void initGui() {
    	System.out.println("init");
    }

   /* @Override //Probably not needed
    protected void keyTyped(char par1, int par2) {

    }*/ 

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {

    }

    @Override
    protected void actionPerformed(GuiButton button) {

    }
}

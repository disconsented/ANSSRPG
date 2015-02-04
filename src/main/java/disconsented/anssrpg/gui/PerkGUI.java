/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package disconsented.anssrpg.gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import disconsented.anssrpg.Main;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.helper.Color;
import disconsented.anssrpg.network.Request;
import disconsented.anssrpg.perk.LocalPerk;

public class PerkGUI extends GuiScreen {
	public static final int GUI_ID = 1;
	private ArrayList <GuiButton> buttons = new ArrayList<GuiButton>(); //378 = status text field thingy
	private int selected = 0;
	private GuiTextField  status = new GuiTextField(fontRendererObj, 504, 392, 244, 18);
	private String descriptionCurrent = "dfghdfgjdbfgj";
	// Keeps track of the current page number to display
	private int pageNumber = 0; //15 items per page
	private int itemsPerPage = 15;
	private static ArrayList <LocalPerk> localPerks = new ArrayList<LocalPerk>();
	private static Settings instance = Settings.getInstance();
	//private FontRenderer thing = new FontRenderer();	
	//private Button next;

	public static void addPerk(LocalPerk e){
		localPerks.add(e);
		System.out.println("The perk " + e.name + " has been recieved by the client");
	}
	
	@Override 
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	void drawRectangle(int x, int y, int width, int height, int color){
    	GuiScreen.drawRect(x, y, width, height, color);
    }
	@Override //Each frame
    public void drawScreen(int par1, int par2, float par3) {
    	int xMod = 84;
    	int yMod = 48;
    	int border = 2;
    	this.drawDefaultBackground();
    	
    	//Black outline
    	drawRectangle(2*xMod-border, 2*yMod-border, 9*xMod+border, 9*yMod+border, 0xFF000000);
    	//Two triangles for the edges
    	drawTriangle(2*xMod, 2*yMod, 7*xMod, 7*yMod, 0xFFFFFF, 1);
    	drawTriangle(2*xMod, 2*yMod, 7*xMod, 7*yMod, 0x8B8B8B, 3);
    	//Overlay square
    	drawRectangle(2*xMod+border, 2*yMod+border, 9*xMod-border, 9*yMod-border, 0xFFC6C6C6);
    	
    	for (int i = 0; i < buttons.size()-3; i++){
    		if(i < localPerks.size()){
    			buttons.get(i+3).displayString = localPerks.get(i+(pageNumber*itemsPerPage)).name;
    		}
    		else{
    			buttons.get(i+3).displayString = "Null";
    		}
    	}
    	for (int i = 0; i < buttons.size(); i++){
    		buttons.get(i).drawButton(Minecraft.getMinecraft(), i, i);
    	}
    	//Text info box thingy
    	drawTriangle(6*xMod, 2*yMod+(border*2),(int)(3*xMod-border*2),(int)(6*yMod-border*2),Color.greyDeep,1);
    	drawTriangle(6*xMod, 2*yMod+(border*2),(int)(3*xMod-border*2),(int)(6*yMod-border*2),Color.black,3);
    	drawTriangle(6*xMod+border, 2*yMod+(border*3),(int)(3*xMod-(border*4)),(int)(6*yMod-(border*4)),Color.brownPaper,1);
    	drawTriangle(6*xMod+border, 2*yMod+(border*3),(int)(3*xMod-(border*4)),(int)(6*yMod-(border*4)),Color.brownPaper,3); 	
    	//Status box
    	drawTriangle(6*xMod,390,(int)(3*xMod-border*2),20,Color.greyDeep,1);  //378 = top Y
    	drawTriangle(6*xMod,390,(int)(3*xMod-border*2),20,Color.black,3);
    	drawTriangle(6*xMod+border,390+border,(int)(3*xMod-border*4),18-border,Color.greyDark,1);
    	drawTriangle(6*xMod+border,390+border,(int)(3*xMod-border*4),18-border,Color.greyDark,3);
//    	if(selected > 3){
    		descriptionCurrent = getCurrentPerk().description + getCurrentPerk().name;
//    	}
    	status.drawCenteredString(fontRendererObj, Settings.getInstance().getStatusMessage(), 626, 396, Color.white);
    	fontRendererObj.drawSplitString(descriptionCurrent, 6*xMod+border, 2*yMod+(border*3), (int)(3*xMod-(border*4)), (int)(6*yMod-(border*4)));   	
    }
    /**
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     * @param mode
     */
    void drawTriangle(int x, int y, int width, int height, int color, int mode){
     GL11.glEnable(GL11.GL_BLEND);
   	 GL11.glDisable(GL11.GL_TEXTURE_2D);
   	 GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
   	 Tessellator tes = Tessellator.instance;
   	 tes.startDrawing(GL11.GL_TRIANGLES);
   	 tes.setColorOpaque_I(color);
   	 switch (mode){
   	 case 1:
   		 tes.addVertex(x, y, 0); //top left
      	 tes.addVertex(x, y+height, 0); //Bottom left
      	 tes.addVertex(x+width, y, 0); //top right  
   		 break;
   	 case 2:
   		 break;
   	 case 3:
   		tes.addVertex(x+width, y, 0); //top right
   		tes.addVertex(x, y+height, 0); //Bottom left
   		tes.addVertex(x+width, y+height, 0); //bottom right
   		 break;
   	 case 4:
   		 break;
   	 }  	 
   	 tes.draw();
   	GL11.glEnable(GL11.GL_TEXTURE_2D);
	 GL11.glDisable(GL11.GL_BLEND);
    	
    }
    private LocalPerk getCurrentPerk(){
		if (selected > 2 && selected-3 < localPerks.size()){
			return localPerks.get(selected-3+(pageNumber*itemsPerPage));
		}else{
			return new LocalPerk("null", "null", 0, null);
		}
	}
    @Override //Opened and resized
    public void initGui() {		
    	super.initGui(); //I see this done a ton and I don't understand why
    	//buttonNext = new GuiButton(1,);
    	buttons.add(new GuiButton(0, 172, 410, 124, 20, "Previous"));
    	buttons.add(new GuiButton(1, 296, 410, 124, 20, "Next"));	
    	buttons.add(new GuiButton(2, 504, 410, 248, 20, "Go"));
    	int x = 3;
    	for (int i = 98;i < 393; i+= 20){
    		buttons.add(new GuiButton(x,172,i,248,20,"Stuff "+x));
    		x++;
    	}
    	x=0;
    	status.setCanLoseFocus(true);
    	status.setText("Status Message");
    	status.setFocused(false);
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
    	for (int i = 0; i < buttons.size(); i++){    		
    		if (buttons.get(i).mousePressed(Minecraft.getMinecraft(), par1, par2)){    			
    			if(buttons.get(i).id >= 3 && buttons.get(i).id <= 17){
    				if (buttons.get(i).enabled == true){
    					buttons.get(i).enabled = false;
    					buttons.get(selected).enabled = true;
    					selected = i;
    				}
    				else if(buttons.get(i).enabled == false && selected == i){    					
    					buttons.get(i).enabled = true;
    				}
    			}
    			else{
    				if(buttons.get(i).id >= 0 && buttons.get(i).id <= 2){
    					buttons.get(i).enabled = false;
    				}
    			}
    		} 			
    	}
    }
	@Override
    protected void mouseMovedOrUp(int a, int b, int c){
    	for (int i = 0; i < buttons.size(); i++){  
    		if(buttons.get(i).id >= 0 && buttons.get(i).id <= 2 && buttons.get(i).enabled == false){
    			buttons.get(i).enabled = true;
    			switch(i){
    			case 0://Prev
    				if (pageNumber > 1)
    				{
    					pageNumber--;
    				}
    				break;
    			case 1://Next
    				if (pageNumber < 1 && pageNumber < roundUp(localPerks.size()))
    				{
    					pageNumber++;
    				}
    				break;
    			case 2: //Go		
    				Main.snw.sendToServer(new Request(getCurrentPerk().perkSlug));
    				break;
    			}
    		}
    	}
    }
    private double roundUp(double x) {
		return (itemsPerPage * Math.ceil(x / itemsPerPage));
	}
    @Override
    public void updateScreen() {
    	this.drawDefaultBackground();
    }
}

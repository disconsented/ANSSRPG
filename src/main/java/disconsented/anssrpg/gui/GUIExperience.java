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

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import disconsented.anssrpg.common.Reference;
import disconsented.anssrpg.gui.components.ExpBox;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * @author Disconsented
 *
 */
public class GUIExperience extends GuiScreen {    
    private ArrayList<ExpBox> expBoxes;
    private int page;
    private int count;
    
    @Override
    public void initGui(){ //id,x,y,width,height,string
        this.buttonList.add(new GuiButton(0, 20, 40, 40, 20, "Next"));
        this.buttonList.add(new GuiButton(1, 20, 60, 40, 20, "Prev"));
        expBoxes = new ArrayList<ExpBox>();
        for (int i = 0; i < 6; i++){
            expBoxes.add(new ExpBox(80, 80 * i+1 , 35, 100, 135, "Example Name"));
        }
    }
    
    @Override
    public void drawScreen(int x1, int x2, float x3)
    {
        drawDefaultBackground();
        //System.out.println(texture.getResourcePath());
        int k;

        for (k = 0; k < this.buttonList.size(); ++k)
        {
            ((GuiButton)this.buttonList.get(k)).drawButton(this.mc, x1, x2);
        }
        
        for(ExpBox box : expBoxes){            
            box.draw();            
        }
        //new ExpBox(80, 80, 0, 0, 0, "Example Name").draw();
//        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//        
//        int xSize = 376;
//        int ySize = 306;
//        int x = (width - xSize) / 2;
//        int y = (height - ySize) / 2;
//        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
    
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    @Override
    protected void actionPerformed(GuiButton button) {
        switch(button.id){
        case 0:
            break;
        case 1:
            break;
        }
    }
    

}

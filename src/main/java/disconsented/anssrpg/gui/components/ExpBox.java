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
package disconsented.anssrpg.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import disconsented.anssrpg.common.Reference;

/**
 * @author Disconsented
 *
 */
public class ExpBox extends ComponentBase{
    //176x76
    private int x;
    private int y;
    public int expCurrent = 0;
    public int expRequired = 0;
    private float currentPercent = 0;
    public int expOld = 0;
    public int level = 0;
    public String name = "Not A Name";
    private ResourceLocation texture = new ResourceLocation(Reference.ID, "expbox.png");
    private ResourceLocation greenTexture = new ResourceLocation(Reference.ID, "green.png");
    
    /**
     * 
     * @param x
     * @param y
     */
    public ExpBox(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void draw(){
        this.calcPercentage();
        bindAndDrawTexture(texture,x,y,256,256);
//        * Name box -  9,9-106x12
        this.drawString(fontRenderer, name, this.x + 9, this.y + 11, 0xBEFF00);        
//        * Level box - 132,9-32x12
        this.drawString(fontRenderer, level+"->"+(level+1), 132 + this.x, this.y + 11, 0xBEFF00);  
//        * 0% - 15,38-20x12
        this.drawString(fontRenderer, "0%", 14 + this.x, this.y + 40, 0xBEFF00);  
//        * 100% - 144,38-20x12
        this.drawString(fontRenderer, "100%", 144 + this.x, this.y + 40, 0xBEFF00);  
//        * numerical exp box - 41,56-71x12
        this.drawString(fontRenderer, expCurrent+"", 41 + this.x, this.y + 58, 0xBEFF00); 
//        * percentile exp box - 118,56-20x12
        this.drawString(fontRenderer, currentPercent+"%", 118 + this.x, this.y + 58, 0xBEFF00); 
//        * bar box - 40,36-99x16
        bindAndDrawTexture(greenTexture, x+40, y+36, (int) currentPercent, 16);
    }

    public void calcPercentage(){
        if (this.expOld < 0)
        {
            this.expOld = 0;
        }
        int temp = ((expCurrent - expOld) * 100) / (expRequired - expOld);
        if(temp >= 0 && temp <= 100) {
            this.currentPercent = temp;
        } else {
            this.currentPercent = 0;
        }

    }
}

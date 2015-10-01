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
    private final int x;
    private final int y;
    public int expCurrent;
    public int expRequired;
    private float currentPercent;
    public int expOld;
    public int level;
    public String name = "Not A Name";
    private final ResourceLocation texture = new ResourceLocation(Reference.ID, "expbox.png");
    private final ResourceLocation greenTexture = new ResourceLocation(Reference.ID, "green.png");
    
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
        calcPercentage();
        this.bindAndDrawTexture(this.texture, this.x, this.y,256,256);
//        * Name box -  9,9-106x12
        drawString(this.fontRenderer, this.name, x + 9, y + 11, 0xBEFF00);
//        * Level box - 132,9-32x12
        drawString(this.fontRenderer, this.level +"->"+(this.level +1), 132 + x, y + 11, 0xBEFF00);
//        * 0% - 15,38-20x12
        drawString(this.fontRenderer, "0%", 14 + x, y + 40, 0xBEFF00);
//        * 100% - 144,38-20x12
        drawString(this.fontRenderer, "100%", 144 + x, y + 40, 0xBEFF00);
//        * numerical exp box - 41,56-71x12
        drawString(this.fontRenderer, this.expCurrent +"", 41 + x, y + 58, 0xBEFF00);
//        * percentile exp box - 118,56-20x12
        drawString(this.fontRenderer, this.currentPercent +"%", 118 + x, y + 58, 0xBEFF00);
//        * bar box - 40,36-99x16
        this.bindAndDrawTexture(this.greenTexture, this.x +40, this.y +36, (int) this.currentPercent, 16);
    }

    public void calcPercentage(){
        if (expOld < 0)
        {
            expOld = 0;
        }
        int temp = (expCurrent - expOld) * 100 / (this.expRequired - this.expOld);
        if(temp >= 0 && temp <= 100) {
            currentPercent = temp;
        } else {
            currentPercent = 0;
        }

    }
}

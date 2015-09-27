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

import disconsented.anssrpg.common.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Disconsented on 24/08/2015.
 */
public class ComponentStatusBook  extends ComponentBase{

    private ResourceLocation stat1 = new ResourceLocation(Reference.ID, "stat1.png"); //140x180
    private ResourceLocation stat2 = new ResourceLocation(Reference.ID, "stat2.png"); //140x180
    public static float currentHearts = 0;
    public static float currentSaturation = 0;
    public static float currentArmourHelmet = 0;
    public static float currentArmourChest = 0;
    public static float currentArmourLeggings = 0;
    public static float currentArmourBoots = 0;
    public static ArrayList<String> activePerks = new ArrayList<>();



    public ComponentStatusBook(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 256;
        this.height = 256;
    }


    /** Draws the background's first then draws the icon's with their value next to them
     *  Active perks are drawn on the right side after being spliced together
     */
    @Override
    public void draw() {
        bindAndDrawTexture(stat1, x-140, y-90, 140, 180);
        bindAndDrawTexture(stat2, x, y - 90, width, height);

        bindAndDrawTexture(stat1, x - 100, y - 30, 1, 188, 16, 16);//Heart
        fontRenderer.drawString(Float.toString(currentHearts), x - 90, y - 28, 0);

        bindAndDrawTexture(stat1, x - 100, y - 10, 18, 188, 16, 16);//Saturation
        fontRenderer.drawString(Float.toString(currentSaturation), x - 91, y - 8, 0);

        bindAndDrawTexture(stat1, x - 70, y + 65, 35, 188, 16, 16);//Boots
        fontRenderer.drawString(Float.toString(currentArmourBoots), x - 55, y + 68, 0);

        bindAndDrawTexture(stat1, x - 40, y + 10, 52, 188, 16, 16);//Leggings
        fontRenderer.drawString(Float.toString(currentArmourLeggings), x - 29, y + 16, 0);

        bindAndDrawTexture(stat1, x - 50, y - 30, 69, 188, 16, 16);//Chest
        fontRenderer.drawString(Float.toString(currentArmourChest), x - 36, y - 24, 0);

        bindAndDrawTexture(stat1, x - 70, y - 76, 86, 188, 16, 16);//Helmet
        fontRenderer.drawString(Float.toString(currentArmourHelmet),x-59, y-74,0);

        String joinedPerks = "";
        for (String string : activePerks){
            joinedPerks += string+"\n";
        }
        fontRenderer.drawSplitString(joinedPerks,x+4,y-78,123,0);

    }
}

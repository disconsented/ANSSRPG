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

    private final ResourceLocation stat1 = new ResourceLocation(Reference.ID, "stat1.png"); //140x180
    private final ResourceLocation stat2 = new ResourceLocation(Reference.ID, "stat2.png"); //140x180
    public static float currentHearts;
    public static float currentSaturation;
    public static float currentArmourHelmet;
    public static float currentArmourChest;
    public static float currentArmourLeggings;
    public static float currentArmourBoots;
    public static ArrayList<String> activePerks = new ArrayList<>();



    public ComponentStatusBook(int x, int y){
        this.x = x;
        this.y = y;
        width = 256;
        height = 256;
    }


    /** Draws the background's first then draws the icon's with their value next to them
     *  Active perks are drawn on the right side after being spliced together
     */
    @Override
    public void draw() {
        this.bindAndDrawTexture(this.stat1, this.x -140, this.y -90, 140, 180);
        this.bindAndDrawTexture(this.stat2, this.x, this.y - 90, this.width, this.height);

        this.bindAndDrawTexture(this.stat1, this.x - 100, this.y - 30, 1, 188, 16, 16);//Heart
        this.fontRenderer.drawString(Float.toString(ComponentStatusBook.currentHearts), this.x - 90, this.y - 28, 0);

        this.bindAndDrawTexture(this.stat1, this.x - 100, this.y - 10, 18, 188, 16, 16);//Saturation
        this.fontRenderer.drawString(Float.toString(ComponentStatusBook.currentSaturation), this.x - 91, this.y - 8, 0);

        this.bindAndDrawTexture(this.stat1, this.x - 70, this.y + 65, 35, 188, 16, 16);//Boots
        this.fontRenderer.drawString(Float.toString(ComponentStatusBook.currentArmourBoots), this.x - 55, this.y + 68, 0);

        this.bindAndDrawTexture(this.stat1, this.x - 40, this.y + 10, 52, 188, 16, 16);//Leggings
        this.fontRenderer.drawString(Float.toString(ComponentStatusBook.currentArmourLeggings), this.x - 29, this.y + 16, 0);

        this.bindAndDrawTexture(this.stat1, this.x - 50, this.y - 30, 69, 188, 16, 16);//Chest
        this.fontRenderer.drawString(Float.toString(ComponentStatusBook.currentArmourChest), this.x - 36, this.y - 24, 0);

        this.bindAndDrawTexture(this.stat1, this.x - 70, this.y - 76, 86, 188, 16, 16);//Helmet
        this.fontRenderer.drawString(Float.toString(ComponentStatusBook.currentArmourHelmet), this.x - 59, this.y - 74, 0);

        String joinedPerks = "";
        for (String string : ComponentStatusBook.activePerks){
            joinedPerks += string+"\n";
        }
        this.fontRenderer.drawSplitString(joinedPerks, this.x + 4, this.y - 78, 123, 0);

    }
}

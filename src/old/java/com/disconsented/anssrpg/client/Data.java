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
package com.disconsented.anssrpg.client;

import com.disconsented.anssrpg.server.network.PerkInfo;
import com.disconsented.anssrpg.server.network.SkillInfo;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Stores data that the client needs, populated as needed.
 *
 * @author Disconsented
 */
public class Data {
    public static HashMap<String, SkillInfo> skillInfo = new HashMap<>();
    public static ArrayList<SkillInfo> skillInfoList = new ArrayList<>();
    public static LinkedHashMap<String, PerkInfo> perkInfo = new LinkedHashMap<>(); //Ensures that perks are all unique, allows for easy overridng
    public static String statusMessage = "";
    public static GuiScreen screenToOpen = null;
    public static int ticksLeft = 0;


    private Data() {
    }
}

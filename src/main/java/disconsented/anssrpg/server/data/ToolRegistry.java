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
package disconsented.anssrpg.server.data;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;

/**
 * @author Disconsented
 * Contains tool -> class mappings
 */
public final class ToolRegistry {
    
    private static final HashMap<String, Class> registry = new HashMap<String, Class>();
    
    public static void init(){
        ToolRegistry.registry.put("Pickaxe", ItemPickaxe.class);
        ToolRegistry.registry.put("Spade", ItemSpade.class);
        ToolRegistry.registry.put("Sword", ItemSword.class);
        ToolRegistry.registry.put("Axe", ItemAxe.class);
        ToolRegistry.registry.put("Hoe", ItemHoe.class);
        ToolRegistry.registry.put("Shears", ItemShears.class);
        ToolRegistry.registry.put("Bow", ItemBow.class);
        ToolRegistry.registry.put("FlintAndSteel", ItemFlintAndSteel.class);
        ToolRegistry.registry.put("Hand", null);
        ToolRegistry.registry.put("", Item.class);
        ToolRegistry.registry.put("*", Item.class);
    }

    public static void setEntry(String key, Class value){
        ToolRegistry.registry.put(key, value);
    }
    
    public static Class getEntry(String key){
        return ToolRegistry.registry.get(key);
    }

}

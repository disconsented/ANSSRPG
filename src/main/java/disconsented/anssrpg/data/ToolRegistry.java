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
package disconsented.anssrpg.data;

import java.util.HashMap;

import net.minecraft.item.Item;

/**
 * @author Disconsented
 * Contains tool -> class mappings
 */
public final class ToolRegistry {
    
    private static HashMap<String, Class> registry = new HashMap<String, Class>();
    
    public static void init(){
        registry.put("Pickaxe", net.minecraft.item.ItemPickaxe.class);
        registry.put("Spade", net.minecraft.item.ItemSpade.class);
        registry.put("Sword", net.minecraft.item.ItemSword.class);
        registry.put("Axe", net.minecraft.item.ItemAxe.class);
        registry.put("Hoe", net.minecraft.item.ItemHoe.class);
        registry.put("Shears", net.minecraft.item.ItemShears.class);
        registry.put("Bow", net.minecraft.item.ItemBow.class);
        registry.put("FlintAndSteel", net.minecraft.item.ItemFlintAndSteel.class);
        registry.put("Hand", null);
        registry.put("", net.minecraft.item.Item.class);
        registry.put("*", net.minecraft.item.Item.class);
    }

    public static void setEntry(String key, Class value){
        registry.put(key, value);
    }
    
    public static Class getEntry(String key){
        return registry.get(key);
    }

}

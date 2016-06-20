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
/**
 *
 */
package disconsented.anssrpg.perk;

import java.util.ArrayList;

import disconsented.anssrpg.objects.INM;
import net.minecraft.item.Item;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.common.Logging;
import disconsented.anssrpg.objects.INME;

public class ItemPerk extends Perk {

    @Expose
    public ArrayList<INM> items = new ArrayList<>();

    public ItemPerk() {
    }

    public ItemPerk(String name, ArrayList<Requirement> requirements,
                    String description, int pointCost, ArrayList<INM> items) {
        super(name, requirements, description, pointCost);
        this.items = items;
    }

    @Override
    public void searchObject() {
        ArrayList<INM> initialised = new ArrayList<>();
        for(INM object : this.items){
            object.item = (Item)Item.itemRegistry.getObject(object.name);
            if (object.item != null){
                Logging.debug(object.name + " has been found. Passing on.");
                initialised.add(object);
            } else {
                Logging.error(object.name + " has not been found. Skipping");
            }
        }
        items = initialised;
    }
    
}

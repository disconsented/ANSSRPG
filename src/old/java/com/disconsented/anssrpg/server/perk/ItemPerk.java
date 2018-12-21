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
package com.disconsented.anssrpg.server.perk;

import com.google.gson.annotations.Expose;
import com.disconsented.anssrpg.server.common.Logging;
import com.disconsented.anssrpg.server.config.storage.INM;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class ItemPerk extends Perk {

    @Expose
    public ArrayList<INM> items = new ArrayList<>();

    public ItemPerk() {
    }

    public ItemPerk(String name, ArrayList<Requirement> requirements,
                    String description, ArrayList<INM> items) {
        super(name, requirements, description);
        this.items = items;
    }

    @Override
    public void init() {
        ArrayList<INM> initialised = new ArrayList<>();
        for (INM object : this.items) {
            object.item = (Item) Item.REGISTRY.getObject(object.resourceLocation);
            if (object.item != null) {
                Logging.debug(object.resourceLocation + " has been found. Passing on.");
                initialised.add(object);
            } else {
                Logging.error(object.resourceLocation + " has not been found. Skipping");
            }
        }
        items = initialised;
    }

}

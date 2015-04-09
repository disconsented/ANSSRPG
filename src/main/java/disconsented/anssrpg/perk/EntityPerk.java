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

import net.minecraft.entity.EntityList;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.common.Logging;
import disconsented.anssrpg.common.Pair;

public class EntityPerk extends Perk {

    @Expose
    public ArrayList<Pair> entities = new ArrayList<Pair>();

    public EntityPerk() {
        super();
    }

    public EntityPerk(String name, ArrayList<Requirement> requirements,
                      String description, int pointCost, ArrayList<Pair> entities) {
        super(name, requirements, description, pointCost);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void searchObject() {
        ArrayList<Pair> initalised = new ArrayList<Pair>();
        for(Pair object : entities){
            object.object = (Class) EntityList.stringToClassMapping.get(object.name);
            if (object.object != null){
                Logging.debug(object.name + " has been found. Passing on.");
                initalised.add(object);
            } else {
                Logging.error(object.name + " has not been found. Skipping");
            }
        }
        this.entities = initalised;    
    }

}

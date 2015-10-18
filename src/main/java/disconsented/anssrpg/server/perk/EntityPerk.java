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

import disconsented.anssrpg.objects.ENE;
import net.minecraft.entity.EntityList;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.common.Logging;

public class EntityPerk extends Perk {
    public EntityPerk(){}

    @Expose
    public ArrayList<ENE> entities = new ArrayList<ENE>();

    public EntityPerk(String name, ArrayList<Requirement> requirements,
                      String description, int pointCost, ArrayList<ENE> entities) {
        super(name, requirements, description, pointCost);
        this.entities = entities;
    }

    @Override
    public void searchObject() {
        ArrayList<ENE> initialised = new ArrayList<ENE>();
        for(ENE object : this.entities){
            object.entity = (Class) EntityList.stringToClassMapping.get(object.name);
            if (object.entity != null){
                Logging.debug(object.name + " has been found. Passing on.");
                initialised.add(object);
            } else {
                Logging.error(object.name + " has not been found. Skipping");
            }
        }
        entities = initialised;
    }

}

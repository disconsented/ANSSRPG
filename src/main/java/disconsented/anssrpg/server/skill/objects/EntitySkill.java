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
package disconsented.anssrpg.server.skill.objects;

import java.util.ArrayList;

import net.minecraft.entity.EntityList;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.server.common.Logging;
import disconsented.anssrpg.server.objects.ENE;

/**
 * @author Disconsented
 */
public class EntitySkill extends ToolSkill {
    
    @Expose
    public ArrayList<ENE> exp = new ArrayList<ENE>();

    @Override
    public void touchUp() {
        this.initTool();
        
        ArrayList<ENE> initialised = new ArrayList<ENE>();
        for (ENE object : this.exp) {
            object.entity = (Class) EntityList.stringToClassMapping.get(object.name);
            if (object.entity != null){
                Logging.debug(object.name+" has been found. Passing on!");
                initialised.add(object);
            } else {
                Logging.error(object.name+" could not be found. Ignoring!");
            }
        }
        this.exp = initialised;
    }
    
}
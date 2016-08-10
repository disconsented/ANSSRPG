/*
 * Copyright (c)  2015-2016, James Kerr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package disconsented.anssrpg.compat.tconstruct;

import com.google.gson.annotations.Expose;
import disconsented.anssrpg.core.server.common.Logging;
import disconsented.anssrpg.core.server.skill.objects.Skill;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

/**
 * Created by jpmke on 29/06/2016.
 */
public class SkillForge extends Skill {
    @Expose
    public ArrayList<MaterialDefinitionSkill> materialDefinitionSkillArrayList = new ArrayList<>();

    public SkillForge(String name, int base, float mod, ArrayList<MaterialDefinitionSkill> materialDefinitionSkillArrayList) {
        super(name, base, mod);
        this.materialDefinitionSkillArrayList = materialDefinitionSkillArrayList;
    }

    @Override
    public void touchUp() {
        ArrayList<MaterialDefinitionSkill> initialised = new ArrayList<>();
        for (MaterialDefinitionSkill object : materialDefinitionSkillArrayList) {
                if(!object.isMaterial){
                    object.item = (Item) Item.REGISTRY.getObject(new ResourceLocation(object.material));
                    if (object.item != null) {
                        Logging.debug(object.material + " has been found. Passing on.");
                        initialised.add(object);
                    } else {
                        Logging.error(object.material + " has not been found. Skipping.");
                    }
                } else {
                    Logging.debug(object.material + " is a material. Continuing.");
                }
        }
        materialDefinitionSkillArrayList = initialised;
    }
}
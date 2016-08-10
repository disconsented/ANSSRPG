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
import com.google.gson.annotations.SerializedName;
import disconsented.anssrpg.core.server.config.Container;
import disconsented.anssrpg.core.server.data.SkillStore;

import java.util.ArrayList;

/**
 * Created by jpmke on 29/06/2016.
 */
public class TiConContainer extends Container{
    @SerializedName("Perks")
    @Expose
    public final ArrayList<PerkForge> perkForgeArrayList = new ArrayList<>();

    @SerializedName("Skills")
    @Expose
    public final ArrayList<SkillForge> skillStoreArrayList = new ArrayList<>();

    public TiConContainer(){}

    @Override
    public void init() {
       fill();
    }

    void fill(){
        perkForgeArrayList.clear();
        skillStoreArrayList.clear();

        perkForgeArrayList.add(new PerkForge("Blacksmithing", null, "Example Tinkers Construct support perk. Allows for the use of iron.", new ArrayList<MaterialDefinitionPerk>(){{
            add(new MaterialDefinitionPerk("iron", false));
            add(new MaterialDefinitionPerk("diamond", false));
        }}));

        skillStoreArrayList.add(new SkillForge("Blacksmithing", 1, 1, new ArrayList<MaterialDefinitionSkill>(){{
            add(new MaterialDefinitionSkill("iron", 10, false));
            add(new MaterialDefinitionSkill("diamond", 100, false));
        }}));


    }
}

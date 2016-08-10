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

import disconsented.anssrpg.core.server.data.PerkStore;
import disconsented.anssrpg.core.server.data.SkillStore;

import java.util.ArrayList;

/**
 * Created by jpmke on 28/06/2016.
 */
public class TiConStore{
    private TiConStore() {
        super();
    }

    private static final ArrayList<PerkForge> perkForgeArrayList = new ArrayList<>();

    private static final ArrayList<SkillForge> skillStoreArrayList = new ArrayList<>();

    //Package local as I don't want this to be accessed elsewhere
   static void addPerk(PerkForge perkForge){
       PerkStore.addPerk(perkForge);
       perkForgeArrayList.add(perkForge);
   }

    static void addSkill(SkillForge skillForge){
        SkillStore.addSkill(skillForge);
        skillStoreArrayList.add(skillForge);
    }


}

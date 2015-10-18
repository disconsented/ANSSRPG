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
package disconsented.anssrpg.server.handler;

import disconsented.anssrpg.server.data.SkillStore;
import disconsented.anssrpg.server.skill.objects.Skill;

/**
 * @author Disconsented
 */
public class SkillHandler {
    
    public static double calculateExpForLevel(Skill skill, double level){
        return SkillHandler.calculateExpForLevel(skill.base, level, skill.mod);
    }

    public static double calculateExpForLevel(int base, double level, float mod){
        return base* Math.pow(level, mod);
    }
    
    public static long calculateLevelForExp(Skill skill, double exp){
        return (long) (Math.pow(exp, 1/skill.mod)/Math.pow(skill.base, 1/skill.mod));
    }

    public static long calculateLevelForExp(int base, float mod, double exp){
        return (long) (Math.pow(exp, 1/mod)/Math.pow(base, 1/mod));
    }
    
    public static Skill getSkill(String name){
        for(Skill skill : SkillStore.getSkills()){
            if(skill.name == name){
            return skill;
            }
        }
        return null;
    }
}

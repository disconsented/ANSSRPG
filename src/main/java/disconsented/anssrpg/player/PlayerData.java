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
package disconsented.anssrpg.player;
/**
 * @author James
 * For storing data about players
 */

import disconsented.anssrpg.handler.SkillHandler;
import disconsented.anssrpg.perk.Slug;
import disconsented.anssrpg.skill.objects.Skill;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerData {
    private ArrayList<Slug> perkList = new ArrayList<>();
    private ArrayList<Slug> activePerks = new ArrayList<>();
    private HashMap<String, Integer> skillExp = new HashMap<>();
    private String playerID;
    private int points;

    public PlayerData(ArrayList<Slug> perkList, HashMap skillExp, String playerID, int points) {
        this.perkList = perkList;
        this.skillExp = skillExp;
        this.playerID = playerID;
        this.points = points;
    }

    public ArrayList<Slug> getPerkList() {
        return this.perkList;
    }

    public void setPerkList(ArrayList<Slug> perkList) {
        this.perkList = perkList;
    }

    public String getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public HashMap<String, Integer> getSkillExp() {
        return this.skillExp;
    }

    public void setSkillExp(HashMap<String, Integer> skillExp) {
        this.skillExp = skillExp;
    }
    public int getSkillLevel(Skill skill){
        return (int) SkillHandler.calculateLevelForExp(skill, skillExp.get(skill.name));
    }

    public ArrayList<Slug> getActivePerks() {
        return this.activePerks;
    }
    
    /**
     * Used for permanent perks 
     * @param slug
     */
    public void addActivePerkSlug(Slug slug){
        activePerks.add(slug);
    }
    
    /**
     * Used for permanent perks 
     * @param slug
     */
    public void removeActivePerkSlug(Slug slug){
        activePerks.remove(slug);
    }
    

    public void setActivePerks(ArrayList<Slug> activePerks) {
        this.activePerks = activePerks;
    }

    @Override
    public String toString() {
        return this.playerID;
    }
}
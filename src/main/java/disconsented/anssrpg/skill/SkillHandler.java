/**
 * 
 */
package disconsented.anssrpg.skill;

import java.util.ArrayList;

import disconsented.anssrpg.Settings;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;

/**
 * @author j
 *
 */
public class SkillHandler {
	static ArrayList<Skill> skillList = new ArrayList<Skill>();
	
	public static void add(Skill object){
		skillList.add(object);
	}
	public static int skillCount(){
		return skillList.size();
	}
	public static int getXP(int blockIndex, int skillNumber){
		return (int) skillList.get(skillNumber).exp.get(blockIndex);
	}
	public static int indexOfBlock(Block block, int skillNum){
		int result = -1;
				for (int x = 0; x < skillList.get(skillNum).entryName.size() && result == -1; x++){
					if (skillList.get(skillNum).entryName.get(x).equals((block))){
						result = x;
					}
					else{
						if (Settings.getDebug()){
							System.out.println("Entry name:"+skillList.get(skillNum).entryName.get(x));
							System.out.println("Block:"+block);
						}
					}
				}
		return result;		
	}
	public static int indexOfEntity(Entity entity){
		int result = -1;
		for (int i = 0; i < skillList.size() && result == -1; i++){
			if (skillList.get(i).type == 2){
				for (int x = 0; x < skillList.size() && result == -1; x++){
					if (skillList.get(i).entryName.get(x).equals(entity)){
						result = x;
					}
					
				}
			}
		}
		return result;		
	}
	public static int indexOfItem(Item item){
		int result = -1;
		for (int i = 0; i < skillList.size() && result == -1; i++){
			if (skillList.get(i).type == 3){
				for (int x = 0; x < skillList.size() && result == -1; x++){
					if (skillList.get(i).entryName.get(x).equals(item)){
						result = x;
					}
					
				}
			}
		}
		return result;		
	}
	public static ArrayList getSkillList(){
		return skillList;
	}
	public static String getSkillName(int i) {		
		return skillList.get(i).name;
	}
	public static void constructSkill(String name, int type, ArrayList tempEntry, ArrayList tempExp) {
		switch(type){
		case 1:
			for (int i = 0;i < tempEntry.size(); i++){
				tempEntry.set(i, Block.blockRegistry.getObject(tempEntry.get(i)));
			}
			break;
		case 2:
			for (int i = 0;i < tempEntry.size(); i++){
				tempEntry.set(i, EntityList.stringToClassMapping.get(tempEntry.get(i)));
			}
			break;
		case 3:
			for (int i = 0;i < tempEntry.size(); i++){
				tempEntry.set(i, Item.itemRegistry.getObject(tempEntry.get(i)));
			}
			break;				
		}
		Skill temp = new Skill(tempExp, name, tempEntry, type);
		add(temp);
	}
}

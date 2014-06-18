/**
 * 
 */
package disconsented.anssrpg.data;

import java.util.ArrayList;

import net.minecraft.block.Block;

/**
 * @author j
 *
 */
public class SkillHandler {
	static ArrayList<SkillObject> skillList = new ArrayList<SkillObject>();
	
	public static void add(SkillObject object){
		skillList.add(object);
	}
	public static int skillCount(){
		return skillList.size();
	}
	public static int getXP(int blockIndex, int skillNumber){
		return (int) skillList.get(skillNumber).exp.get(skillNumber);
	}
	public static int indexOfBlock(int type, Block block){
		int result = -1;
		for (int i = 0; i < skillList.size() && result == -1; i++){
			if (skillList.get(i).type == type){
				for (int x = 0; x < skillList.size() && result == -1; x++){
					if (skillList.get(i).entryName.get(x).equals(block)){
						result = x;
					}
				}
			}
		}
		return result;		
	}
	public static void get(){
		
	}
	public static String getSkillName(int i) {		
		return skillList.get(i).name;
	}
}

package disconsented.anssrpg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
    public static ArrayList<SkillInfo> skillInfo = new ArrayList<SkillInfo>(); //Has to be static
	public static double experienceCurve;
	public static boolean debugInfo = true;
	static ArrayList names = new ArrayList();
	static ArrayList mainStore = new ArrayList();
	LinkedHashMap entityList = new LinkedHashMap();
	static File fileNameMain = new File("config/ANSSRPG","Main.cfg");
	static String line = null;
	private static void writeDefaultSkill(String name, byte type){
		File fileName = new File ("config/ANSSRPG", name.toLowerCase()+".cfg");
		try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("//Skill Name: "+name);
            bufferedWriter.newLine();
            bufferedWriter.write("//Skill Name: "+type);
            bufferedWriter.newLine();
            bufferedWriter.write("//the ; represents a new section");
            bufferedWriter.newLine();
            bufferedWriter.write("//Information must be structued like: (notice the lack of spaces after:)");
            bufferedWriter.newLine();
            bufferedWriter.write("//Entry name:tile.stone");
            bufferedWriter.newLine();
            bufferedWriter.write("//Entry experience:1");
            bufferedWriter.newLine();
            bufferedWriter.write("//Entry level requirement:0");
            bufferedWriter.newLine();
            bufferedWriter.write("Entry name:");
            bufferedWriter.newLine();
            bufferedWriter.write("Entry experience:");
            bufferedWriter.newLine();
            bufferedWriter.write("Entry level requirement:");
            bufferedWriter.newLine();
            // Note that write() does not automatically
            // append a newline character.

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '"+ fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}
	private static void writeDefaultMain(){
		try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileNameMain);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("//the ; represents a new section");
            bufferedWriter.newLine();
            bufferedWriter.write("// List of skillnames goes here (Make sure there is only one per line)");
            bufferedWriter.newLine();
            bufferedWriter.write("//And suffixed by -# where # is the skill type number");
            bufferedWriter.newLine();
            bufferedWriter.write("example skill name=1");
            bufferedWriter.newLine();
            bufferedWriter.write(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
            bufferedWriter.newLine();
            bufferedWriter.write("Debug info:false");
            bufferedWriter.newLine();
            // Note that write() does not automatically
            // append a newline character.

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '"+ fileNameMain + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}
	/**
	 * Handles the configuration files
	 */
	public static void loadAndSave(){
		ArrayList skillNames = new ArrayList();
		ArrayList entryName = new ArrayList();
		ArrayList entryExperience = new ArrayList();
		ArrayList entryRequirement = new ArrayList();
		ArrayList<Integer> type = new ArrayList<Integer>();
		int stage = 0;

		 try { // Main reader
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileNameMain);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
/*ignore comment lines 
 * get the skill names
 * substring them to seperate the names(between":" and "-" and the type (after "-"/ last char)
 * Get debug info
 * 
 */
	            while((line = bufferedReader.readLine()) != null) {
	            	if (!line.startsWith("/")){ //All ANSSRPG config files should start with a comment
	            		mainStore.add(line);
	            	}
	            }	

	            // Always close files.
	            System.out.println(mainStore);
	            bufferedReader.close();			
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + fileNameMain + "'");
	            	writeDefaultMain();
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" + fileNameMain + "'");					
	        }
		 for (int i = 0; i < mainStore.size(); i++){
			 if (!mainStore.get(i).toString().startsWith("/")){
				 if (mainStore.get(i).toString().contains("=")){ //If it contains - aka a skill name
					 names.add(mainStore.get(i).toString().substring(0, mainStore.get(i).toString().indexOf("=")));//Adding the skill name
					 type.add(Integer.parseInt(mainStore.get(i).toString().substring(mainStore.get(i).toString().indexOf("=")+1)) );
				 }
				 if (true){//If it contans : aka a setting
					 
				 }
			 }
		 }
		 System.out.println(names);
		 System.out.println(names.size());
		for(int i = 0; i < names.size()-1; i++){ // For the skills
			File fileName = new File ("config/ANSSRPG", names.get(i).toString().toLowerCase()+".cfg");
			int x = 0;
			 try {
		            // FileReader reads text files in the default encoding.
		            FileReader fileReader = 
		                new FileReader(fileName);

		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = 
		                new BufferedReader(fileReader);

		            while((line = bufferedReader.readLine()) != null) {
		            	if (!line.startsWith("/")){ //All ANSSRPG config files should start with a comment
		            		writeDefaultSkill((String) names.get(x), type.get(x).byteValue());
		            	}
		            	if (line.startsWith("/")){
		            	}
		            	else {
		            		switch (x){
		            		case 0:
		            			entryName.add(line);
		            			x++;
		            			break;
		            		case 1:
		            			entryExperience.add(line);
		            			x++;
		            			break;
		            		case 2:
		            			entryRequirement.add(line);
		            			x = 0;
		            			break;
		            		}
		            		
		            	}
		            }	

		            // Always close files.
		            bufferedReader.close();			
		        }
		        catch(FileNotFoundException ex) {
		            System.out.println(
		                "Unable to open file '" + fileName + "'");
		            writeDefaultSkill((String) names.get(i), type.get(i).byteValue());
		        }
		        catch(IOException ex) {
		            System.out.println(
		                "Error reading file '" + fileName + "'");					
		        }
			 skillInfo.add(new SkillInfo(entryExperience, entryRequirement, names.get(i).toString(), skillNames,  type.get(i).byteValue()));
		}
//This is just old code incase I want it
		/*try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }*/
    }
		
		 
		/*
		Configuration main = new Configuration(new File("config/ANSSRPG","Main.cfg"));
		Configuration perks = new Configuration(new File("main/ANSSRPG","Perk.cfg"));
		main.load(); 
		
		debugInfo = main.get(main.CATEGORY_GENERAL, "Debug info", false).getBoolean(false);
		experienceCurve = main.get(main.CATEGORY_GENERAL, "Player Experience curve", 1.3).getDouble(1.3);
		numberOfSkills = main.get(main.CATEGORY_GENERAL, "Number of skills", 8,"You will probably need to delete your main if you lower this").getInt();
		
		for (int i = 0; i < numberOfSkills; i++){    			
			skillNames.add(i,main.get("Base Skill", "Skill name "+i, "Default Name").getString());
			skillEntryCount.add(i, main.get("Base Skill", "Number entries per skill" +i, 1).getInt());
			type.add(i, (byte) main.get("Base Skill", "Skill Type" +i, 1, "1 = Block Breaking, 2 = Entity Damaging, 3 = Crafting").getInt());
		}
		
			for (int i = 0; i < numberOfSkills; i++){ //Iteration loop for each skill
				ArrayList exp = new ArrayList();
				ArrayList req = new ArrayList();
				ArrayList itemList = new ArrayList();

				switch ((int)type.get(i)){
				case 1: // Block breaking skills
					for (int o = 0; o < Integer.parseInt(skillEntryCount.get(i).toString()); o++){
						itemList.add( main.get((String) skillNames.get(i), "Block Name "+(o+1), "tile.").getString());//Name of blocks
						req.add(main.get((String) skillNames.get(i), "Block Level Requirment"+(o+1), 1).getInt());//Level requirement
						exp.add(main.get((String) skillNames.get(i), "Block Experience"+(o+1), 1).getInt());//Exp given
						}
					skillInfo.add(i,new SkillInfo(exp, req, skillNames.get(i).toString(), itemList, type.get(i)));
						break;
				case 2: // Entity killing skills
					for (int o = 0; o < Integer.parseInt(skillEntryCount.get(i).toString()); o++){
						exp.add(main.get((String) skillNames.get(i), "Entity Experience"+(o+1), 1).getInt());
						req.add(main.get((String) skillNames.get(i), "Entity Level Requirment"+(o+1), 1).getInt());
						itemList.add(main.get((String) skillNames.get(i), "Entity Name"+(o+1), "").getString());
					}
					skillInfo.add(i,new SkillInfo(exp, req, skillNames.get(i).toString(), itemList, type.get(i)));
					break;
				case 3: // Crafting skills
					for (int o = 0; o < Integer.parseInt(skillEntryCount.get(i).toString()); o++){
						exp.add(main.get((String) skillNames.get(i), "Item Experience"+(o+1), 1).getInt());
						req.add(main.get((String) skillNames.get(i), "Item Level Requirment"+(o+1), 1).getInt());
						itemList.add(main.get((String) skillNames.get(i), "Item Name"+(o+1), "item.").getString());
					}
					skillInfo.add(i,new SkillInfo(exp, req, skillNames.get(i).toString(), itemList, type.get(i)));
					break;    				
				}
				perks.load();
				perks.get((String) skillNames.get(i), "Block Name ", "tile.").getString();
				perks.save();

			}    		
		//JOptionPane.showMessageDialog(null,skillNames);
		main.save();
*/	}



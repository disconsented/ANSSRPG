package disconsented.anssrpg.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
public class RegisterWriter{

public static void Write(String entry){
	List<String> temp = null;
	String registryName = null;
	switch (entry.toLowerCase()){
	case "item":
		 temp = new ArrayList<String>(Item.itemRegistry.getKeys());
		 registryName = "item.registry";
		break;
	case "entity":
		temp = new ArrayList(Arrays.asList(EntityList.func_151515_b().toArray()));		;
		 registryName = "entity.registry";
		break;
	case "block":
		temp = new ArrayList<String>(Block.blockRegistry.getKeys());
		 registryName = "block.registry";
		break;
	}
	try {
        FileWriter fileWriter = new FileWriter(registryName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < temp.size(); i++){
        	bufferedWriter.write(temp.get(i));
        	bufferedWriter.newLine();
        }
    bufferedWriter.close();
    }
    catch(IOException ex) {
        System.out.println("Error writing to file '"+ "itemRegister.txt" + "'");            
         ex.printStackTrace();
    }
}
}
	

    package Disconsented.ANSSRPG;
     
    import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
     
    public final class PlayerInformation implements IExtendedEntityProperties {
           // private int currentXP;
            private Map experience = new HashMap();
            private double levelCurve = Main.experienceCurve;
            public final static String EXT_PROP_NAME = "PlayerInformation";
            private final EntityPlayer player;
           
            public static final PlayerInformation get(EntityPlayer player) {
                    return (PlayerInformation) player.getExtendedProperties(EXT_PROP_NAME);
            }
           
           
            public PlayerInformation(EntityPlayer player) {
            		NBTTagCompound entityTempTag = player.getEntityData();
            		NBTTagCompound persistTag = entityTempTag.getCompoundTag(player.PERSISTED_NBT_TAG);
                    this.player = player;
            }
           
            public static final void register(EntityPlayer player) {
                    player.registerExtendedProperties(PlayerInformation.EXT_PROP_NAME, new PlayerInformation(player));
            }
     
            @Override //Updates anything that needs to be updated each tick
            public void init(Entity entity, World world) {         
            }
     
            @Override
            public void saveNBTData(NBTTagCompound compound) {
            	Set mapSet = (Set) experience.entrySet();
                //Create iterator on Set 
                Iterator mapIterator = mapSet.iterator();
                if(Main.debugInfo == true){ 
                	System.out.println("Display the key/value of HashMap.");
                }
                while (mapIterator.hasNext()) {
                        Map.Entry mapEntry = (Map.Entry) mapIterator.next();
                        // getKey Method of HashMap access a key of map
                        String keyValue = (String) mapEntry.getKey();
                        //getValue method returns corresponding key's value
                        int value = (Integer) mapEntry.getValue();
                        if(Main.debugInfo == true){ 
                        	System.out.println("Key : " + keyValue + "= Value : " + value);
                        }
                        compound.setInteger(keyValue, value);
                }

            	
           
            }
     
          	 @Override
          	 public final void loadNBTData(NBTTagCompound compound) {            	
          		 if(Main.debugInfo == true){    
          			 System.out.println("NBT LOADED");
          		 }
          		 //this.currentXP = compound.getInteger("currentXP");   
          		 System.out.println(compound);
          	 }    
     
          	 public void addXP(int ammount, String skillName){
          		int currentXP;
          		if (experience.get(skillName) == null ){currentXP = 1;}
          		else {
          			currentXP = (Integer) experience.get(skillName);}
          		 this.experience.put(skillName, currentXP + ammount);
          	 }
          	 
          	 public void setXP(int ammount, String skillName){
          		 this.experience.put(skillName, ammount);
          	 }       
       
          	 public int getXP(String skillName){
          		 System.out.println("EXPTEST"+this.experience.get(skillName));
          		 if (this.experience.get(skillName) == null){this.experience.put(skillName, 1);}
          		 if ((Integer)this.experience.get(skillName) <= 0 || this.experience.get(skillName) == null)          		 {
          			 this.experience.put(skillName, 1);
          			 }
          		 return (Integer)this.experience.get(skillName);
          	 }
          	 
          	 public int getLevel(int xp){        	
          		 return (int) (Math.log10((double)xp)/Math.log10(levelCurve));
        }
    }
     


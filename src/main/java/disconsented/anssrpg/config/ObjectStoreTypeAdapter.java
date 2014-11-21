package disconsented.anssrpg.config;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.ItemPerk;

public class ObjectStoreTypeAdapter extends TypeAdapter {
	 
    //Write the ArralyLists with given names
    @Override
    public void write(JsonWriter out, Object obj) throws IOException {
    	Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    	ObjectStore os = (ObjectStore) obj;
        out.beginObject();
        out.name("items").value(gson.toJson(os.getItemPerks()));
        out.name("blocks").value(gson.toJson(os.getBlockPerks()));
        //And so forth with the other arraylists (Might have to do a out.beginArray(), and then gson.ToJson(os.get*perks())?
        out.endObject();
    }
    @Override
    public ObjectStore read(JsonReader in) throws IOException {
        final ObjectStore os = new ObjectStore();
        ArrayList<ItemPerk> items = null;
        ArrayList<BlockPerk> blocks = null;
        in.beginObject();
        Gson gson = new Gson();
        while(in.hasNext()) {
            switch(in.nextName()) {
                case "items":
                    in.beginArray();
                    while(in.hasNext()) {
                        items.add(gson.fromJson(in.nextString(), ItemPerk.class));
                    }
                    in.endArray();
                break;
                case "blocks":
                     in.beginArray();
                     while(in.hasNext()) {
                         blocks.add(gson.fromJson(in.nextString(), BlockPerk.class));
                     }
                     in.endArray();
                 break;
                //Rest of reading of arraylists  here
            }
         }
       in.endObject();
       os.setItemPerks(items);
       os.setBlockPerks(blocks);
       // Set rest of the ArrayLists on ObjectStore os
       return os;
    }

}
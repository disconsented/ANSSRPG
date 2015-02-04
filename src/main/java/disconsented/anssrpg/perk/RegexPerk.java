package disconsented.anssrpg.perk;

import com.google.gson.annotations.Expose;

public abstract interface RegexPerk{
    
    @Expose 
    String searchQuery = "default_searchQuery";
}

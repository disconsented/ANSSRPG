package disconsented.anssrpg.perk;

import com.google.gson.annotations.Expose;

public abstract class RegexPerk extends Perk{
    
    @Expose 
    public String searchQuery = "default_searchQuery";
}

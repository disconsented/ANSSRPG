#I am always looking for help and feedback
ANSSRPG is an open source player progression framework built for Minecraft Forge and Cauldron servers. 
It (currently) features 3 skill archetypes that allow the players to gain experience through their actions.
Players are also able to obtain “perks” which allow them to unlock actions related to skills (in the future it will be expanded to provide user’s ability’s) 
Skills and perk's are user defined, so using this in a modpack is highly recommended

Contacting me via IRC is the best way to get in touch, I might not read your message immediately but I will eventually 
http://webchat.esper.net/?nick=&channels=Disconsented



Quest's are beyond the scope of this mod (no point reinventing the wheel) but if a modder request’s an API to help make my mod work with theirs I am more than happy to do it. 

Skills and Experience a very closely linked (as you would expect). Each "Skill" falls under one of (currently) three archetypes (Block Breaking, Crafting and Entity damaging for now). During configuration you assign each skill (all of which are end user defined) an archetype.

You then need to input the list of entry names and associated experience values into the skills config file. 

Perks (think fallout) are how I am going to handle gating and "ability’s". All 3 Archetypes are gatable. Block breaking simply doesn’t break the block, Entity’s being attacked will have incoming damage reduced to 1 and crafting will just close the GUI. As for obtaining them, the GUI which I showed in the thread earlier.
To stop people obtaining all perks from the get go they normally will have an associated skill requirement and point cost (tentative). I am still theory crafting ideas for obtaining points but some of the ideas that have been suggested include a “general” experience and then awarding points based of that, gameplay time based or by achievements (I am currently exploring this)

(N.B. This is all theory crafting) Ability’s will come in two flavours active, toggleable and passive, to help with intermod compatibility I have decided to use potion effects (If I go with this route I have plans to release a companion mod with additional effects). Multiple effects can be applied at once so you can create so more interesting effects (Speed boost but you have Hunger at the same time) 

Stat points like strength, dexterity etc are not within the scope of this mod (I find them silly and boring) 

As for additional archetypes, I am just stumped on what to add that won’t be used for bland one off skills. (Hence my hesitance for breeding, fishing etc) 
The big theme with this mod is _User Definitions_ I won’t be (initially) including any default skills, perks etc. I want to encourage the community to create those and share them with everyone else (it is makes this pretty awesome for servers and modpacks)
(Now that I have actually written this out I need to update github)

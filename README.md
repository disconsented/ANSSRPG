#ANSSRPG
## What it is:
ANSSRPG is a mod for Minecraft Forge that provides players, modpackers and server admins the ability to develop their own unique RPG system  with player progression without any programming knowledge needed.
## How it works:
ANSSRPG boils down to two fundamental concepts.
###Skills:
Primarily used to award a player with experience for doing things. For example you can create a 'Mining' skill that provides players with experience for mining ores. For example Iron Ore for 5 experience and Diamond Ore for 50 experience. Skills follow a few preset types but can be combined to create compound skills. All skills have a failure state which allows progression to be handled via the mod (a 'digging' skill will not allow a player to mine a block).

###Perks:
Perks come in two flavours: Unlocks and Effects.

Unlock Perks allow failure states to be represented. If a perk if present and the player does not have it they will trigger the failure state, if they do have it however the failure state will _not_ be triggered. If there are no unlock perks defined for 'x' then there will not be a failure state for it.

Effect Perks allow for effects (potion effects at this time) to be applied to players [among other things](https://github.com/disconsented/ANSSRPG/wiki/Action-perks-design-doc).

##[Guide](/guide.rst)

##How to report a issue or get help
1. Read the wiki and other resources for the mod.
2. Read: [How to report a bug](http://www.chiark.greenend.org.uk/~sgtatham/bugs.html).
3. Using a service like [Hastebin](http://hastebin.com) (no Dropbox) please upload:
 1. Any crash reports.
 2. Console logs (both server.log and forge mod loader(fml) logs).
 3. All Config files .
4. Create a github issue with the following information:
 * Description of the issue:
 * Steps to reproduce it (How do I make this happen?):
 * Steps taken to resolve it (Did I fix it? If so, How?):
 * The uploaded logs from step 3.

NOTE: If you're looking for help via IRC read [this](https://workaround.org/getting-help-on-irc).

After reading that, you can find me in #disconsented on the Espernet IRC [(or click here)](https://kiwiirc.com/client/irc.esper.net/?#disconsented).

##Builds
Master: [![Build Status](https://travis-ci.org/disconsented/ANSSRPG.svg?branch=master)](https://travis-ci.org/disconsented/ANSSRPG)

Dev: [![Build Status](https://travis-ci.org/disconsented/ANSSRPG.svg?branch=DEV)](https://travis-ci.org/disconsented/ANSSRPG)

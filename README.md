#JRPG (JSON RPG)
JRPG (formerly ANSSRPG) is an Minecraft Forge mod aimed at letting modpack builders create an RPG experience with little more than JSON. This mod assumes that you already have experience with basic RPG concepts and JSON since you need to configure everything by hand..
Eventually this repo will also contain sets of example config files but until then it is likely best avoided.

## Primary concepts
JRPG fundamentally revolves around two concepts: skills and traits.
### Skills
Skills re any action that can be construed to the actions of a single player E.G. mining, farming, forging weapons or slaying monsters however they cannot be anything that cannot be pinned down to the actions of a single player (smelting).

### Traits
Traits encompass a bit more than skills but are a related concept, essentially they are how you stop a player from doing something until they earn it. 
For example say a trait for mining diamonds exists, a player will not be able to mine it until they have gained that trait.

Traits current only exist to lock and unlock content for a player, once the mod is in an acceptable state and it has been properly designed then there will be traits that directly impact players and the world around them.

## What these mean together
Through the use of both Skills and Traits an RPG can be tailor made for a modpack or server. 
There are a few tricks not mentioned above that are important for getting the most out of the mod:
- Experience is awarded to named pools, meaning that multiple skill definitions can aware experience into common pools.
- Traits can require other perks to be unlocked, this combined with titles in order to create concepts like classes or jobs.

## Getting help
0. Please check the issue tracker so we don't have duplicate issues
0. Please check the wiki for answers

If neither provide you the answer you can:
0. Open an issue
0. Find me on [#disconsented on irc.esper.net](https://kiwiirc.com/client/irc.esper.net/?#disconsented). 
0. Add me on discord (Disconsented#3405)

##Builds
Master: [![Build Status](https://travis-ci.org/disconsented/ANSSRPG.svg?branch=master)](https://travis-ci.org/disconsented/ANSSRPG)
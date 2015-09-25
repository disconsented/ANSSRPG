.. I like reStructuredText :)
How to install.
===============

1. Install `Minecraft Forge <http://www.minecraftforge.net/wiki/Installation/Universal#Post-1.6.1_Minecraft_Forge_.28using_installer.29>`_.
2. Download and install the latest stable release of ANSSRPG.
3. Place ANSSRPG into your ``/mods/`` folder.


How to install external configs.
================================

1. Have you started `How To configure ANSSRPG <#how-to-configure-anssrpg>`_? If so continue to step 2.
2. If your zipped config files contains ``anssrpg.cfg`` move to step 4.
3. Open up ``anssrpg.cfg`` (``/config/anssrpg.cfg``) and change ``B:useExternalConfig=false`` to ``B:useExternalConfig=true``.
4. Unzip into ``/config/``.
5. ???
6. Play.

..

    Please use an application like `Notepad++ <https://notepad-plus-plus.org/>`_ instead of regular Notepad, Wordpad or Word.


Commands.
=========

``/anssrpg [#]``
Shows a GUI associated with the number proceeding the command.
#0 Experience GUI.
#1 Perk GUI.
#2 Status GUI.

* ``/perk [list|obtain|getinfo|convertpoints|activate]``
- list [current|all] 
    
* ``current`` lists all perks that are currently obtained by the player. ``all`` lists all perks loaded into the game.

    - obtain [perkslug]
        Attempts to grant the calling player the perk associated with the slug.
    - getinfo [perkslug]
        Returns information about the perk.
    - covertpoints [#]
        When enabled it will convert # general experience into perk points.
    - activate [perkslug]
        Will attempt to activate the associated perk.

* ``/skill``
Returns information about all of your skills.

How to configure ANSSRPG.
=========================

1. Plan out your configs before hand.
2. Change useExternalConfig as described above.
3. Read this `Json Primer <http://guide.couchdb.org/draft/json.html>`_.
4. Copy and adjust existing configs to your needs following the rules laid out below.

* All fields need to be filled out (however you're allowed to have a value of 0 or "" where appropriate I.E. metadata, pointcost, tool).
* Multiple skill definitions with the same name are considered compound (experience gained is shared) but they 
* Skills do *not* require perk definitions.
* Block,item,entity definitions do *not* require skills.
* Where a perk is present with a game object (block,item,entity), if the player fails to have it they will be unable to fulfil the action.

Perk types.
===========

* Block.
Triggered when a player mines a block. Upon failure: Block is not mined.

* Entity.
Triggered when a player attacks an entity. Upon failure: Entity's incoming damage is reduced to 1 and it will not drop items on death.

* Item.
Triggered when a player attempts to craft with a vanilla crafting table or personal 2x2 crafting grid. Upon failure: Crafting GUI will close.

* Title.
Does not have a trigger, exists only to be used as a requirement for other perks I.E. create classes.

* Self Potion.
Triggered when activated via command, if successful will apply the associated effects.
Has 4 unique fields:

    1. Effects list containing information about the potion effects id:potion id, amplifier:potion amplifier, duration:duration in ticks.
    2. Repeat, determines if the effect will run more than once.
    3. Cycle how often the effects will be applied.
    4. Max cycles (only makes sense with ``"repeat": true``) after this is exceeded the perk will not longer repeat.


Skill types.
============

* Block.
Gives experience on mining blocks.

* Entity.
Gives experience on killing an entity(mob's including zombies,sheep etc.).

* Item.
Gives experience when crafting am item.
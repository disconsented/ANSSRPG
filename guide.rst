.. I like reStructuredText :)
How to install.
===============

1. Install `Minecraft Forge <http://www.minecraftforge.net/wiki/Installation/Universal#Post-1.6.1_Minecraft_Forge_.28using_installer.29>`_.
2. Download and install the latest stable release of ANSSRPG.
3. Place ANSSRPG into your ``/mods/`` folder.

---------------------------------

How to install external configs.
================================

1. If your zipped config files contains ``anssrpg.cfg`` move to step 3.
2. Open up ``anssrpg.cfg`` (``/config/anssrpg.cfg``) and change ``B:useExternalConfig=false`` to ``B:useExternalConfig=true``.
3. Unzip into ``/config/``.
4. ???
5. Play.

---------------------------------

Commands.
=========

``/anssrpg [#]``
Shows a GUI associated with the number proceeding the command.
#0 Experience GUI.
#1 Perk GUI.
#2 Status GUI.

``/perk [list|obtain|getinfo|convertpoints|activate]``
- list [current|all] 
``current`` lists all perks that are currently obtained by the player. ``all`` lists all perks loaded into the game.
- obtain [perkslug]
Attempts to grant the calling player the perk associated with the slug.
- getinfo [perkslug]
Returns information about the perk.
- covertpoints [#]
When enabled it will convert # general experience into perk points.
- activate [perkslug]
Will attempt to activate the associated perk.

``/skill``
Returns information about all of your skills.

--------------------------------

How to configure ANSSRPG.
=========================

1. Plan out your configs before hand.
2. Change useExternalConfig as described above.
3. Read this `Json Primer<http://guide.couchdb.org/draft/json.html>`_.
4. Copy and adjust existing configs to your needs following the rules laid out below.

* All fields need to be filled out (however you're allowed to have a value of 0 or "" where appropriate I.E. metadata, pointcost, tool).
* Multiple skill definitions with the same name are considered compound (experience gained is shared) but they 
* Skills do *not* require perk definitions.
* Block,item,entity definitions do *not* require skills.
* Where a perk is present with a game object (block,item,entity), if the player fails to have it they will be unable to fulfil the action.
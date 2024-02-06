## 1.20.4-0.5.7+bu——2024/02/06

### Update:

-
	- The world generator of 'SR Snapshot' has been updated. Now the boundary of 'Creepy Spider Forest' has a noise transition, which temporarily reduces the
	  problem of excessive difficulty for players to enter the biome.

## 1.20.4-0.5.6+bf——2024/01/27

### Fixes:

- Fixed the issue where getCodec() and codec() of the chunk generator use the same method name.

## 1.20.4-0.5.5+bu——2024/01/26

### Update:

- Register world upgrader for 'SR Snapshot' world preset.
- Major changes to the spider biome generation rules, first tested on the "SR Snapshot" world preset.

## 1.20.4-0.5.4+bu---2023/12/18

### Update:

- Added the block 'Block of Copper For Smelting Ingot'.
- Added the block 'Block of CuFe'.
- Added the block 'Block of AuCu'.
- Added the block 'Block of Pig Iron'.
- Added advancement 'New Rotten Flesh'.
- Added advancement 'Spider Camouflage'.
- Added a death message to the 'acidize' effect.
- Added a death message for being killed by Spray Poison Spider's toxin.

### Change:

- Logic
	- Changed the entity contact judgment of 'gossamery leaves'. Now entities touching the sides will also have a deceleration effect.
	- Changed the id of 'Block of CuFe Alloy', now it is spontaneous-replace:cufe_block.
	- Changed the id of 'Block of AuCu Alloy', now it is spontaneous-replace:cufe_block.

### Fixes:

- Logic
	- Fixed the triggering condition of 'Alloycraft' advancements. Obtaining 'Refined Copper Ingot' will not trigger this advancements now.
- Client
	- Fixed tool third-person model display issue.

## 1.20.4-0.5.3+bf 2023/12/09

### Bug Fixes

- Client
	- Fixed an issue where mod log reading was overwritten by silk api logs.

v1.20.4-0.5.2+bp 2023/12/08

- 'Spontaneous-Replace' has been updated to 1.20.4, and the mod icon has been remake.
- 'Spontaneous-Replace' uses the latest fabric mod architecture to reconstruct the entire mod. For example, the logical layer is separated from the physical
  layer, and a data generator is used to generate all json, etc., which greatly improves the code quality and solves the source of most bugs.
- 'Spontaneous-Replace' has sorted out most of the generalizable interfaces and hooks, and rewritten it into a new mod 'Silk API'. 'Silk API' is a new
  generation fabric library that is open source and can be used by other fabric mods, and 'Spontaneous-Replace' itself also requires 'Silk API' as a dependency.
- The version number naming mechanism has been modified, now changed to [mc version]-(mod version)+<update explanation field>.
- Mod id changed from spontaneous_replace to spontaneous-replace.
- Fixed the bug that requires Mod Menu as the dependency.
- Optimized the movement modification of the mod crossbow to make it smoother.
- The alloy forging die has been deleted, alloys can now be craft directly at the smithing table, and decorations can be added to alloy equipments.
- Modified the triggering conditions and content of some advancements, and deleted some unnecessary advancements.
- Modified the data of some equipment and tools so that they can be changed according to the data of the original equipment and tools, making it more balanced.
- Simplified the characteristics of some mod items or blocks to make them simpler, more direct, and easier to understand.
- Modified some textures to make them more suitable for Minecraft.
- Added a new status effect 'Acidize', its effect is similar to 'Poison', but it can cause death.
- Modified the world preset id, now changed to spontaneous-replace:classic and spontaneous-replace:snapshot.

v0.5.1-0.5b.1f+1.20.1 2023/08/08

	1. Fix a bug where a sapling can't grow
	2. Fixed a bug caused by the loss of the tags folder in versions 1.19.4 and below
	3. Fixed a bug in 1.18.2 that failed to create a new world (old save failed as normal)

v0.5.0-0.5b.0f+1.20.1 2023/07/31

	1. As the refactored new architectural code is not supported, the 1.19 ~ 1.19.1 release no longer supports subsequent updates as of today
	2. Update the temporary Mod Biome 'Spider Swamp' to 'Creepy Spider Forest'. Adding biome natural blocks, eerie tree and Treacherous Tree and their series blocks, new spider variant 'Spider Larva'
	3. Minecraft's vanilla biome will no longer produce spider variants, which will only be created in the creepy spider forest
	4. Change the spawn spider of spider egg cocoon from three large spider to three spider larvas
	5. Change version 1.19.4 and above to the default world 'Spontaneous-Replace'. Now you don't need to manually switch the world preset. 'SR Temporary' will be replaced with 'SR Snapshot'
	6. Continue to improve the mod code structure and configuration file structure
	7. Added zooming actions to the 1.18.2 animation system to improve the problem that zooming was not supported in the vanilla ModelPart
	8. Fixed an open folder crash in Mod due to system incompatibility
	9. Fixed an issue where pressing ESC on the mod screen returned the title interface directly
	10. Fixed a problem with Ju-ger repeating crossbow drop models that were too large
	11. Now AuCu, CuFe, steel block can activate the beacon
	12. Add the block tag spontaneous_replace:cobwebs, where the blocks are broken 15 times faster with swords and scissors
	13. Add the block tag spontaneous_replace:eerie_rinds, in which the block is identified as an eerie wooden shell
	14. Add the biome tag spontaneous_replace:is_spider_biome, and the biome in this tag was identified as spider biome

v0.4.8-0.4i.8p+1.20.1 2023/07/13

	1. Refactor the entire project code structure to improve code readability and slightly improve performance
	2. Refactor armor and tools to make them easier to write  and slightly improve performance
	3. Optimize the initialize() function of the mod spider parent class to fix bugs in a non-development environment
	4. Optimize the mod button page jump, change to common code, make the system other than Windows also available
	5. Optimize the display of the mod button. The position of the SR button will now change with the button on the modmenu
	6. Add localized mod name and optimize the description text

v0.4.7-0.4i.7f+1.20.1 2023/06/24

	1.  Fix the registration error that occurred when the server was started
	2.  Rearranges optimizes the server side and the client side registration content, enhances the performance
	3.  The fix optimizes the update system to make it more complete and usable

v0.4.6-0.4i.6f+1.20.1 2023/06/18

	1.  Fixed an unlimited update prompt problem in 1.20 and 1.20.1

v0.4.5-0.4i.5u+1.20.1 2023/06/18

	1.  Spontaneous-Replace now SUPPORT Minecraft 1.20 and 1.20.1!!!!
	2.  Add smithing template to fit new 1.20 smithing system
	3.  Add spontaneous-replace new metal related building decoration block
	4.  Improved Update System MC version judge function

v0.4.4-0.4i.4u+1.19.4 2023/06/14

	1.  Add check update tips and view changelog tips
	2.  Modify the Website Link to change CurseForge Website to Modrinth Website
	3.  Refactoring the code and rewriting the package name to conform to the Java language standard writing specification
	4.  Removed Mixin code that uses @Redirect, improving mod compatibility of enchantments related code

v0.4.3-0.4i.3d+1.19.4 2023/05/30

	1.  Modified the crafting recipe for Compact String, reducing the consumption of Compact Gossamer and increasing the consumption of string to reduce the difficulty of crafting.
	2.  Modified the spider egg cocoon drops to add Compact Gossamer. Make Compact Gossamer easier to obtain, slightly enhance the balance.
	3.  Modified the drop probability of spider variants' special drops from 1/5 to 1/3.
	4.  Modified some of the ranged equipment advancements to make them more meaningful or fun.
	5.  Modified the crafting recipe unlock conditions for some ranged weapons, now the conditions for showing the recipe table for some ranged weapons are more demanding.
	6.  Modified the Features of the Ju-ger repeating crossbow and the marks-crossbow, now these two crossbows will not be burned.
	7.  Modified the knockback distance of stoneball, now the knockback distance of stoneball is twice as far as before.
	8.  Modified the firing of special projectiles, now the dispenser can also fire stoneball and full power steel arrow.
	9.  Modified the can not enchanting's enchantments of the Ju-ger repeating crossbow from 'Quick Charge' to 'Piercing', reorientation of the balance of the  Ju-ger repeating crossbow.
	10.  Modified the ammo display of the Ju-ger repeating crossbow to show the actual number of arrows with "Multishot" if there are 'Multishot'.
	11.  Changed the English translation of 'JuGer repeating crossbow' to 'Ju-ger Repeating Crossbow' again.
	12.  Changed 'Update Log' to 'Changelog'.

v0.4.2-0.4i.2r+1.19.4 2023/05/26

	1.  Spontaneous-Replace now SUPPORT Minecraft 1.18.2!!!!
	2.  Repaired item status display error for special bows& crossbows
	3.  Repaired unexpected texture error in some armors
	4.  Repaired JuGer repeating crossbow being fully loaded in survival mode even if there is only one arrow
	5.  Repaired a rendering issue in first person with shaders on
	6.  Repaired the spider hurt animation issue in 1.19.3 and below
	7.  Repaired arbalest firing issue in 1.19.2 and below

v0.4.1-0.4i.1u+1.19.4 2023/05/20

	1.  Repaired an error that could cause the return code -805306369 (0xCFFFFFFF)
	2.  Repaired and optimized JuGer repeating crossbow's item model
	3.  Modified the texture orientation of the slingshot
	4.  Redrawn the textures of compound bow and marks-crossbow
	5.  Optimized the using movement effect of marks-crossbow and the loading animation of JuGer repeating crossbow
	6.  Optimized the layout format of the update log
	7.  Optimized the translation of various languages
	8.  Added new advancements on new ranged equipment

v0.4.0-0.4i.0+1.19.4 2023/05/06

	1.  Add a note to the module configuration file, the previous module configuration file has expired, please select the option again
	2.  Restructure the progress system, the previously achieved progress needs to be re-achieved
	3.  Improve the compatibility between mods below version 1.19.4 and Disable Custom Worlds Advice, now installing these two mods at the same time will not crash anymore
	4.  Deleted the eating animation of "Spontaneous-Replace" food for later update
	5.  Add new equipment "Arrowproof Vest", which can greatly reduce the damage to projectiles
	6.  Add various new remote equipment for play

v0.3.1-0.3b.1r+1.19.4 2023/04/17

	1. Modified the synthesis of "Spider Fang", now the synthesis result is two 5s arrow of poison
	2. Modified the FEATURE that cannot be synthesized by changing the position of the item in the smithing table, and now there is no order of placement
	3. Repaired the display problem of the "Spider Camouflage" status effect in versions below 1.19.4
	4. Modified the materials of "Sticky-Compact Gossamer", "Composite String" and "Composite Fabric"
	5. The translation of "丝弦" is changed to "丝线"

v0.3.0-0.3b.0+1.19.4 2023/04/13

	1. Update the spider chrysalis and add it to the "Spider Swamp" biome. The spider chrysalis  cannot be silk touch due to gameplay considerations
	2. "粘密蛛网"(Sticky Compact Cobweb) was renamed "黏密蛛网" according to modern Chinese norms, added corresponding drops and added to the "Spider Swamp" biome
	3. Update "Spider Swamp", modify the refresh rate of special blocks and add Spider Chrysalis and Sticky Compact Cobweb
	4. Update "Spider Egg Cocoon", now if you wear a single piece of spider leather SRArmor, the trigger range is reduced to 4.5 blocks and any break except command will spawn spiders. And fixed the problem that there is no sound after the egg cocoon is triggered
	5. Add "Spider Leather Cap", if equipped with "spider leather tunic" at the same time, you will get the special status effect "spider camouflage"
	6. Add other items to prepare for the 0.4.0 version update

v0.2.2-0.2i.2u+1.19.4 2023/04/04

	1. Update the Sound FX when the Spider Leather Tunic is equipped to make it hiss

v0.2.1-0.2i.1r+1.19.4 2023/04/01

	1. Repaired the wrong death tip problem when killed by the Spray Poison Spider
	2. Repaired the height misalignment of the Realms widget
	3. Adjusted the placement judgment mechanism of spider egg cocoon and spider chrysalis, now spider egg cocoon and spider chrysalis can be placed on leaves
	4. Updated the eating animation of SR food, now there will be model changes when eating SR food

v0.2.0-0.2i.0+1.19.4 2023/03/30

	1. Added license and current mod version info to the mod interface
	2. Reset and added item group's items and arrangement
	3. Added the general drops and special drops of three special spider variants. 20% chance for special drops
	4. Added derivatives and equipment of three special spider variant drops

v0.1.4-0.1.4r+1.19.4 2023/03/22

	1. Repair the issue of incorrect discord official community links on all pages
	2. Repair garbled code caused by incorrect encoding settings in the Chinese log interface

v0.1.3-0.1.3u+1.19.4 2023/03/22

	1. Added a new Mod home page that conforms to the new version 1.19.4 and deleted the original Mod config button
	2. Added a Mod config button in the game menu screen
	3. Added the option to disable the "Disable Warning Advice", which is not enabled by default, and the warning interface will not appear if it is enabled
	4. Fix the problem that mod config options sometimes fail

v0.1.2-0.1.2u-1.19.4-rc2 2023/03/12

	1. Updated the biome "Spider Swamp", spider egg cocoon will randomly spawn in this biome. In the world type, select "SR Temporary" to generate the world and play
	2. Now special spider variants are immune to fall damage

v0.1.1-0.1.1r-1.19.4-pre3 2023/03/07

	1. Fixed the texture error of Spider Chrysalis humanoid

v0.1.0-0.1.0p-1.19.3 2023/02/24

	1. Reconstruct the Java code and slightly improve the game performance
	2. Now piglin will ignore AuCu SRArmor
	3. Now all "Spontaneous-Replace" spiders are regarded as a group
	4. Now the Mushroom Fields, ocean biome and Deep Dark will no longer refresh the SR spider
	5. Reduced the probability of respawn the SR spider
	6. The trigger radius of Spider Egg Cocoon is changed from 5 block to 7 block
	7. Due to technical reasons, 1.19.3-version spider egg cocoons no longer spawn in the world
	8. Add the Fabric translation keys for Re-translation
	9. Optimized the text format and annotation system of the translation file
	10. Re-update and make new mod icon
	11. List a pack separately for all mod resource and data

v0.0.10-0.0.10a+1.19.2 2023/02/14

	1. Added new progress list, "Minecraft Expansion" and "Spontaneous-Replace"
	2. Added different equipment sound effects to each mod SRArmor
	3. Added a unique spitting sound effect for "Spray Poison Spider"
	4. Added exclusive subtitles for all special sound effects
	5. Added temporary "Spontaneous-Replace" adventure gameplay
	6. Now "SR World Options..." has a new option
	7. The three exclusive spider variants are now changed from single-target hatred to group hatred
	8. Fixed the problem that the number of webs weaved by the "Weaving-Web Spider" was reset when the game was restarted
	9. Fixed the problem that "Guard Spider" would generate vanilla variants and BUFF individuals
	10. Fixed the problem that the spider generated by "Spider Egg Cocoon" would get stuck in the block
	11. Fixed the problem that if there is an option configuration file but there is no option configuration, the default is "OFF" instead of the default value

v0.0.9-0.0.9a+1.19.2 2023/02/07

	1. Added block "Sticky-Compact Cobweb"
	2. Added test block "Spider Chrysalis"
	3. Added test block "Spider Egg Cocoon"

v0.0.8-0.0.8a+1.19.2 2023/01/23

	1. Added test mob "Weaving-Web Spider"

v0.0.7-0.0.7a+1.19.2 2023/01/15

	1. Added test mob "Spray Poison Spider"

v0.0.6-0.0.6a 2023/01/05

	1. Added test mob "Guard Spider"
	2. Add a coalescence group to all recipes to facilitate other mod authors to add recipes based on items in this mod

v0.0.5-0.0.5a 2022/12/26

	1. Updated the mod configuration interface
	2. Added "Auto Show Config Button" option
	3. Added "Enable Mod Content" option

v0.0.4-0.0.4a 2022/12/24

	1. Added steel products and related items
	2. Updated the icon of the item group
	3. Reduced the smelting experience of refined copper ingots and alloy ingots

v0.0.3-0.0.3a 2022/12/13

	1. Added AuCu alloy equipment and some AuCu alloy items
	2. Changed the smelting formula of refined copper ingots, now not only can the furnace be used to smelt refined copper ingots, but also the smelting efficiency is doubled
	3. Modified the protection attribute of the refined copper suit, now the SRArmor value of the refined copper suit has increased, but the toughness has decreased
	4. Optimized the typesetting of the Chinese introduction to adapt to different sizes of UI

v0.0.2a 2022/12/08

	1. Added CuFe equipment and some items
	2. Added smelted metal nuggets. And modified the refined copper tool items after smelting, now all smelting recipes have become refined copper nuggets
	3. Optimized the texture of refined copper SRArmor

v0.0.1a 2022/11/14 ~ 2022/12/08

	1. Canceled the text color of the version number
	2. Added a thank you to the Fabric staff
	3. Added a jump button to the official website for the placeholder interface
	4. Added refined copper equipment and some items

v0.0.0a 2022/11/14

	1. Created this mod and added an introduction
	2. Added various placeholder buttons
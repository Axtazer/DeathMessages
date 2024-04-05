package dev.mrshawn.deathmessages.config.modules;

import dev.mrshawn.deathmessages.DeathMessages;
import dev.mrshawn.deathmessages.config.Config;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityDeathMessages {

    public Map<String, Map<String, Object>> vanillaEntities = new ConcurrentHashMap<>();
    public Map<String, Map<String, Object>> mythicMobEntities = new ConcurrentHashMap<>();

    public EntityDeathMessages() {
        String[] header = {
                "DeathMessages by Dreeam__ (Original by MrShawn/Joshb_/CosmoConsole[DeathMessagesPrime Author])",
                "",
                "All configs from this plugin has an auto updater. Meaning if I add a value in the config from updates,",
                "the value will be automatically added without regenerating the configs.",
                "",
                "Entity Death Messages Config",
                "This config lets you configure death messages of mobs. (I.E: Enderdragon,",
                "",
                "Placeholders:",
                "%killer% - returns the player name",
                "%killer_display% - returns the custom display name of the player",
                "%owner% - returns the entities owner's name (Dog, cat, Parrot...) Only works in Tamed sections.",
                "%weapon% - returns a tooltip of the item name, lore, and enchantments",
                "%entity% - returns the entity's type. (Defined in Messages.yml in the Mobs section)",
                "%entity_display% - returns the entity's custom name. If it has no name, it will default to %entity%",
                "%biome% - returns the biome name the player located",
                "%world% - returns the world name",
                "%world_environment% - returns the environment of the world (normal, nether, the_end) (These are configurable in Messages.yml (Environment))",
                "%x% - returns the x position the player died at.",
                "%y% - returns the y position the player died at.",
                "%z% - returns the z position the player died at.",
                "",
                "Text components:",
                "",
                "Using \"::\" in the messages will create text components.",
                "Format as followed: Base text::Hover text::Action",
                "Actions must start with either COMMAND:, or SUGGEST_COMMAND:",
                "You can add lines in the hover messages like so \"line one\\nLine Two.....\"",
                "Examples:",
                "\"%player% was killed by [%killer%::&cLocations: X:%x%, Y:%y%, Z:%z%::COMMAND::tppos %x% %y% %z%]\"",
                "\"%player% was killed by [%killer%::&cLocations: X:%x%, Y:%y%, Z:%z%]\"",
                "\"%player% was killed by [%killer%::&cLocations: X:%x%, Y:%y%, Z:%z%::SUGGEST_COMMAND::msg %player% lol noob]\"",
                "",
                "!!!!!!!!!!!!!!!!!!!!LOOK HERE :EYES: :EYES:!!!!!!!!!!!!!!!!!!!!!!!!!",
                "If you do not want to use this feature of the plugin. Setup this config like so:",
                "",
                "Entities: []",
                "",
                "That's it.",
                "",
                "Vanilla Entities"
        };

        // Default example for Vanilla Entities death messages
        DeathMessages.getConfigManager().addComments(Config.getEntityDeathMessages(), "Entities", header);
        DeathMessages.getConfigManager().addComments(Config.getEntityDeathMessages(), "Entities.wolf", "Below is an example of how this section is setup. You can use any death-type like in PlayerDeathMessages.yml");

        // Default example for MythicMob entities death messages
        DeathMessages.getConfigManager().addComments(Config.getEntityDeathMessages(), "Mythic-Mobs-Entities","Mythic-Mob Entities https://mythiccraft.io/index.php?resources/mythicmobs.1/");
        DeathMessages.getConfigManager().getList(Config.getEntityDeathMessages(), "Mythic-Mobs-Entities.SkeletalMinion.Melee", Collections.singletonList("%killer% killed skele boss %entity_display% with their bare hands"));
        DeathMessages.getConfigManager().getList(Config.getEntityDeathMessages(), "Mythic-Mobs-Entities.SkeletalMinion.Weapon", Collections.singletonList("%killer% destroyed the boss mob %entity_display% with %weapon%"));
        DeathMessages.getConfigManager().getList(Config.getEntityDeathMessages(), "Mythic-Mobs-Entities.SkeletalMinion.TNT", Collections.singletonList("%killer% blew up the boss mob %entity_display% with TNT"));
    }
}

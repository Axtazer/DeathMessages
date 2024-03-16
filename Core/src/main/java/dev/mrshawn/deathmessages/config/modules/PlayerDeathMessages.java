package dev.mrshawn.deathmessages.config.modules;

import space.arim.dazzleconf.annote.ConfHeader;

@ConfHeader({
        "DeathMessages by Dreeam__ (Original by MrShawn/Joshb_/CosmoConsole[DeathMessagesPrime Author])",
        "",
        "All configs from this plugin has an auto updater. Meaning if I add a value in the config from updates,",
        "the value will be automatically added without regenerating the configs.",
        "",
        "Player Death Messages Config",
        "",
        "Placeholders:",
        "%player% - returns the player name",
        "%player_display% - returns the custom display name of the player",
        "%weapon% - returns a tooltip of the item name, lore, and enchantments",
        "%killer% - returns the entity's name. If there is no name set, it will be the entity type.",
        "%killer_display% - if the killer is a player, returns the display name of the player",
        "%killer_type% - returns the entity type from Mobs.<Mob> in Messages.yml",
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
        "Permissions Based Messages",
        "",
        "For victims that have a specific permission set, EX: deathmessages.premium, the death message",
        "will be sent that is formatted as followed:",
        "PERMISSION[YOUR.PERM.HERE]%player% dead lol",
        "",
        "For killers that have a specific permission set, EX: deathmessages.premium, the death message",
        "will be sent that is formatted as followed:",
        "PERMISSION_KILLER[YOUR.PERM.HERE]%killer% REKT %player%",
        "",
        "Players that are in a region (With WorldGuard hook enabled) and is defined below, will be sending a custom",
        "region messages.",
        "Formatted as followed: \"REGION[REGION_NAME]%player% died in a region\"",
        "Example:",
        "",
        "Mobs:",
        "  player:",
        "    Melee:",
        "      - \"%player% was slain by %killer%\"",
        "      - \"PERMISSION[deathmessages.premium]&6Premium %player% &fdied to %killer%",
        "      - \"REGION[spawn]Idiot %player% died in spawn lol\"",
        "      If the player has permission and in a region",
        "      - \"REGION[spawn]PERMISSION[deathmessages.premium]Idiot premium %player% died in spawn lol\"",
        "",
        "To completely remove the message for a certain death. Configure as followed:",
        "Natrual-Death:",
        "  Bed: []",
        "or",
        "Mobs:",
        "  Creeper:",
        "    Solo:",
        "      Explosion: []",
        "",
        "Due for there being no cure for laziness, this option ignores everything in the Mobs section and makes all messages",
        "appear the same."
})
public interface PlayerDeathMessages {
}
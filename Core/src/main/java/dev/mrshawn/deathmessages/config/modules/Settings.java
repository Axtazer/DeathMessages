package dev.mrshawn.deathmessages.config.modules;

import dev.mrshawn.deathmessages.DeathMessages;
import dev.mrshawn.deathmessages.config.Config;
import io.github.thatsmusic99.configurationmaster.api.ConfigSection;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Settings {

    public boolean CHECK_UPDATE;
    public boolean CHECK_UPDATE_DEV;
    public boolean DISABLE_DEFAULT_MESSAGES;
    public boolean ADD_PREFIX_TO_ALL_MESSAGES;
    public boolean HOOKS_MYTHICMOBS_ENABLED;
    public boolean HOOKS_WORLDGUARD_ENABLED;
    public boolean HOOKS_COMBATLOGX_ENABLED;
    public boolean HOOKS_DISCORD_ENABLED;
    public boolean HOOKS_DISCORD_CHANNELS_PLAYER_ENABLED;
    public List<String> HOOKS_DISCORD_CHANNELS_PLAYER_CHANNELS;
    public boolean HOOKS_DISCORD_CHANNELS_MOB_ENABLED;
    public List<String> HOOKS_DISCORD_CHANNELS_MOB_CHANNELS;
    public boolean HOOKS_DISCORD_CHANNELS_NATURAL_ENABLED;
    public List<String> HOOKS_DISCORD_CHANNELS_NATURAL_CHANNELS;
    public boolean HOOKS_DISCORD_CHANNELS_ENTITY_ENABLED;
    public List<String> HOOKS_DISCORD_CHANNELS_ENTITY_CHANNELS;
    public boolean HOOKS_DISCORD_WORLD_WHITELIST_ENABLED;
    public List<String> HOOKS_DISCORD_WORLD_WHITELIST_WORLDS;
    public boolean HOOKS_BUNGEE_ENABLED;
    public boolean HOOKS_BUNGEE_SERVER_NAME_GET_FROM_BUNGEE;
    public String HOOKS_BUNGEE_SERVER_NAME_DISPLAY_NAME;
    public boolean HOOKS_BUNGEE_SERVER_GROUPS_ENABLED;
    public List<String> HOOKS_BUNGEE_SERVER_GROUPS_SERVERS;
    public boolean SAVED_USER_DATA;
    public boolean DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_ENABLED;
    public boolean DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_ALLOW_MESSAGE_COLOR_OVERRIDE;
    public boolean DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_IGNORE_ENCHANTMENTS;
    public String DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_SOURCE_PROJECTILE_DEFAULT_TO;
    public String DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_SOURCE_WEAPON_DEFAULT_TO;
    public boolean DEFAULT_MELEE_LAST_DAMAGE_NOT_DEFINED;
    public boolean DEFAULT_NATURAL_DEATH_NOT_DEFINED;
    public String DEATH_LISTENER_PRIORITY;
    public boolean RENAME_MOBS_ENABLED;
    public String RENAME_MOBS_IF_CONTAINS;
    public boolean DISABLE_NAMED_MOBS;
    public boolean DISPLAY_I18N_ITEM_NAME;
    public boolean DISPLAY_I18N_MOB_NAME;
    public int EXPIRE_LAST_DAMAGE_EXPIRE_PLAYER;
    public int EXPIRE_LAST_DAMAGE_EXPIRE_ENTITY;
    public boolean PER_WORLD_MESSAGES;
    public ConfigSection WORLD_GROUPS;
    public List<String> DISABLED_WORLDS;
    public boolean PRIVATE_MESSAGES_PLAYER;
    public boolean PRIVATE_MESSAGES_MOBS;
    public boolean PRIVATE_MESSAGES_NATURAL;
    public boolean PRIVATE_MESSAGES_TAMEABLE;
    public int COOLDOWN;
    public List<String> CUSTOM_ITEM_DISPLAY_NAMES_IS_WEAPON;
    public List<String> CUSTOM_ITEM_MATERIAL_IS_WEAPON;
    public boolean DEBUG;

    public Settings() {
        String[] header = {
                "DeathMessages by Dreeam__ (Original by MrShawn/Joshb_/CosmoConsole[DeathMessagesPrime Author])",
                ""
        };
        DeathMessages.getConfigManager().addComments(Config.getSettingsFile(), "Check-Update", header);

        CHECK_UPDATE = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Check-Update.Enabled", true);

        CHECK_UPDATE_DEV = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Check-Update.DEV-Version", false);

        DISABLE_DEFAULT_MESSAGES = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Disable-Default-Messages", true);

        ADD_PREFIX_TO_ALL_MESSAGES = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Add-Prefix-To-All-Messages", true);

        HOOKS_MYTHICMOBS_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.MythicMobs.Enabled", true);

        HOOKS_WORLDGUARD_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.WorldGuard.Enabled", true);

        HOOKS_COMBATLOGX_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.CombatLogX.Enabled", true);

        HOOKS_DISCORD_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.Discord.Enabled", true);

        HOOKS_DISCORD_CHANNELS_PLAYER_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.Discord.Channels.Player.Enabled", true);

        HOOKS_DISCORD_CHANNELS_PLAYER_CHANNELS = DeathMessages.getConfigManager().getList(Config.getSettingsFile(), "Hooks.Discord.Channels.Player.Channels", Collections.singletonList("218258614192450048:827286062147837621"));

        HOOKS_DISCORD_CHANNELS_MOB_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.Discord.Channels.Mob.Enabled", true);

        HOOKS_DISCORD_CHANNELS_MOB_CHANNELS = DeathMessages.getConfigManager().getList(Config.getSettingsFile(), "Hooks.Discord.Channels.Mob.Channels", Collections.singletonList(""));

        HOOKS_DISCORD_CHANNELS_NATURAL_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.Discord.Channels.Natural.Enabled", true);

        HOOKS_DISCORD_CHANNELS_NATURAL_CHANNELS = DeathMessages.getConfigManager().getList(Config.getSettingsFile(), "Hooks.Discord.Channels.Natural.Channels", Collections.singletonList(""));

        HOOKS_DISCORD_CHANNELS_ENTITY_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.Discord.Channels.Entity.Enabled", true);

        HOOKS_DISCORD_CHANNELS_ENTITY_CHANNELS = DeathMessages.getConfigManager().getList(Config.getSettingsFile(), "Hooks.Discord.Channels.Entity.Channels", Collections.singletonList(""));

        HOOKS_DISCORD_WORLD_WHITELIST_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.Discord.World-Whitelist.Enabled", false);

        HOOKS_DISCORD_WORLD_WHITELIST_WORLDS = DeathMessages.getConfigManager().getList(Config.getSettingsFile(), "Hooks.Discord.World-Whitelist.Worlds", Arrays.asList("test1", "test2"));

        HOOKS_BUNGEE_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.Bungee.Enabled", false);

        HOOKS_BUNGEE_SERVER_NAME_GET_FROM_BUNGEE = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.Bungee.Server-Name.Get-From-Bungee", false);

        HOOKS_BUNGEE_SERVER_NAME_DISPLAY_NAME = DeathMessages.getConfigManager().getString(Config.getSettingsFile(), "Hooks.Bungee.Server-Name.Display-Name", "lobby");

        HOOKS_BUNGEE_SERVER_GROUPS_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Hooks.Bungee.Server-Groups.Enabled", false);

        HOOKS_BUNGEE_SERVER_GROUPS_SERVERS = DeathMessages.getConfigManager().getList(Config.getSettingsFile(), "Hooks.Bungee.Server-Groups.Servers", Arrays.asList("lobby", "survival"));

        SAVED_USER_DATA = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Saved-User-Data", true);

        DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Disable-Weapon-Kill-With-No-Custom-Name.Enabled", false);

        DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_ALLOW_MESSAGE_COLOR_OVERRIDE = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Disable-Weapon-Kill-With-No-Custom-Name.Allow-Message-Color-Override", true);

        DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_IGNORE_ENCHANTMENTS = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Disable-Weapon-Kill-With-No-Custom-Name.Ignore-Enchantments", true);

        DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_SOURCE_PROJECTILE_DEFAULT_TO = DeathMessages.getConfigManager().getString(Config.getSettingsFile(), "Disable-Weapon-Kill-With-No-Custom-Name.Source.Projectile.Default-To", "Projectile-Unknown");

        DISABLE_WEAPON_KILL_WITH_NO_CUSTOM_NAME_SOURCE_WEAPON_DEFAULT_TO = DeathMessages.getConfigManager().getString(Config.getSettingsFile(), "Disable-Weapon-Kill-With-No-Custom-Name.Source.Weapon.Default-To", "Melee");

        DEFAULT_MELEE_LAST_DAMAGE_NOT_DEFINED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Default-Melee-Last-Damage-Not-Defined", true);

        DEFAULT_NATURAL_DEATH_NOT_DEFINED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Default-Natural-Death-Not-Defined", true);

        DEATH_LISTENER_PRIORITY = DeathMessages.getConfigManager().getString(Config.getSettingsFile(), "Death-Listener-Priority", "HIGH");

        RENAME_MOBS_ENABLED = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Rename-Mobs.Enabled", true);

        RENAME_MOBS_IF_CONTAINS = DeathMessages.getConfigManager().getString(Config.getSettingsFile(), "Rename-Mobs.If-Contains", "♡♥❤■");

        DISABLE_NAMED_MOBS = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Disable-Named-Mobs", false);

        DISPLAY_I18N_ITEM_NAME = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "I18N-Display.Item-Name", true);

        DISPLAY_I18N_MOB_NAME = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "I18N-Display.Mob-Name", true);

        EXPIRE_LAST_DAMAGE_EXPIRE_PLAYER = DeathMessages.getConfigManager().getInt(Config.getSettingsFile(), "Expire-Last-Damage.Expire-Player", 7);

        EXPIRE_LAST_DAMAGE_EXPIRE_ENTITY = DeathMessages.getConfigManager().getInt(Config.getSettingsFile(), "Expire-Last-Damage.Expire-Entity", 7);

        PER_WORLD_MESSAGES = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Per-World-Messages", false);

        Map<String, Object> groups = new LinkedHashMap<>();
        groups.put("1", Arrays.asList("world", "world_nether", "world_the_end"));
        WORLD_GROUPS = DeathMessages.getConfigManager().getConfigSection(Config.getSettingsFile(), "World-Groups", groups);

        DISABLED_WORLDS = DeathMessages.getConfigManager().getList(Config.getSettingsFile(), "Disabled-Worlds", Arrays.asList("someDisabledWorld", "someOtherDisabledWorld"));

        PRIVATE_MESSAGES_PLAYER = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Private-Messages.Player", false);

        PRIVATE_MESSAGES_MOBS = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Private-Messages.Mobs", false);

        PRIVATE_MESSAGES_NATURAL = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Private-Messages.Natural", false);

        PRIVATE_MESSAGES_TAMEABLE = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Private-Messages.Tameable", false);

        COOLDOWN = DeathMessages.getConfigManager().getInt(Config.getSettingsFile(), "Cooldown", 0);

        CUSTOM_ITEM_DISPLAY_NAMES_IS_WEAPON = DeathMessages.getConfigManager().getList(Config.getSettingsFile(), "Custom-Item-Display-Names-Is-Weapon", Arrays.asList("&6SUPER COOL GOLDEN APPLE", "SICKNAME"));

        CUSTOM_ITEM_MATERIAL_IS_WEAPON = DeathMessages.getConfigManager().getList(Config.getSettingsFile(), "Custom-Item-Material-Is-Weapon", Collections.singletonList("ACACIA_FENCE"));

        DEBUG = DeathMessages.getConfigManager().getBoolean(Config.getSettingsFile(), "Debug", false);
    }
}

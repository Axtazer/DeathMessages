package dev.mrshawn.deathmessages;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.tcoded.folialib.FoliaLib;
import dev.mrshawn.deathmessages.api.PlayerManager;
import dev.mrshawn.deathmessages.command.deathmessages.CommandManager;
import dev.mrshawn.deathmessages.command.deathmessages.TabCompleter;
import dev.mrshawn.deathmessages.command.deathmessages.alias.CommandDeathMessagesToggle;
import dev.mrshawn.deathmessages.config.ConfigManager;
import dev.mrshawn.deathmessages.files.Config;
import dev.mrshawn.deathmessages.files.FileSettings;
import dev.mrshawn.deathmessages.hooks.DiscordBotAPIExtension;
import dev.mrshawn.deathmessages.hooks.DiscordSRVExtension;
import dev.mrshawn.deathmessages.hooks.PlaceholderAPIExtension;
import dev.mrshawn.deathmessages.kotlin.files.FileStore;
import dev.mrshawn.deathmessages.kotlin.utils.EventUtils;
import dev.mrshawn.deathmessages.listeners.EntityDamage;
import dev.mrshawn.deathmessages.listeners.EntityDamageByBlock;
import dev.mrshawn.deathmessages.listeners.EntityDamageByEntity;
import dev.mrshawn.deathmessages.listeners.EntityDeath;
import dev.mrshawn.deathmessages.listeners.InteractEvent;
import dev.mrshawn.deathmessages.listeners.OnChat;
import dev.mrshawn.deathmessages.listeners.OnJoin;
import dev.mrshawn.deathmessages.listeners.OnMove;
import dev.mrshawn.deathmessages.listeners.OnQuit;
import dev.mrshawn.deathmessages.listeners.PlayerDeath;
import dev.mrshawn.deathmessages.listeners.PluginMessaging;
import dev.mrshawn.deathmessages.listeners.combatlogx.PlayerUntag;
import dev.mrshawn.deathmessages.listeners.customlisteners.BlockExplosion;
import dev.mrshawn.deathmessages.listeners.customlisteners.BroadcastEntityDeathListener;
import dev.mrshawn.deathmessages.listeners.customlisteners.BroadcastPlayerDeathListener;
import dev.mrshawn.deathmessages.listeners.mythicmobs.MobDeath;
import dev.mrshawn.deathmessages.worldguard.WorldGuard7Extension;
import dev.mrshawn.deathmessages.worldguard.WorldGuardExtension;
import io.lumine.mythic.bukkit.MythicBukkit;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DeathMessages extends JavaPlugin {

	private static DeathMessages instance;
	public FoliaLib foliaLib = new FoliaLib(this);

	public boolean placeholderAPIEnabled = false;
	public boolean combatLogXAPIEnabled = false;

	public MythicBukkit mythicMobs = null;
	public boolean mythicmobsEnabled = false;


	public static String bungeeServerName;
	public static boolean bungeeServerNameRequest = true;
	public static boolean bungeeInit = false;


	public static WorldGuardExtension worldGuardExtension;
	public static boolean worldGuardEnabled;

	public static DiscordBotAPIExtension discordBotAPIExtension;
	public static DiscordSRVExtension discordSRVExtension;

	private static EventPriority eventPriority = EventPriority.HIGH;

	private static FileSettings<Config> config;

	public void onEnable() {
		this.adventure = BukkitAudiences.create(this);
		initializeListeners();
		initializeCommands();
		initializeHooks();
		initializeOnlinePlayers();
		checkGameRules();
		new Metrics(this, 12365);
		getLogger().info("bStats Hook Enabled!");
	}

	public void onLoad() {
		instance = this;
		initializeConfigs();
		initializeHooksOnLoad();
	}

	public void onDisable() {
		if(this.adventure != null) {
			this.adventure.close();
			this.adventure = null;
		}
		instance = null;
	}

	private BukkitAudiences adventure;

	public @NotNull BukkitAudiences adventure() {
		if(this.adventure == null) {
			throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
		}
		return this.adventure;
	}

	public static int majorVersion() {
		// Dreeam - eg. 1.20.2-R0.1-SNAPSHOT, replace string before first decimal point, then replace all string after the decimal point
		return Integer.parseInt(Bukkit.getServer().getBukkitVersion()
				.replaceFirst("^(\\d+)\\.", "")
				.replaceAll("\\.(.+)", "")
		);
	}

	private void initializeConfigs() {
		ConfigManager.getInstance().initialize();
		config = FileStore.INSTANCE.getCONFIG();

		DeathMessages.eventPriority = EventPriority.valueOf(
				config.getString(Config.DEATH_LISTENER_PRIORITY).toUpperCase()
		);
	}

	private void initializeListeners() {
		EventUtils.INSTANCE.registerEvents(
				// DeathMessages
				new BroadcastPlayerDeathListener(),
				new BroadcastEntityDeathListener(),
				// Bukkit
				new BlockExplosion(),
				new EntityDamage(),
				new EntityDamageByBlock(),
				new EntityDamageByEntity(),
				new EntityDeath(),
				new InteractEvent(),
				new OnChat(),
				new OnJoin(),
				new OnMove(),
				new OnQuit(),
				new PlayerDeath()
		);
	}

	private void initializeCommands() {
		CommandManager cm = new CommandManager();
		cm.initializeSubCommands();
		getCommand("deathmessages").setExecutor(cm);
		getCommand("deathmessages").setTabCompleter(new TabCompleter());
		getCommand("deathmessagestoggle").setExecutor(new CommandDeathMessagesToggle());
	}

	private void initializeHooks() {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			new PlaceholderAPIExtension(this).register();
			placeholderAPIEnabled = true;
			getLogger().info("PlaceholderAPI Hook Enabled!");
		}

		if (worldGuardEnabled) {
			getLogger().info("WorldGuard Hook Enabled!");
		}

		if (Bukkit.getPluginManager().getPlugin("DiscordBotAPI") != null && config.getBoolean(Config.HOOKS_DISCORD_ENABLED)) {
			discordBotAPIExtension = new DiscordBotAPIExtension();
			getLogger().info("DiscordBotAPI Hook Enabled!");
		}

		if (Bukkit.getPluginManager().getPlugin("DiscordSRV") != null && config.getBoolean(Config.HOOKS_DISCORD_ENABLED)) {
			discordSRVExtension = new DiscordSRVExtension();
			getLogger().info("DiscordSRV Hook Enabled!");
		}

		if (Bukkit.getPluginManager().isPluginEnabled("PlugMan") && worldGuardExtension != null) {
			Plugin plugMan = Bukkit.getPluginManager().getPlugin("PlugMan");
			getLogger().info("PlugMan found. Adding this plugin to its ignored plugins list due to WorldGuard hook being enabled!");
			try {
				List<String> ignoredPlugins = (List<String>) plugMan.getClass().getMethod("getIgnoredPlugins").invoke(plugMan);
				if (!ignoredPlugins.contains("DeathMessages")) {
					ignoredPlugins.add("DeathMessages");
				}
			} catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException exception) {
				getLogger().severe("Error adding plugin to ignored plugins list: " +
						exception.getMessage());
			}
		}

		if (Bukkit.getPluginManager().getPlugin("CombatLogX") != null && config.getBoolean(Config.HOOKS_COMBATLOGX_ENABLED)) {
			combatLogXAPIEnabled = true;
			Bukkit.getPluginManager().registerEvents(new PlayerUntag(), this);
			getLogger().info("CombatLogX Hook Enabled!");
		}

		if (Bukkit.getPluginManager().getPlugin("MythicMobs") != null && config.getBoolean(Config.HOOKS_MYTHICMOBS_ENABLED)) {
			mythicMobs = MythicBukkit.inst();
			mythicmobsEnabled = true;
			getLogger().info("MythicMobs Hook Enabled!");
			Bukkit.getPluginManager().registerEvents(new MobDeath(), this);
		}

		if (config.getBoolean(Config.HOOKS_BUNGEE_ENABLED)) {
			Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
			Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMessaging());
			getLogger().info("Bungee Hook enabled!");
			if (config.getBoolean(Config.HOOKS_BUNGEE_SERVER_NAME_GET_FROM_BUNGEE)) {
				bungeeInit = true;
			} else {
				bungeeInit = false;
				bungeeServerName = config.getString(Config.HOOKS_BUNGEE_SERVER_NAME_DISPLAY_NAME);
			}
		}
	}

	private void initializeHooksOnLoad() {
		if (Bukkit.getPluginManager().getPlugin("WorldGuard") != null && config.getBoolean(Config.HOOKS_WORLDGUARD_ENABLED)) {
			try {
				final WorldGuardPlugin worldGuardPlugin = WorldGuardPlugin.inst();
				if (worldGuardPlugin == null) throw new Exception();
				final String version = worldGuardPlugin.getDescription().getVersion();
				if (version.startsWith("7")) {
					worldGuardExtension = new WorldGuard7Extension();
					worldGuardExtension.registerFlags();
				} else if (version.startsWith("6")) {
					//worldGuardExtension = new WorldGuard6Extension();
					worldGuardExtension.registerFlags();
				} else throw new Exception();
				worldGuardEnabled = true;
			} catch (final Throwable e) {
				getLogger().severe("Error loading WorldGuardHook. Error: " + e.getMessage());
				worldGuardEnabled = false;
			}
		}
	}

	private void initializeOnlinePlayers() {
		Bukkit.getOnlinePlayers().forEach(PlayerManager::new);
	}

	private void checkGameRules() {
		if (config.getBoolean(Config.DISABLE_DEFAULT_MESSAGES)) {
			for (World world : Bukkit.getWorlds()) {
				try {
					if (Boolean.TRUE.equals(world.getGameRuleValue(GameRule.SHOW_DEATH_MESSAGES))) {
						foliaLib.getImpl().runNextTick(task -> world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false));
					}
				} catch (NoClassDefFoundError e) {
					if (world.getGameRuleValue("showDeathMessages").equals("true")) world.setGameRuleValue("showDeathMessages", "false");
				}
			}
		}
	}

	public static EventPriority getEventPriority() {
		return eventPriority;
	}

	public static DeathMessages getInstance() {
		return instance;
	}

}

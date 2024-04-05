package dev.mrshawn.deathmessages.config;

import dev.mrshawn.deathmessages.DeathMessages;
import dev.mrshawn.deathmessages.config.modules.EntityDeathMessages;
import dev.mrshawn.deathmessages.config.modules.Gangs;
import dev.mrshawn.deathmessages.config.modules.Messages;
import dev.mrshawn.deathmessages.config.modules.PlayerDeathMessages;
import dev.mrshawn.deathmessages.config.modules.Settings;
import io.github.thatsmusic99.configurationmaster.api.ConfigFile;
import io.github.thatsmusic99.configurationmaster.impl.CMConfigSection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Config extends CMConfigSection {

    private static ConfigFile entityDeathMessagesFile, gangsFile, messagesFile, playerDeathMessagesFile, settingsFile;

    public static EntityDeathMessages entityDeathMessages;
    public static Gangs gangs;
    public static Messages messages;
    public static PlayerDeathMessages playerDeathMessages;
    public static Settings settings;

    public static void load() {
        File configFolder = DeathMessages.getInstance().getDataFolder();

        if (!configFolder.exists() && !configFolder.mkdir()) {
            DeathMessages.LOGGER.error("Failed to create plugin folder.");
        }

        System.out.println("1: " + readFileContent());
        try {
            entityDeathMessagesFile = ConfigFile.loadConfig(new File(configFolder, "EntityDeathMessages.yml"));
            gangsFile = ConfigFile.loadConfig(new File(configFolder, "Gangs.yml"));
            messagesFile = ConfigFile.loadConfig(new File(configFolder, "Messages.yml"));
            playerDeathMessagesFile = ConfigFile.loadConfig(new File(configFolder, "PlayerDeathMessages.yml"));
            settingsFile = ConfigFile.loadConfig(new File(configFolder, "Settings.yml"));
        } catch (Exception e) {
            DeathMessages.LOGGER.error("Config load failed.", e);
        }

        System.out.println("2: " + readFileContent());

        entityDeathMessages = new EntityDeathMessages();
        gangs = new Gangs();
        messages = new Messages();
        playerDeathMessages = new PlayerDeathMessages();
        settings = new Settings();

        System.out.println("3: " + readFileContent());

        ConfigManager.saveConfig(entityDeathMessagesFile);
        ConfigManager.saveConfig(gangsFile);
        ConfigManager.saveConfig(messagesFile);
        ConfigManager.saveConfig(playerDeathMessagesFile);
        ConfigManager.saveConfig(settingsFile);
        System.out.println("4: " + readFileContent());

        entityDeathMessagesFile.getKeys()
    }

    public static ConfigFile getEntityDeathMessages() {
        return entityDeathMessagesFile;
    }

    public static ConfigFile getGangsFile() {
        return gangsFile;
    }

    public static ConfigFile getMessagesFile() {
        return messagesFile;
    }

    public static ConfigFile getPlayerDeathMessagesFile() {
        return playerDeathMessagesFile;
    }

    public static ConfigFile getSettingsFile() {
        return settingsFile;
    }

    // debug test
    protected static String readFileContent() {
        StringBuilder content = null;
        BufferedReader reader = null;

        try {
            content = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(Files.newInputStream(new File(DeathMessages.getInstance().getDataFolder(), "EntityDeathMessages.yml").toPath()), StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            if (content.length() == 0) {
            }
        } catch (Throwable var6) {
            try {
                reader.close();
            } catch (Throwable var5) {
                var6.addSuppressed(var5);
            }

        }

        try {
            reader.close();
        } catch (IOException ignored) {
            return "null";
        }
        return content.toString();
    }
}

package dev.mrshawn.deathmessages.config;

import dev.mrshawn.deathmessages.DeathMessages;
import dev.mrshawn.deathmessages.config.modules.UserData;
import dev.mrshawn.deathmessages.utils.Util;
import io.github.thatsmusic99.configurationmaster.api.ConfigFile;
import io.github.thatsmusic99.configurationmaster.api.ConfigSection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Refer to config system of moom0o/AnarchyExploitFixes by xGinko
 * @see <a href="https://github.com/moom0o/AnarchyExploitFixes">...</a>
 */
public class ConfigManager {

    public final File backupDirectory = new File(DeathMessages.getInstance().getDataFolder(), "Backups");

    public static void saveConfig(ConfigFile config) {
        try {
            config.load();
        } catch (Exception e) {
            DeathMessages.LOGGER.error("Failed to save config file!", e);
        }
    }

    /*
    private void structureConfig() {
        config.addDefault("config-version", 1.00);
        createTitledSection("Language", "language");
    }
     */

    public void createTitledSection(ConfigFile config, String title, String path) {
        config.addSection(title);
        config.addDefault(path, null);
    }

    public boolean getBoolean(ConfigFile config, String path) {
        boolean value = config.getBoolean(path);
        System.out.println("getBoolean value: " + value);
        config.addDefault(path, value);
        return value;
    }

    public boolean getBoolean(ConfigFile config, String path, boolean def) {
        config.addDefault(path, def);
        return config.getBoolean(path, def);
    }

    public boolean getBoolean(ConfigFile config, String path, boolean def, String comment) {
        config.addDefault(path, def, comment);
        return config.getBoolean(path, def);
    }

    public String getString(ConfigFile config, String path) {
        String value = config.getString(path);
        System.out.println("getString value: " + value);
        config.addDefault(path, value);
        return value;
    }

    public String getString(ConfigFile config, String path, String def) {
        config.addDefault(path, def);
        return config.getString(path, def);
    }

    public String getString(ConfigFile config, String path, String def, String comment) {
        config.addDefault(path, def, comment);
        return config.getString(path, def);
    }

    public double getDouble(ConfigFile config, String path) {
        double value = config.getDouble(path);
        System.out.println("getDouble value: " + value);
        config.addDefault(path, value);
        return value;
    }

    public double getDouble(ConfigFile config, String path, double def) {
        config.addDefault(path, def);
        return config.getDouble(path, def);
    }

    public double getDouble(ConfigFile config, String path, double def, String comment) {
        config.addDefault(path, def, comment);
        return config.getDouble(path, def);
    }

    public int getInt(ConfigFile config, String path) {
        int value = config.getInteger(path);
        System.out.println("getInt value: " + value);
        config.addDefault(path, value);
        return value;
    }

    public int getInt(ConfigFile config, String path, int def) {
        config.addDefault(path, def);
        return config.getInteger(path, def);
    }

    public int getInt(ConfigFile config, String path, int def, String comment) {
        config.addDefault(path, def, comment);
        return config.getInteger(path, def);
    }

    public List<String> getList(ConfigFile config, String path) {
        List<String> value = config.getStringList(path);
        System.out.println("getList value: " + value);
        config.addDefault(path, value);
        return value;
    }

    public List<String> getList(ConfigFile config, String path, List<String> def) {
        config.addDefault(path, def);
        return config.getStringList(path);
    }

    public List<String> getList(ConfigFile config, String path, List<String> def, String comment) {
        config.addDefault(path, def, comment);
        return config.getStringList(path);
    }

    public ConfigSection getConfigSection(ConfigFile config, String path, Map<String, Object> defaultKeyValue) {
        config.addDefault(path, null);
        config.makeSectionLenient(path);
        defaultKeyValue.forEach((string, object) -> config.addExample(path + "." + string, object));
        return config.getConfigSection(path);
    }

    public ConfigSection getConfigSection(ConfigFile config, String path, Map<String, Object> defaultKeyValue, String comment) {
        config.addDefault(path, null, comment);
        config.makeSectionLenient(path);
        defaultKeyValue.forEach((string, object) -> config.addExample(path + "." + string, object));
        return config.getConfigSection(path);
    }

    public void addComments(ConfigFile config, String path, String... comments) {
        config.addComments(path, comments);
    }

    public String backup(boolean excludeUserData) {
        if (!backupDirectory.exists()) {
            backupDirectory.mkdir();
        }

        String randomCode = Util.randomNumeric(4);
        File backupDir = new File(backupDirectory, randomCode);
        backupDir.mkdir();

        this.copy(Config.getEntityDeathMessages().getFile().toPath(), backupDir.toPath());
        this.copy(Config.getGangsFile().getFile().toPath(), backupDir.toPath());
        this.copy(Config.getMessagesFile().getFile().toPath(), backupDir.toPath());
        this.copy(Config.getPlayerDeathMessagesFile().getFile().toPath(), backupDir.toPath());
        this.copy(Config.getSettingsFile().getFile().toPath(), backupDir.toPath());

        if (!excludeUserData) {
            this.copy(UserData.getInstance().file.toPath(), backupDir.toPath());
        }

        return randomCode;
    }

    /*
        Returns true if the operation was successful.
        Returns false if the operation was not successful.
     */
    /*
    public boolean restore(String code, boolean excludeUserData) {
        File backupDir = new File(backupDirectory, code);
        if (!backupDir.exists()) {
            return false;
        }
        try {
            String fileName = EntityDeathMessages.getInstance().fileName;
            File f = new File(backupDir, fileName + ".yml");
            if (EntityDeathMessages.getInstance().file.delete()) {
                FileUtils.copyFileToDirectory(f, DeathMessages.getInstance().getDataFolder());
            } else {
                LogManager.getLogger().error("COULD NOT RESTORE " + fileName + ".");
            }
        } catch (IOException e) {
            LogManager.getLogger().error(e);
        }
        try {
            String fileName = Gangs.getInstance().fileName;
            File f = new File(backupDir, fileName + ".yml");
            if (Gangs.getInstance().file.delete()) {
                FileUtils.copyFileToDirectory(f, DeathMessages.getInstance().getDataFolder());
            } else {
                LogManager.getLogger().error("COULD NOT RESTORE " + fileName + ".");
            }
        } catch (IOException e) {
            LogManager.getLogger().error(e);
        }
        try {
            String fileName = Messages.getInstance().fileName;
            File f = new File(backupDir, fileName + ".yml");
            if (Messages.getInstance().file.delete()) {
                FileUtils.copyFileToDirectory(f, DeathMessages.getInstance().getDataFolder());
            } else {
                LogManager.getLogger().error("COULD NOT RESTORE " + fileName + ".");
            }
        } catch (IOException e) {
            LogManager.getLogger().error(e);
        }
        try {
            String fileName = PlayerDeathMessages.getInstance().fileName;
            File f = new File(backupDir, fileName + ".yml");
            if (PlayerDeathMessages.getInstance().file.delete()) {
                FileUtils.copyFileToDirectory(f, DeathMessages.getInstance().getDataFolder());
            } else {
                LogManager.getLogger().error("COULD NOT RESTORE " + fileName + ".");
            }
        } catch (IOException e) {
            LogManager.getLogger().error(e);
        }
        try {
            String fileName = Settings.getInstance().fileName;
            File f = new File(backupDir, fileName + ".yml");
            if (Settings.getInstance().getFile().delete()) {
                FileUtils.copyFileToDirectory(f, DeathMessages.getInstance().getDataFolder());
            } else {
                LogManager.getLogger().error("COULD NOT RESTORE " + fileName + ".");
            }
        } catch (IOException e) {
            LogManager.getLogger().error(e);
        }
        if (!excludeUserData) {
            try {
                String fileName = UserData.getInstance().fileName;
                File f = new File(backupDir, fileName + ".yml");
                if (UserData.getInstance().file.delete()) {
                    FileUtils.copyFileToDirectory(f, DeathMessages.getInstance().getDataFolder());
                } else {
                    LogManager.getLogger().error("COULD NOT RESTORE " + fileName + ".");
                }
            } catch (IOException e) {
                LogManager.getLogger().error(e);
            }
        }
        dev.mrshawn.deathmessages.config.legacy.ConfigManager.getInstance().reload();
        return true;
    }
     */

    public void copy(InputStream in, File file) {
        try {
            Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            DeathMessages.LOGGER.error(e);
        }
    }

    public void copy(Path from, Path to) {
        try {
            Files.copy(from, to);
        } catch (IOException e) {
            DeathMessages.LOGGER.error(e);
        }
    }
}
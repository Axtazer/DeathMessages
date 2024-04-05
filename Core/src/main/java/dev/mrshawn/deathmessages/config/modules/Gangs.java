package dev.mrshawn.deathmessages.config.modules;

import dev.mrshawn.deathmessages.DeathMessages;
import dev.mrshawn.deathmessages.config.Config;
import dev.mrshawn.deathmessages.utils.EntityUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class Gangs {

    public boolean enableGangs;
    public Map<String, Map<String, Object>> gangsGroup = new LinkedHashMap<>(); // Dreeam TODO: maybe private and use ConfigManager getInt method
    private final Map<String, Object> defaultDensity = new LinkedHashMap<>();

    public Gangs() {
        String[] header = {
                "DeathMessages by Dreeam__ (Original by MrShawn/Joshb_/CosmoConsole[DeathMessagesPrime Author])",
                ""
        };
        DeathMessages.getConfigManager().addComments(Config.getGangsFile(), "Gang", header);
        enableGangs = DeathMessages.getConfigManager().getBoolean(Config.getGangsFile(), "Gang.Enabled", true);

        defaultDensity.put("Radius", 5);
        defaultDensity.put("Amount", 4);
        EntityUtil.getEntitiyList().forEach(e -> {
            String path = "Gang.Mobs." + e;
            DeathMessages.getConfigManager().getConfigSection(Config.getGangsFile(), path, defaultDensity);
            gangsGroup.put(e, defaultDensity);
        });
    }
}

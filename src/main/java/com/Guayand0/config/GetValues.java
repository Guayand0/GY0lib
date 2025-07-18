package com.Guayand0.config;

import org.bukkit.plugin.Plugin;

public class GetValues {

    public boolean getBoolean(Plugin plugin, String key, boolean defaultValue) {
        return plugin.getConfig().getBoolean(key, defaultValue);
    }

    public String getString(Plugin plugin, String key, String defaultValue) {
        return plugin.getConfig().getString(key, defaultValue);
    }

    public int getInt(Plugin plugin, String key, int defaultValue) {
        return plugin.getConfig().getInt(key, defaultValue);
    }

    public double getDouble(Plugin plugin, String key, double defaultValue) {
        return plugin.getConfig().getDouble(key, defaultValue);
    }

}

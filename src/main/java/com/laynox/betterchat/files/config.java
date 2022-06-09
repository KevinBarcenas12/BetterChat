package com.laynox.betterchat.files;

import com.laynox.betterchat.manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class config {
    private static File file;
    private static FileConfiguration config;
    public static void set(Plugin plugin) {
        file = new File(plugin.getDataFolder(), File.separator + "config.yml");
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException exception) {
            manager.console.log("&4" + exception.getMessage());
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration get() {
        save();
        return config;
    }
    public static void save() {
        try {
            config.save(file);
        } catch (IOException exception) {
            manager.console.log("&4" + exception.getMessage());
        }
    }
}

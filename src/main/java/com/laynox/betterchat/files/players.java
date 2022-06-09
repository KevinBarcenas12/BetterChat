package com.laynox.betterchat.files;

import com.laynox.betterchat.manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class players {
    private static File file;
    private static FileConfiguration players;
    public static void set(Plugin plugin) {
        file = new File(plugin.getDataFolder(), File.separator + "players.yml");
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException exception) {
            manager.console.log("&4"+exception.getMessage());
        }
        players = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration get() {
        save();
        return players;
    }
    public static void save() {
        try {
            players.save(file);
        } catch (IOException exception) {
            manager.console.log("&4"+exception.getMessage());
        }
    }
}

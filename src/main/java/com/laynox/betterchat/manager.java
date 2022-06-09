package com.laynox.betterchat;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class manager {
    public static class console {
        public static void log(String message) {
            Bukkit.getConsoleSender().sendMessage(chat.color(message));
        }
    }
    public static class files {
        public static FileConfiguration config = com.laynox.betterchat.files.config.get();
        public static FileConfiguration players = com.laynox.betterchat.files.players.get();
        public static class configFunctions {
            public static void save() {
                com.laynox.betterchat.files.config.save();
            }
            public static void set(Plugin plugin) {com.laynox.betterchat.files.config.set(plugin);}
        }
        public static class playerFunctions {
            public static void save() {
                com.laynox.betterchat.files.players.save();
            }
            public static void set(Plugin plugin) {com.laynox.betterchat.files.players.set(plugin);}
        }
    }
}

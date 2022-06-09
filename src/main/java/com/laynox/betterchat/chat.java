package com.laynox.betterchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class chat {
    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input == null ? "&1null " : input);
    }
    public static class broadcast {
        public static void global(String message) {
            for (Player user:Bukkit.getOnlinePlayers()) user.sendMessage(chat.color(message));
            Bukkit.getConsoleSender().sendMessage(chat.color(message));
        }
        public static void users(Player sender, String message) {
            String output = sender.getName() + " " + message;
            for (Player user : Bukkit.getOnlinePlayers()) user.sendMessage(chat.color(output));
        }
        public static void users(String message) {
            for (Player user:Bukkit.getOnlinePlayers()) user.sendMessage(chat.color(message));
        }
        public static void operators(String message) {
            for (Player user:Bukkit.getOnlinePlayers()) if (user.isOp()) user.sendMessage(chat.color(message));
        }
    }
    public static class message {
        public static void send(String message) {
            for (Player user : Bukkit.getOnlinePlayers()) user.sendMessage(chat.color(message));
        }
        public static void direct(Player player, String message) {
            if (message.isEmpty()) return;
            player.sendMessage(chat.color(message));
        }
    }
}

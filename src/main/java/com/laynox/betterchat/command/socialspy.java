package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class socialspy {
    public socialspy(Player player) {
        FileConfiguration players = manager.files.players;
        if (!player.hasPermission("bc.socialspy")) {
            player.sendMessage(chat.color(manager.files.config.getString("messages.noPerms")));
            return;
        }
        String path = player.getUniqueId()+".socialspy";
        if (players.getBoolean(path)) {
            players.set(path, false);
            player.sendMessage(chat.color("&5SocialSpy &6desactivado."));
        } else {
            players.set(path, true);
            player.sendMessage(chat.color("&5SocialSpy &6activado."));
        }
        com.laynox.betterchat.files.players.save();
    }
    public static void message(String message, Player sender, Player target) {
        FileConfiguration players = manager.files.players, config = manager.files.config;
        for (Player user:Bukkit.getOnlinePlayers()) {
            if (players.getBoolean(user.getUniqueId()+".socialspy") && (sender != user || target != user)) {
                user.sendMessage(chat.color( config.getString("prefix.socialspy") + "&7[&b"+sender.getName()+"&c>>&b"+target.getName()+"&7] &f" + message));
            }
        }
        manager.console.log(config.getString("prefix.socialspy") + "&7[&b"+sender.getName()+"&c>>&b"+target.getName()+"&7] &f" + message);
    }
}

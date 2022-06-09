package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class vanish {
    public vanish(Player player) {
        FileConfiguration config = manager.files.config;
        if (!player.hasPermission("bc.vanish")) {
            player.sendMessage(config.getString("messages.noPerms"));
            return;
        }
        FileConfiguration players = manager.files.players;
        if (players.getBoolean(player.getUniqueId()+".vanished")) {
            players.set(player.getUniqueId()+".vanished", false);
            for (Player user : Bukkit.getOnlinePlayers()) {
                user.showPlayer(Bukkit.getPluginManager().getPlugin("BetterChat"), player);
                chat.message.send(config.getString("prefix.join") + player.getName());
            }
        } else {
            players.set(player.getUniqueId()+".vanished", true);
            for (Player user : Bukkit.getOnlinePlayers()) {
                user.hidePlayer(Bukkit.getPluginManager().getPlugin("BetterChat"), player);
                chat.message.send(config.getString("prefix.quit") + player.getName());
            }
        }
        manager.files.configFunctions.save();
    }
}

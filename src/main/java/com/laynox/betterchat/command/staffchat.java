package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class staffchat {
    public staffchat(Player sender) {
        if (!sender.hasPermission("bc.staffchat")) {
            sender.sendMessage(chat.color(manager.files.config.getString("messages.noPerms")));
            return;
        }
        FileConfiguration players = manager.files.players;
        if (!players.getBoolean(sender.getUniqueId()+".staffchat")) {
            players.set(sender.getUniqueId()+".staffchat", true);
            sender.sendMessage(chat.color("&5StaffChat &6activado."));
        } else {
            players.set(sender.getUniqueId()+".staffchat", false);
            sender.sendMessage(chat.color("&5StaffChat &6desactivado."));
        }
        com.laynox.betterchat.files.players.save();
    }
    public static void message(Player sender, String message) {
        FileConfiguration config = manager.files.config;
        String staffMsg = config.getString("chat.staff").replace("%player%", sender.getName()).replace("%message%", message);
        for (Player user: Bukkit.getOnlinePlayers()) if (user.isOp()) user.sendMessage(chat.color(staffMsg));
    }
}

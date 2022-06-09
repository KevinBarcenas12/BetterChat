package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class msg {
    public msg(Player sender, Player target, String message) {
        FileConfiguration config = manager.files.config;
        if (!sender.hasPermission("bc.msg")) {
            sender.sendMessage(chat.color(config.getString("messages.noPerms")));
            return;
        }
        sender.sendMessage(chat.color("&7[&bTú&c>>&b"+target.getName()+"&7] &f" + message));
        target.sendMessage(chat.color("&7[&b"+sender.getName()+"&c>>&bTú&7] &f" + message));
        socialspy.message(message, sender, target);
    }
}
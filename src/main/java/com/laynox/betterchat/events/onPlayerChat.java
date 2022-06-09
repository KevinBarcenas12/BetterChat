package com.laynox.betterchat.events;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.command.staffchat;
import com.laynox.betterchat.manager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onPlayerChat implements Listener {
    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player sender = event.getPlayer();
        String message = event.getMessage();
        FileConfiguration config = manager.files.config, players = manager.files.players;

        if (players.getBoolean(sender.getUniqueId()+".staffchat")) {
            staffchat.message(sender, message);
            return;
        }

        String playerName = sender.isOp() ? config.getString("chat.op-prefix")+sender.getName():sender.getName();
        String style = config.getString("chat.style"); assert style != null;
        String output = style.replace("%player%", playerName).replace("%message%", message);

        for (Player user: Bukkit.getOnlinePlayers()) if (!players.getBoolean(user.getUniqueId()+".muted-users."+ sender.getUniqueId())) {
            user.sendMessage(chat.color(output));
        }
        manager.console.log(chat.color(output));
    }
}

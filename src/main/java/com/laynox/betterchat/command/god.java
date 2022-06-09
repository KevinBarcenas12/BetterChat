package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.entity.Player;

public class god {
    public god(Player player) {
        if (!player.hasPermission("bc.god")) {
            player.sendMessage(chat.color(manager.files.config.getString("messages.noPerms")));
            return;
        }
        if (!player.isInvulnerable()) {
            player.setInvulnerable(false);
            player.sendMessage(chat.color("&6Modo &4Dios &6desactivado."));
        } else {
            player.setInvulnerable(true);
            player.sendMessage(chat.color("&6Modo &4Dios &6activado."));
        }
    }
}

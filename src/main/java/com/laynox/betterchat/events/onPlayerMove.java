package com.laynox.betterchat.events;

import com.laynox.betterchat.chat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onPlayerMove implements Listener {
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        FileConfiguration players = com.laynox.betterchat.files.players.get();
        if (players.getBoolean(event.getPlayer().getUniqueId()+".freezed")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(chat.color("&cEstás congelado! No te puedes mover!"));
        }
    }
}

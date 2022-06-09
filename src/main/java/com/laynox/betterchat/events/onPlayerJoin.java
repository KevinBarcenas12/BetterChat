package com.laynox.betterchat.events;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoin implements Listener {
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage(chat.color(manager.files.config.getString("prefix.join")+event.getPlayer().getName()));
        if (!event.getPlayer().hasPlayedBefore()) {
            chat.broadcast.users(event.getPlayer(), "&6ha entrado por primera vez!");
        }
    }
}

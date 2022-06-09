package com.laynox.betterchat.events;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onPlayerQuit implements Listener {
    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        event.setQuitMessage(chat.color(manager.files.config.getString("prefix.quit") + event.getPlayer().getName()));
        FileConfiguration players = manager.files.players;
        if (players.isBoolean(event.getPlayer().getUniqueId()+".staffchat")) players.set(event.getPlayer().getUniqueId()+".staffchat", false);
        if (players.isBoolean(event.getPlayer().getUniqueId()+".socialspy")) players.set(event.getPlayer().getUniqueId()+".socialspy", false);
    }
}

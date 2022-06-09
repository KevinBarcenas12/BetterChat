package com.laynox.betterchat.events;

import com.laynox.betterchat.chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeath implements Listener {
    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event) {
        String message = event.getDeathMessage();
        if (message.contains("fell from")) event.setDeathMessage(chat.color("&5" + event.getEntity().getName() + " &6se ha marcado un &bCanserbero"));
        else if (message.contains("Iron Golem")) event.setDeathMessage(chat.color("&5" + event.getEntity().getName() + " &6ha descubierto que los Golems son de hierro..."));
        else if (message.contains("slain") && event.getEntity().getKiller() != null) event.setDeathMessage(chat.color("&5" + event.getEntity().getName() + " &6 se ha morido por &3" + event.getEntity().getKiller().getName()));
        else event.setDeathMessage(chat.color("&5" + event.getEntity().getName() + " &6se ha morido..."));
    }
}
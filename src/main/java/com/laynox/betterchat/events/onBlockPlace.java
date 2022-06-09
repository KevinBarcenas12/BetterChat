package com.laynox.betterchat.events;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class onBlockPlace implements Listener {
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (manager.files.players.getBoolean(player.getUniqueId()+".freeze")) {
            event.setCancelled(true);
            player.sendMessage(chat.color("&cEstás congelado! No puedes colocar bloques!"));
        }
//        List<String> blocks = manager.files.config.getStringList("banned-blocks");
//        for (String block:blocks) if (event.getBlock().getType().getKey().toString().split(":")[1].equals(block)) {
//            event.setCancelled(true);
//            player.sendMessage(chat.color("&cEste bloque no se puede colocar!"));
//            return;
//        }

    }
}

package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class mute implements Listener {
    public mute(Player sender, Player target) {
        if (target == null) {
            sender.sendMessage(chat.color("&cDebe ser un jugador en línea!"));
            return;
        }
        if (sender == target) {
            sender.sendMessage(chat.color("&cNo te puedes silenciar a ti mismo!"));
            return;
        }
        FileConfiguration players = manager.files.players;
        if (players.getBoolean(sender.getUniqueId()+".muted-users."+target.getUniqueId())) {
            players.set(sender.getUniqueId()+".muted-users."+target.getUniqueId(), false);
            sender.sendMessage(chat.color("&6Has desmuteado a &5" + target.getName()));
        } else {
            players.set(sender.getUniqueId()+".muted-users."+target.getUniqueId(), true);
            sender.sendMessage(chat.color("&6Has muteado a &5"+target.getName()));
        }
    }
}

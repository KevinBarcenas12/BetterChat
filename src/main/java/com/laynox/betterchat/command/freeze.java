package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class freeze {
    public static void player(Player sender, Player target) {
        FileConfiguration players = manager.files.players;
        FileConfiguration config = manager.files.config;
        if (sender != null) {
            if (!sender.hasPermission("bc.freeze")) {
                sender.sendMessage(chat.color(config.getString("messages.noPerms")));
                return;
            }
            if (target == null) {
                sender.sendMessage(chat.color("&4El jugador debe estar en línea!"));
                return;
            }
            if (sender == target) {
                sender.sendMessage(chat.color("&No te puedes congelar a ti mismo!"));
                return;
            }
            if (players.getBoolean(target.getUniqueId()+".freezed")) {
                players.set(target.getUniqueId()+".freezed", false);
                sender.sendMessage(chat.color("&6Se ha descongelado a &5"+target.getName()));
                target.sendMessage(chat.color("&5"+sender.getName()+" &6te ha descongelado"));
                chat.broadcast.operators("&4"+sender.getName()+" &6ha descongelado a &5"+target.getName());
            } else {
                players.set(target.getUniqueId()+".freezed", true);
                sender.sendMessage(chat.color("&6Has congelado a &5"+target.getName()));
                target.sendMessage(chat.color("&5"+sender.getName()+" &6te ha congelado"));
                chat.broadcast.operators("&4"+sender.getName()+" &6ha congelado a &5"+target.getName());
            }
            manager.files.playerFunctions.save();
            return;
        }
        if (target == null) {
            manager.console.log("&4Debe ser un jugador en línea!");
            return;
        }
        if (players.getBoolean(target.getUniqueId()+".freezed")) {
            players.set(target.getUniqueId()+".freezed", false);
            target.sendMessage(chat.color("&6Has sido descongelado!"));
            manager.console.log("&6Se ha descongelado a &5"+target.getName());
            chat.broadcast.operators("&6Se ha descongelado a &5"+target.getName());
        } else {
            players.set(target.getUniqueId()+".freezed", true);
            target.sendMessage(chat.color("&6Has sido congelado!"));
            manager.console.log("&6Se ha congelado a &5"+target.getName());
            chat.broadcast.operators("&6Se ha congelado a &5"+target.getName());
        }
        manager.files.playerFunctions.save();
    }
}

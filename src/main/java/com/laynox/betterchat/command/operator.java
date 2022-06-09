package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class operator {
    public static void set(Player sender, Player target) {
        if (sender == null) {
            if (target.isOp()) {
                manager.console.log("&5"+target.getName()+" &6ya es un operador!");
                return;
            }
            target.setOp(true);
            chat.broadcast.operators("&5"+target.getName()+" &6ha conseguido ser operador");
            manager.console.log("&5"+target.getName()+" &6ahora es operador");
            return;
        }
        if (!sender.hasPermission("bc.op")) {
            sender.sendMessage(chat.color(manager.files.config.getString("messages.noPerms")));
            return;
        }
        if (target.isOp()) {
            sender.sendMessage(chat.color("&5"+target.getName()+" &6ya es un operador!"));
            return;
        }
        target.setOp(true);
        sender.sendMessage(chat.color("&5"+target.getName()+" &6ahora es un operador"));
        target.sendMessage(chat.color("&5"+sender.getName()+" &6te ha hecho un operador"));
        chat.broadcast.operators("&5"+sender.getName()+" &6ha hecho operador a &5"+target.getName());
    }
    public static void unset(Player sender, Player target) {
        FileConfiguration players = manager.files.players;
        if (sender == null) {
            if (!target.isOp()) {
                manager.console.log("&5"+target.getName()+" &no era un operador!");
                return;
            }
            if (players.getBoolean(target.getUniqueId()+".socialspy")) {
                players.set(target.getUniqueId()+".socialspy", false);
            }
            if (players.getBoolean(target.getUniqueId()+".staffchat")) {
                players.set(target.getUniqueId()+".staffchat", false);
            }
            target.setOp(false);
            manager.console.log("&5"+target.getName()+" &6ya no es operador");
            chat.broadcast.operators("&5"+target.getName()+" &6ha perdido su operador");
            return;
        }
        if (!sender.hasPermission("bc.deop")) {
            sender.sendMessage(chat.color(com.laynox.betterchat.files.config.get().getString("messages.noPerms")));
            return;
        }
        if (!target.isOp()) {
            sender.sendMessage(chat.color("&5"+target.getName()+" &no era un operador!"));
            return;
        }
        if (players.getBoolean(target.getUniqueId()+".socialspy")) {
            players.set(target.getUniqueId()+".socialspy", false);
        }
        if (players.getBoolean(target.getUniqueId()+".staffchat")) {
            players.set(target.getUniqueId()+".staffchat", false);
        }
        target.setOp(false);
        sender.sendMessage(chat.color("&5"+target.getName()+" &6ya no es operador"));
        chat.broadcast.operators("&5"+sender.getName()+" &6le ha quitado el operador a &5"+target.getName());
    }
}

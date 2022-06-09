package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class tele {
    public tele(Player sender, String[] args) {
        if (sender == null) return;

        StringBuilder msg = new StringBuilder();
        for (String it : args) msg.append(it).append(" ");

        switch (args.length) {
            case 1:
                if (!sender.hasPermission("bc.tele.self")) {
                    sender.sendMessage(chat.color("&4No tienes permisos!"));
                    return;
                }
                Player Target = Bukkit.getPlayer(args[0]);
                if (Target == null) {
                    sender.sendMessage(chat.color("&4Jugador no encontrado!"));
                    return;
                }
                if (sender.getName().equals(args[0])) {
                    sender.sendMessage(chat.color("&cNo te puedes teleportar a ti mismo!"));
                    return;
                }
                sender.teleport(Target.getLocation());
                sender.sendMessage(chat.color("&6Te has teleportado a &5"+Target.getName()));
                Target.sendMessage(chat.color("&5"+sender.getName()+" &6se ha teleportado a ti"));
                return;
            case 2:
                if (!sender.hasPermission("bc.tele.others")) {
                    sender.sendMessage(chat.color("&cNo tienes permiso para esto!"));
                    return;
                }
                if (args[0].equals(args[1])) {
                    sender.sendMessage(chat.color("&cNo puedes teleportar al mismo jugador!"));
                    return;
                }
                Player[] target = { Bukkit.getPlayerExact(args[0]), Bukkit.getPlayerExact(args[1]) };
                if (target[0] == null) {
                    sender.sendMessage(chat.color("&4Jugador 1 no encontrado!"));
                    return;
                }
                if (target[1] == null) {
                    sender.sendMessage(chat.color("&4Jugador 2 no encontrado!"));
                    return;
                }
                target[0].teleport(target[1].getLocation());
                target[1].sendMessage(chat.color("&4"+sender.getName()+" &6ha teleportado a &5"+target[0].getName()+" &6a ti"));
                target[0].sendMessage(chat.color("&4"+sender.getName()+" &6te ha teleportado a &5"+target[1].getName()));
                return;
            default:
                sender.sendMessage(chat.color(manager.files.config.getString("messages.null-args")));
        }
    }
}

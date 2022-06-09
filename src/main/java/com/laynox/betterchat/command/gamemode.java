package com.laynox.betterchat.command;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.manager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class gamemode {
    public gamemode(Player player, String[] args) {
        FileConfiguration config = manager.files.config;
        if (args.length > 2) {
            player.sendMessage(chat.color(config.getString("messages.null-args")));
            return;
        }
        if (Bukkit.getPlayerExact(args[1]) != null) player = Bukkit.getPlayerExact(args[1]);
        String mode = args[0];
        if (mode.equals("c") || mode.equals("creative")) {
            modes(player, GameMode.CREATIVE);
        }
        if (mode.equals("s") || mode.equals("survival")) {
            modes(player, GameMode.SURVIVAL);
        }
        if (mode.equals("spec") || mode.equals("spectator") || mode.equals("sp")) {
            modes(player, GameMode.SPECTATOR);
        }
        if (mode.equals("a") || mode.equals("adventure") || mode.equals("ad")) {
            modes(player, GameMode.ADVENTURE);
        }
    }
    public gamemode(Player sender, String args) {
        if (args.equals("c") || args.equals("creative")) {
            modes(sender, GameMode.CREATIVE);
        }
        if (args.equals("s") || args.equals("survival")) {
            modes(sender, GameMode.SURVIVAL);
        }
        if (args.equals("spec") || args.equals("sp") || args.equals("spectator")) {
            modes(sender, GameMode.SPECTATOR);
        }
        if (args.equals("a") || args.equals("adventure") || args.equals("ad")) {
            modes(sender, GameMode.ADVENTURE);
        }
    }
    public void modes(Player sender, GameMode mode) {
        FileConfiguration config = manager.files.config;
        switch (mode) {
            case CREATIVE:
                if (!sender.hasPermission("bc.gamemode.creative")) {
                    sender.sendMessage(chat.color(config.getString("messages.noPerms")));
                    return;
                }
                if (sender.getGameMode() == GameMode.CREATIVE) {
                    sender.sendMessage(chat.color(config.getString("messages.actual-gamemode")));
                    return;
                }
                sender.sendMessage(chat.color("&6Tu modo de juego ha cambiado a &5Creativo"));
                sender.setGameMode(GameMode.CREATIVE);
                return;
            case SURVIVAL:
                if (!sender.hasPermission("bc.gamemode.survival")) {
                    sender.sendMessage(chat.color(config.getString("messages.noPerms")));
                    return;
                }
                if (sender.getGameMode() == GameMode.SURVIVAL) {
                    sender.sendMessage(chat.color(config.getString("messages.actual-gamemode")));
                    return;
                }
                sender.sendMessage(chat.color("&6Tu modo de juego ha cambiado a &5Supervivencia"));
                sender.setGameMode(GameMode.SURVIVAL);
                return;
            case SPECTATOR:
                if (!sender.hasPermission("bc.gamemode.spectator")) {
                    sender.sendMessage(chat.color(config.getString("messages.noPerms")));
                    return;
                }
                if (sender.getGameMode() == GameMode.SPECTATOR) {
                    sender.sendMessage(chat.color(config.getString("messages.actual-gamemode")));
                    return;
                }
                sender.sendMessage(chat.color("&6Tu modo de juego ha cambiado a &5Espectador"));
                sender.setGameMode(GameMode.SPECTATOR);
                return;
            case ADVENTURE:
                if (!sender.hasPermission("bc.gamemode.adventure")) {
                    sender.sendMessage(chat.color(config.getString("messages.noPerms")));
                    return;
                }
                if (sender.getGameMode() == GameMode.ADVENTURE) {
                    sender.sendMessage(chat.color(config.getString("messages.actual-gamemode")));
                    return;
                }
                sender.sendMessage(chat.color("&6Tu modo de juego ha cambiado a &5Aventura"));
                sender.setGameMode(GameMode.ADVENTURE);
        }
    }
}

package com.laynox.betterchat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.laynox.betterchat.command.*;
import com.laynox.betterchat.events.*;

public final class Main extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) getDataFolder().mkdirs();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this, this);
        pm.registerEvents(new onPlayerJoin(), this);
        pm.registerEvents(new onPlayerQuit(), this);
        pm.registerEvents(new onBlockBreak(), this);
        pm.registerEvents(new onBlockPlace(), this);
//        pm.registerEvents(new onPlayerChat(), this);
        pm.registerEvents(new onPlayerDeath(), this);
        pm.registerEvents(new onEntityDeath(), this);
        pm.registerEvents(new onPlayerMove(), this);
        config.options().copyDefaults(true);
        saveDefaultConfig();
        plugin = this;
        manager.files.configFunctions.set(this);
        manager.files.playerFunctions.set(this);
        getServer().getConsoleSender().sendMessage(chat.color(config.getString("prefix.this")+"Plugin iniciado."));
    }
    @Override
    public void onDisable() {
        saveConfig();
        getServer().getConsoleSender().sendMessage(chat.color(config.getString("prefix.this")+"Plugin deshabilitado."));
        manager.files.playerFunctions.save();
        manager.files.configFunctions.save();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        switch (command.getName()) {
            case "tele" -> {
                if (!(sender instanceof Player)) {
                    manager.console.log("&6El comando debe ser ejecutado por un jugador!");
                    return true;
                }
                player = (Player) sender;
                new tele(player, args);
                return true;
            }
            case "creative", "survival", "spectator" -> {
                if (!(sender instanceof Player)) {
                    manager.console.log("&6El comando debe ser ejecutado por un jugador!");
                    return true;
                }
                player = (Player) sender;
                new gamemode(player, command.getName());
                return true;
            }
            case "gamemode" -> {
                if (!(sender instanceof Player)) {
                    manager.console.log("&6El comando debe ser ejecutado por un jugador!");
                    return true;
                }
                player = (Player) sender;
                if (args.length == 1) {
                    new gamemode(player, args[0]);
                } else {
                    new gamemode(player, args);
                }
                return true;
            }
            case "god" -> {
                if (!(sender instanceof Player)) {
                    manager.console.log("&6El comando debe ser ejecutado por un jugador!");
                    return true;
                }
                player = (Player) sender;
                new god(player);
                return true;
            }
//            case "socialspy" -> {
//                if (!(sender instanceof Player)) {
//                    manager.console.log("&6El comando debe ser ejecutado por un jugador!");
//                    return true;
//                }
//                player = (Player) sender;
//                new socialspy(player);
//                return true;
//            }
            case "msg" -> {
                if (!(sender instanceof Player)) {
                    manager.console.log("&6El comando debe ser ejecutado por un jugador!");
                    return true;
                }
                player = (Player) sender;
                StringBuilder message = new StringBuilder();
                if (args.length < 2) {
                    player.sendMessage(chat.color("&cDebes escribir un mensaje!"));
                    return true;
                } else for (int i = 1; i < args.length; i++) message.append(args[i]).append(" ");
                Player[] users = {player, Bukkit.getPlayerExact(args[0])};
                new msg(users[0], users[1], message.toString());
                return true;
            }
//            case "vanish" -> {
//                if (!(sender instanceof Player)) {
//                    manager.console.log("&6El comando debe ser ejecutado por un jugador!");
//                    return true;
//                }
//                player = (Player) sender;
//                new vanish(player);
//                return true;
//            }
//            case "staffchat" -> {
//                if (!(sender instanceof Player)) {
//                    manager.console.log("&6El comando debe ser ejecutado por un jugador!");
//                    return true;
//                }
//                player = (Player) sender;
//                new staffchat(player);
//                return true;
//            }
            case "op" -> {
                player = sender instanceof Player ? (Player) sender : null;
                if (player == null && args.length != 1) {
                    manager.console.log(config.getString("messages.null-args"));
                    return true;
                }
                if (args.length != 1) {
                    player.sendMessage(chat.color(config.getString("messages.null-args")));
                    return true;
                }
                if (Bukkit.getPlayerExact(args[0]) == null) {
                    player.sendMessage(chat.color("&4El jugador debe estar en línea!"));
                    return true;
                }
                operator.set(player, Bukkit.getPlayerExact(args[0]));
                return true;
            }
            case "deop" -> {
                player = sender instanceof Player ? (Player) sender : null;
                if (player == null && args.length != 1) {
                    manager.console.log(config.getString("messages.null-args"));
                    return true;
                }
                if (args.length != 1) {
                    player.sendMessage(chat.color(config.getString("messages.null-args")));
                    return true;
                }
                if (Bukkit.getPlayerExact(args[0]) == null) {
                    player.sendMessage(chat.color("&4El jugador debe estar en línea!"));
                    return true;
                }
                operator.unset(player, Bukkit.getPlayerExact(args[0]));
                return true;
            }
            case "bcreload" -> {
                new reload();
                return true;
            }
//            case "freeze" -> {
//                player = sender instanceof Player ? (Player) sender : null;
//                if (player == null && args.length != 1) {
//                    manager.console.log(config.getString("messages.null-args"));
//                    return true;
//                }
//                if (args.length != 1) {
//                    player.sendMessage(chat.color(config.getString("messages.null-args")));
//                    return true;
//                }
//                freeze.player(player, Bukkit.getPlayerExact(args[0]));
//                return true;
//            }
//            case "mute" -> {
//                if (!(sender instanceof Player)) {
//                    manager.console.log(chat.color("&cEl comando debe ser ejecutado por un jugador!"));
//                    return true;
//                }
//                player = (Player) sender;
//                if (args.length == 0) {
//                    player.sendMessage(chat.color("&cDebes especificar un jugador!"));
//                    return true;
//                }
//                new mute(player, Bukkit.getPlayerExact(args[0]));
//                return true;
//            }
        }
        return true;
    }
}

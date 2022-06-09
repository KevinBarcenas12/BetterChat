package com.laynox.betterchat.util;

import com.laynox.betterchat.chat;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActionBarTitleUtils {
    public static String getItemTitle(ItemStack item) {
        if (item.getType().equals(Material.AIR) && item.getAmount() < 1) return "";

        String format = " &7+ &3%amount% &b%name%";

        List<String> nameWords = autoCapitalize(item.getType().getKey().toString().split(":")[1].split("_"));
        String name = "";

        for (int i = 0; i < nameWords.size(); i++) name += nameWords.get(i) + (i < (nameWords.size() - 1) ? " " : "");

        return format.replaceFirst("%amount%", (item.getAmount() == 0 ? 1 : item.getAmount()) + "").replace("%name%", name);
    }
    public static void sendTitle(Player target, String message) {
        target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(chat.color(message)));
    }
    private static List<String> autoCapitalize(String[] words) {

        List<String> result = new ArrayList<>();

        for (String word:words) {
            result.add(word.substring(0,1).toUpperCase(Locale.ROOT) + word.substring(1));
        }

        return result;

    }
}

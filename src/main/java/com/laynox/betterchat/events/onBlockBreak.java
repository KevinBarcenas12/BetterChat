package com.laynox.betterchat.events;

import com.laynox.betterchat.chat;
import com.laynox.betterchat.files.blocks;
import com.laynox.betterchat.manager;
import com.laynox.betterchat.util.ActionBarTitleUtils;
import com.laynox.betterchat.util.DroppedItemsUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class onBlockBreak implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        Player player = event.getPlayer();
        if (manager.files.players.getBoolean(player.getUniqueId()+".freezed")) {
            event.setCancelled(true);
            player.sendMessage(chat.color("&cEstás congelado! No puedes romper bloques!"));
            return;
        }
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1.0F);
        Material block = event.getBlock().getType();
        for (Material item:blocks.bed) if (block.equals(item)) {
            event.setDropItems(false);
            if (player.getInventory().firstEmpty() == -1) player.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(item));
            else player.getInventory().addItem(new ItemStack(item));
            return;
        }
        for (Material item:blocks.door) if (block.equals(item)) {
            event.setDropItems(false);
            if (player.getInventory().firstEmpty() == -1) player.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(item));
            else player.getInventory().addItem(new ItemStack(item));
            return;
        }

        Collection<ItemStack> items = event.getBlock().getDrops(player.getInventory().getItemInMainHand());
        if (items.isEmpty()) return;

        event.setDropItems(false);

        DroppedItemsUtil.droppedItems(player, items, player.getInventory().getStorageContents());

    }
}
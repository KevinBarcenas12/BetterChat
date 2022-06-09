package com.laynox.betterchat.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DroppedItemsUtil {
    public static void droppedItems(Player player, Collection<ItemStack> items, ItemStack[] slots) {
        List<ItemStack> drops = new ArrayList<>();
        List<ItemStack> saved = new ArrayList<>();
        for (ItemStack item:items) {
            if (player.getInventory().getItemInOffHand().getType().equals(item.getType())) {
                if (player.getInventory().getItemInOffHand().getAmount() + item.getAmount() > item.getMaxStackSize()) {
                    saved.add(new ItemStack(item.getType(), item.getAmount() - (item.getMaxStackSize() - player.getInventory().getItemInOffHand().getAmount())));
                    item.setAmount(item.getAmount() - (item.getMaxStackSize() - player.getInventory().getItemInOffHand().getAmount()));
                    player.getInventory().setItemInOffHand(new ItemStack(item.getType(), item.getMaxStackSize()));
                }
                else {
                    player.getInventory().setItemInOffHand(new ItemStack(item.getType(), item.getAmount() + player.getInventory().getItemInOffHand().getAmount()));
                    saved.add(item);
                    continue;
                }
            }
            if (player.getInventory().firstEmpty() == -1) {
                for (ItemStack slot:slots) {
                    if (item.getType().equals(slot.getType())) {
                        if (slot.getAmount() + item.getAmount() > slot.getMaxStackSize()) {
                            player.getInventory().addItem(new ItemStack(slot.getType(), slot.getMaxStackSize() - slot.getAmount()));
                            if (slot.getMaxStackSize() - slot.getAmount() > 0) saved.add(new ItemStack(item.getType(), slot.getMaxStackSize() - slot.getAmount()));
                            item.setAmount(item.getAmount() - (slot.getMaxStackSize() - slot.getAmount()));
                        }
                        else {
                            player.getInventory().addItem(item);
                            saved.add(item);
                            item.setAmount(0);
                        }
                        if (item.getAmount() < 1) break;
                    }
                }
                if (item.getAmount() > 0) drops.add(item);
            }
            else {
                player.getInventory().addItem(item);
                saved.add(item);
            }
        }
        String title = "";
        for (ItemStack drop:drops) {
            player.getWorld().dropItemNaturally(player.getLocation(), drop);
        }
        List<String> names = new ArrayList<>();
        for (ItemStack save:saved) names.add(ActionBarTitleUtils.getItemTitle(save));
        for (String name:names) title+=name;
        ActionBarTitleUtils.sendTitle(player, title);
    }
}

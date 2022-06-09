package com.laynox.betterchat.events;

import com.laynox.betterchat.util.DroppedItemsUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class onEntityDeath implements Listener {
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        if (player == null) return;

        Collection<ItemStack> items = event.getDrops();
        ItemStack[] slots = player.getInventory().getStorageContents();

        if (items.isEmpty()) return;
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.5F, 1.0F);

        DroppedItemsUtil.droppedItems(player, items, slots);
        event.getDrops().clear();
    }
}
